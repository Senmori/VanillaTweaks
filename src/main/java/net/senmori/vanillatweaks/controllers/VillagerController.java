package net.senmori.vanillatweaks.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.server.v1_11_R1.EntityVillager;
import net.minecraft.server.v1_11_R1.Item;
import net.minecraft.server.v1_11_R1.ItemStack;
import net.minecraft.server.v1_11_R1.MinecraftKey;
import net.minecraft.server.v1_11_R1.PathfinderGoalTempt;
import net.senmori.vanillatweaks.VanillaTweaks;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_11_R1.entity.CraftVillager;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class VillagerController extends TweakController implements Listener {

    private Map<String,Boolean> loaded = new HashMap<>();
    private ItemStack followStack = ItemStack.a;
    public VillagerController(VanillaTweaks plugin) {
        super(plugin);


        String blockName = getPlugin().getTweakConfig().getVillagerFollowBlock();
        if(blockName.isEmpty()) return;

        Item item = Item.REGISTRY.get(new MinecraftKey(blockName));
        ItemStack stack = new ItemStack(item);
        if(!stack.isEmpty()) {
            followStack = stack;
        }

        plugin.getServer().getPluginManager().registerEvents(this, plugin);

        // run every 5 seconds and clear loaded to ensure villagers keep getting their goals set
        new BukkitRunnable() {
            @Override
            public void run() {
                loaded.clear();
            }
        }.runTaskTimer(getPlugin(), 1L, 20L * 5);
    }

    @EventHandler(ignoreCancelled = true)
    public void onSpawn(EntitySpawnEvent event) {
        if(!getPlugin().getTweakConfig().getVillagersShouldFollow()) return;
        if(followStack.isEmpty()) return;
        if(event.getEntity().getType() != EntityType.VILLAGER) return;

        if(!loaded.containsKey(event.getEntity().getWorld().getName()) || !loaded.get(event.getEntity().getWorld().getName())) {
            setAllVillagers();
            loaded.put(event.getEntity().getWorld().getName(), true);
        }

        Villager villager = (Villager)event.getEntity();
        setGoal(villager, getPriority(), getGoal(villager));
    }

    @EventHandler
    public void loadVillagers(PlayerJoinEvent event) {
        if(!loaded.containsKey(event.getPlayer().getWorld().getName()) || !loaded.get(event.getPlayer().getWorld().getName())) {
            setAllVillagers();
            loaded.put(event.getPlayer().getWorld().getName(), true);
        }
    }

    @EventHandler
    public void loadVillagers(PlayerChangedWorldEvent event) {
        if(!loaded.containsKey(event.getPlayer().getWorld().getName()) || !loaded.get(event.getPlayer().getWorld().getName())) {
            setAllVillagers();
            loaded.put(event.getPlayer().getWorld().getName(), true);
        }
    }

    private void setGoal(Villager villager, int priority, PathfinderGoalTempt goal) {
        EntityVillager nmsVillager = ((CraftVillager)villager).getHandle();
        PathfinderGoalTempt finalGoal = getGoal(villager);

        nmsVillager.goalSelector.a(finalGoal); // remove goal
        nmsVillager.goalSelector.a(getPriority(), finalGoal); // add goal
    }

    private PathfinderGoalTempt getGoal(Villager villager) {
        return getGoal(villager, 0.6D, followStack.getItem(), false);
    }

    private PathfinderGoalTempt getGoal(Villager villager, double speed, Item item, boolean scaredBySuddenMovement) {
        return new PathfinderGoalTempt(((CraftVillager)villager).getHandle(), speed, item, false);
    }

    private int getPriority() {
        return 4;
    }

    private void setAllVillagers() {
        if(!getPlugin().getTweakConfig().getVillagersShouldFollow()) return;

        for(World world : getPlugin().getServer().getWorlds()) {
            List<Villager> villagers = (List<Villager>)world.getEntitiesByClass(Villager.class);

            villagers.forEach(v -> {
                setGoal(v, getPriority(), getGoal(v));
            });

            villagers.clear();
        }
    }
}
