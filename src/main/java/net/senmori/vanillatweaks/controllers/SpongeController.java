package net.senmori.vanillatweaks.controllers;

import net.senmori.vanillatweaks.VanillaTweaks;
import net.senmori.vanillatweaks.config.ConfigOption;
import net.senmori.vanillatweaks.tasks.DrySpongeTask;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class SpongeController extends TweakController implements Listener {

    public SpongeController(VanillaTweaks plugin) {
        super(plugin);
        getPlugin().getServer().getPluginManager().registerEvents(this, getPlugin());
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        if(event.getBlock().getType() != Material.SPONGE) return;
        if(event.getBlock().getWorld().getEnvironment() != World.Environment.NETHER) return;
        if(! ConfigOption.SPONGE_DRY_ENABLED.getValue()) return;

        if(isWet(event.getBlockPlaced())) {
            new DrySpongeTask(getPlugin(), event.getBlockPlaced(), ConfigOption.SPONGE_DRY_CHANCE.getValue());
        }
    }


    @SuppressWarnings("deprecation")
    private boolean isWet(Block block) {
        return block.getData() == 1;
    }
}
