package net.senmori.vanillatweaks.controllers;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import java.util.List;
import java.util.Random;

import net.senmori.vanillatweaks.VanillaTweaks;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class GrassSpreadController extends TweakController implements Listener {
    private final byte DATA = 15;
    private final ItemStack BONEMEAL = new ItemStack(Material.INK_SACK, 1, DATA);
    private final Random random;
    private final ImmutableList<BlockFace> faces;
    public GrassSpreadController(VanillaTweaks plugin) {
        super(plugin);
        random = new Random(System.currentTimeMillis());
        faces = ImmutableList.copyOf(Lists.newArrayList(BlockFace.values()));
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void spreadGrass(PlayerInteractEvent event) {
        if(event.getAction() != Action.RIGHT_CLICK_BLOCK) return;
        if(!event.hasItem()) return;
        if(event.getItem().getType() != BONEMEAL.getType()) return;
        if(event.getItem().getData().getData() != BONEMEAL.getData().getData()) return;
        if(event.getClickedBlock().getType() != Material.GRASS) return;
        Block block = event.getClickedBlock();
        for(BlockFace face : faces) {
            if(block.getRelative(face).getType() == Material.DIRT && event.getPlayer().isSneaking()) {
                event.setCancelled(true);
                block.getRelative(face).setType(Material.GRASS);
                event.getPlayer().spawnParticle(Particle.VILLAGER_HAPPY, block.getLocation(), 3);
                event.getItem().setAmount(event.getItem().getAmount() -1);
                return;
            }
        }
    }
}
