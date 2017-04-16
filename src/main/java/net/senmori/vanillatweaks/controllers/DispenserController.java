package net.senmori.vanillatweaks.controllers;

import net.senmori.vanillatweaks.VanillaTweaks;
import net.senmori.vanillatweaks.registry.dispenser.DispenserRegistry;
import net.senmori.vanillatweaks.registry.dispenser.behaviour.FillBottleBehaviour;
import net.senmori.vanillatweaks.registry.dispenser.behaviour.RecordBehaviour;
import net.senmori.vanillatweaks.registry.dispenser.behaviour.WaterBucketBehaviour;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
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
        getRegistry().register(Material.GLASS_BOTTLE, new FillBottleBehaviour());
        for(Material material : Material.values()) {
            if(material.isRecord()) {
                getRegistry().register(material, new RecordBehaviour());
            }
        }
        getPlugin().getServer().getPluginManager().registerEvents(this, getPlugin());
    }


    @EventHandler
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
