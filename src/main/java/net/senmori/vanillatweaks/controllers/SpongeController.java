package net.senmori.vanillatweaks.controllers;

import net.senmori.vanillatweaks.VanillaTweaks;
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
        if(!getPlugin().getTweakConfig().canSpongeDryInNether()) return;

        if(isWet(event.getBlockPlaced())) {
            new DrySpongeTask(getPlugin(), event.getBlockPlaced(), getPlugin().getTweakConfig().getSpongeDryChance());
        }
    }


    @SuppressWarnings("deprecation")
    private boolean isWet(Block block) {
        return block.getData() == 1;
    }
}
