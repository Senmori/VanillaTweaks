package net.senmori.vanillatweaks.controllers;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import com.google.common.collect.Sets;
import net.minecraft.server.v1_12_R1.EntityVillager;
import net.minecraft.server.v1_12_R1.Item;
import net.minecraft.server.v1_12_R1.ItemStack;
import net.minecraft.server.v1_12_R1.MinecraftKey;
import net.minecraft.server.v1_12_R1.PathfinderGoalTempt;
import net.senmori.senlib.configuration.ConfigOption;
import net.senmori.vanillatweaks.VanillaTweaks;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftVillager;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.world.WorldLoadEvent;
import org.bukkit.event.world.WorldUnloadEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class VillagerController extends TweakController implements Listener {

    private Set<UUID> loadedWorlds = Sets.newHashSet();
    private ItemStack followStack = ItemStack.a;
    public VillagerController(VanillaTweaks plugin) {
        super(plugin);

        if(!getSettings().VILLAGER.ENABLED.getValue()) return;

        String blockName = getSettings().VILLAGER.FOLLOW_BLOCK.getValue();
        if(blockName.isEmpty()) return;

        Item item = Item.REGISTRY.get(new MinecraftKey(blockName));
        ItemStack stack = new ItemStack(item);
        if(!stack.isEmpty()) {
            followStack = stack;
        }

        plugin.getServer().getPluginManager().registerEvents(this, plugin);

        // run every 5 seconds and clear loadedWorlds to ensure villagers keep getting their goals set
        new BukkitRunnable() {
            @Override
            public void run() {
                loadedWorlds.clear();
                setAllVillagers();
            }
        }.runTaskTimer(getPlugin(), 1L, 20L * 5L);
    }

    @EventHandler
    public void onSpawn(EntitySpawnEvent event) {
        if(!getSettings().VILLAGER.ENABLED.getValue()) return;
        if(followStack.isEmpty()) return;
        if(event.getEntity().getType() != EntityType.VILLAGER) return;

        if(!loadedWorlds.contains(event.getEntity().getWorld().getUID()) ) {
            setAllVillagers();
            loadedWorlds.add(event.getEntity().getWorld().getUID());
        }

        Villager villager = (Villager)event.getEntity();
        setGoal(villager, getPriority());
    }

    @EventHandler
    public void loadVillagers(PlayerJoinEvent event) {
        if(! loadedWorlds.contains(event.getPlayer().getWorld().getUID())) {
            setAllVillagers();
            loadedWorlds.add(event.getPlayer().getWorld().getUID());
        }
    }

    @EventHandler
    public void loadVillagers(PlayerChangedWorldEvent event) {
        if(! loadedWorlds.contains(event.getPlayer().getWorld().getUID())) {
            setAllVillagers();
            loadedWorlds.add(event.getPlayer().getWorld().getUID());
        }
    }

    @EventHandler
    public void onLoadWorld(WorldLoadEvent event) {
        if(! loadedWorlds.contains(event.getWorld().getUID())) {
            setAllVillagers();
            loadedWorlds.add(event.getWorld().getUID());
        }
    }

    @EventHandler
    public void onUnload(WorldUnloadEvent event) {
        if(loadedWorlds.contains(event.getWorld().getUID())) {
            loadedWorlds.remove(event.getWorld().getUID());
        }
    }

    private void setGoal(Villager villager, int priority) {
        EntityVillager nmsVillager = ((CraftVillager)villager).getHandle();
        PathfinderGoalTempt finalGoal = getGoal(villager);

        nmsVillager.goalSelector.a(finalGoal); // remove goal
        nmsVillager.goalSelector.a(priority, finalGoal); // add goal
    }

    private PathfinderGoalTempt getGoal(Villager villager) {
        return getGoal(villager, 0.6D, followStack.getItem(), false);
    }

    private PathfinderGoalTempt getGoal(Villager villager, double speed, Item item, boolean scaredBySuddenMovement) {
        return new PathfinderGoalTempt(((CraftVillager)villager).getHandle(), speed, item, scaredBySuddenMovement);
    }

    private int getPriority() {
        return 4;
    }

    private void setAllVillagers() {
        if(!getSettings().VILLAGER.ENABLED.getValue()) return;

        for(World world : getPlugin().getServer().getWorlds()) {
            List<Villager> villagers = world.getEntitiesByClass(Villager.class).stream()
                                                               .filter(Villager::hasAI) // ignore villagers that don't have AI
                                                               .collect(Collectors.toList());

            villagers.forEach(v -> {
                setGoal(v, getPriority());
            });

            villagers.clear();
        }
    }
}
