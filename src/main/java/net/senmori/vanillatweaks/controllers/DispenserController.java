package net.senmori.vanillatweaks.controllers;

import net.senmori.vanillatweaks.VanillaTweaks;
import net.senmori.vanillatweaks.registry.dispenser.DispenserRegistry;
import net.senmori.vanillatweaks.registry.dispenser.behaviour.WaterBucketBehaviour;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDispenseEvent;

public class DispenserController extends TweakController implements Listener {

    private static DispenserRegistry REGISTRY;

    public DispenserController(VanillaTweaks plugin) {
        super(plugin);
        if(REGISTRY == null) {
            REGISTRY = new DispenserRegistry(getPlugin());
        }
        getRegistry().register(Material.WATER_BUCKET, new WaterBucketBehaviour());
        getPlugin().getServer().getPluginManager().registerEvents(this, getPlugin());
    }


    @EventHandler(priority = EventPriority.LOWEST)
    public void onDispense(BlockDispenseEvent event) {
        if(getRegistry().isRegistered(event.getItem())) {
            event.setCancelled(true);
            getRegistry().dispense(event.getItem(), event.getBlock());
        }
    }

    private DispenserRegistry getRegistry() {
        return REGISTRY;
    }
}
