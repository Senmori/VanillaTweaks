package net.senmori.vanillatweaks.controllers;

import net.senmori.vanillatweaks.VanillaTweaks;
import net.senmori.vanillatweaks.registry.dispenser.DispenserRegistry;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDispenseEvent;

public class DispenserController extends TweakController implements Listener {

    private static DispenserRegistry REGISTRY;

    public DispenserController(VanillaTweaks plugin) {
        super(plugin);
        REGISTRY = DispenserRegistry.getInstance();
        getPlugin().getServer().getPluginManager().registerEvents(this, getPlugin());
    }


    @EventHandler(priority = EventPriority.LOWEST)
    public void onDispense(BlockDispenseEvent event) {
        if(REGISTRY.isRegistered(event.getItem())) {
            event.setCancelled(true);
            DispenserController.REGISTRY.dispense(event.getItem(), event.getBlock());
        }
    }

    @Override
    public boolean hasRegistry() {
        return true;
    }

    @Override
    public DispenserRegistry getRegistry() {
        return REGISTRY;
    }
}
