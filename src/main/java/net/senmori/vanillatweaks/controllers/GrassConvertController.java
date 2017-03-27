package net.senmori.vanillatweaks.controllers;

import java.util.List;
import net.senmori.vanillatweaks.VanillaTweaks;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * This controller dictates if wheat seeds can convert Dirt into Grass
 */
public final class GrassConvertController extends TweakController implements Listener {
    private final Material SEEDS = Material.SEEDS;
    public GrassConvertController(VanillaTweaks plugin) {
        super(plugin);
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @Override
    public boolean requiresRestart() {
        return false;
    }

    @Override
    public boolean hasTasks() {
        return false;
    }

    @Override
    public List<BukkitRunnable> getTasks() {
        return null;
    }

    @EventHandler
    public void convertGrass(PlayerInteractEvent event) {
        if(event.getAction() != Action.RIGHT_CLICK_BLOCK) return;
        if(!event.hasItem()) return;
        if(event.getItem().getType() != SEEDS) return;
        if(event.getClickedBlock().getType() != Material.DIRT) return;
        if(!getPlugin().getTweakConfig().canConvertDirt()) return;

        event.getItem().setAmount(event.getItem().getAmount() -1);
        event.getClickedBlock().setType(Material.GRASS);
    }
}
