package net.senmori.vanillatweaks.controllers.tasked;

import com.google.common.collect.ImmutableMap;

import java.util.HashMap;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import net.senmori.vanillatweaks.VanillaTweaks;
import net.senmori.vanillatweaks.config.SettingsManager;
import net.senmori.vanillatweaks.controllers.TweakController;
import net.senmori.vanillatweaks.tasks.MinecartSpawnTask;
import org.bukkit.Bukkit;
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
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitTask;

public class MinecartController extends TweakController implements Listener {

    private final ImmutableMap<Material, EntityType> conversionMap;
    private final Set<MinecartSpawnTask> tasks = Sets.newHashSet();
    private SettingsManager settings;

    public MinecartController(VanillaTweaks plugin) {
        super(plugin);
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        this.settings = plugin.getSettingsManager();

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
        if(!settings.MINECART.MODIFICATIONS.getValue()) return;
        if(!(event.getRightClicked() instanceof Minecart)) return;
        if(!event.getPlayer().isSneaking()) return;
        if(!event.getRightClicked().getPassengers().isEmpty()) return; // ignore if minecart has passengers

        ItemStack stack = event.getPlayer().getInventory().getItem(event.getPlayer().getInventory().getHeldItemSlot());

        if(stack != null && conversionMap.containsKey(stack.getType())) {
            event.setCancelled(true);
            Location loc = event.getRightClicked().getLocation();
            event.getRightClicked().remove();
            spawn(loc, conversionMap.getOrDefault(stack.getType(), EntityType.MINECART));

            if(event.getPlayer().getGameMode() != GameMode.CREATIVE) {
                stack.setAmount(stack.getAmount() - 1);

                if(stack.getAmount() <= 0) {
                    stack.setType(Material.AIR); // remove itemstack
                }
            }

        }
    }

    @SuppressWarnings("deprecation")
    private void spawn(Location loc, EntityType type) {
        tasks.add(new MinecartSpawnTask(getPlugin(), loc, type));
        new BukkitRunnable() {
            @Override
            public void run() { // remove all tasks that are not running, not queued, and/or cancelled
                tasks.stream()
                     .filter(BukkitRunnable::isCancelled)
                     .collect(Collectors.toList())
                     .forEach(tasks::remove);
            }
        }.runTaskLater(VanillaTweaks.getInstance(), 1L);
    }
}
