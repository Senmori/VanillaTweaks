package net.senmori.vanillatweaks.controllers;

import net.senmori.vanillatweaks.VanillaTweaks;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.vehicle.VehicleEnterEvent;
import org.bukkit.inventory.ItemStack;

public class MinecartController extends TweakController implements Listener {
    public MinecartController(VanillaTweaks plugin) {
        super(plugin);
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void changeMinecart(PlayerInteractAtEntityEvent event) {
        if(!(event.getRightClicked() instanceof Minecart)) return;
        event.setCancelled(true);
        ItemStack held = event.getPlayer().getInventory().getItem(event.getPlayer().getInventory().getHeldItemSlot());
        Location loc = event.getRightClicked().getLocation();
        switch(held.getType()) {
            case CHEST:
            case TRAPPED_CHEST:
                event.setCancelled(true);
                event.getPlayer().getWorld().spawnEntity(loc, EntityType.MINECART_CHEST);
                event.getPlayer().teleport(event.getPlayer().getLocation().add(0.0, -0.5, 1.0));
                event.getRightClicked().remove();
                return;
            case HOPPER:
                event.setCancelled(true);
                event.getPlayer().getWorld().spawnEntity(loc, EntityType.MINECART_HOPPER);
                event.getPlayer().teleport(event.getPlayer().getLocation().add(0.0, -0.5, 1.0));
                event.getRightClicked().remove();
                return;
            case FURNACE:
                event.setCancelled(true);
                event.getPlayer().getWorld().spawnEntity(loc, EntityType.MINECART_FURNACE);
                event.getPlayer().teleport(event.getPlayer().getLocation().add(0.0, -0.5, 1.0));
                event.getRightClicked().remove();
                return;
            default:
                return;
        }
    }

    @EventHandler
    public void onEnterVehicle(VehicleEnterEvent event) {
        if(!(event.getEntered() instanceof Player)) return;
        if(!(event.getVehicle() instanceof Minecart)) return;
        if(!( (Player) event.getEntered() ).isSneaking()) return;

        Player player = (Player)event.getEntered();
        ItemStack held = player.getInventory().getItem(player.getInventory().getHeldItemSlot());
        Location loc = event.getVehicle().getLocation();
        switch(held.getType()) {
            case CHEST:
            case TRAPPED_CHEST:
                event.setCancelled(true);
                event.getEntered().getWorld().spawnEntity(loc, EntityType.MINECART_CHEST);
                event.getVehicle().remove();
                return;
            case HOPPER:
                event.setCancelled(true);
                event.getEntered().getWorld().spawnEntity(loc, EntityType.MINECART_HOPPER);
                event.getVehicle().remove();
                return;
            case FURNACE:
                event.setCancelled(true);
                event.getEntered().getWorld().spawnEntity(loc, EntityType.MINECART_FURNACE);
                event.getVehicle().remove();
                return;
            default:
                return;
        }
    }
}
