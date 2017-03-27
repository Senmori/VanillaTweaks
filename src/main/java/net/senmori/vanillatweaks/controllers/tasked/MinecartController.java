package net.senmori.vanillatweaks.controllers.tasked;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;
import net.senmori.vanillatweaks.VanillaTweaks;
import net.senmori.vanillatweaks.controllers.TweakController;
import net.senmori.vanillatweaks.tasks.MinecartSpawnTask;
import net.senmori.vanillatweaks.util.LogHandler;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Minecart;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class MinecartController extends TweakController implements Listener {

    private final ImmutableMap<Material, EntityType> conversionMap;
    private final WeakHashMap<Location, MinecartSpawnTask> tasks = new WeakHashMap<>();

    public MinecartController(VanillaTweaks plugin) {
        super(plugin);
        plugin.getServer().getPluginManager().registerEvents(this, plugin);

        conversionMap = new ImmutableMap.Builder<Material,EntityType>()
                                .put(Material.CHEST, EntityType.MINECART_CHEST)
                                .put(Material.TRAPPED_CHEST, EntityType.MINECART_CHEST)
                                .put(Material.HOPPER, EntityType.MINECART_HOPPER)
                                .put(Material.FURNACE, EntityType.MINECART_FURNACE)
                                .put(Material.BURNING_FURNACE, EntityType.MINECART_FURNACE)
                                .put(Material.TNT, EntityType.MINECART_TNT)
                                .put(Material.COMMAND, EntityType.MINECART_COMMAND)
                                .build();
    }

    @EventHandler
    public void changeMinecart(PlayerInteractAtEntityEvent event) {
        if(!getPlugin().getTweakConfig().canModifyMinecarts()) return;
        if(! (event.getRightClicked() instanceof Minecart) ) return;
        if(!event.getPlayer().isSneaking()) return;
        if(!event.getRightClicked().getPassengers().isEmpty()) return; // ignore if minecart has passengers
        event.getPlayer().sendMessage("Test");
        ItemStack stack = event.getPlayer().getInventory().getItemInMainHand();

        if(stack != null && conversionMap.containsKey(stack.getType())) {
            event.setCancelled(true);
            Location loc = event.getRightClicked().getLocation();
            event.getRightClicked().remove();
            spawn(loc, conversionMap.getOrDefault(stack.getType(), EntityType.MINECART));

            if(event.getPlayer().getGameMode() != GameMode.CREATIVE) {
                event.getPlayer().getInventory().getItemInMainHand().setAmount(stack.getAmount() - 1);

                if(stack.getAmount() <= 0) {
                    event.getPlayer().getInventory().setItemInMainHand(new ItemStack(Material.AIR)); // remove itemstack
                }
            }

        }
    }

    private void spawn(Location loc, EntityType type) {
        if(!tasks.containsKey(loc)) {
            tasks.put(loc, new MinecartSpawnTask(getPlugin(), loc, type));
            getPlugin().getServer().getScheduler().runTaskLater(getPlugin(), new BukkitRunnable() {
                @Override
                public void run() {
                    tasks.remove(loc);
                }
            }, 1);
        } else {
            tasks.remove(loc);
        }
    }
}
