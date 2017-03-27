package net.senmori.vanillatweaks.controllers;

import net.senmori.vanillatweaks.VanillaTweaks;

import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

/**
 * This controller will handle the addition of arms to armor stands
 */
public final class ArmorStandController extends TweakController implements Listener {

    public ArmorStandController(VanillaTweaks plugin) {
        super(plugin);
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onEntitySpawn(EntitySpawnEvent event) {
        if(!event.getEntity().getType().equals(EntityType.ARMOR_STAND)) return;

        ArmorStand stand = (ArmorStand)event.getEntity();
        stand.setArms(getPlugin().getTweakConfig().showArmorStandArms());
        stand.setBasePlate(getPlugin().getTweakConfig().showArmorStandBasePlate());
    }
}
