package net.senmori.vanillatweaks.tasks;

import java.util.Random;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.block.Block;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class DrySpongeTask extends BukkitRunnable {
    private static Random rand = new Random(System.nanoTime());

    private int chance;
    private Block sponge;

    public DrySpongeTask(JavaPlugin plugin, Block sponge, int chance) {
        this.sponge = sponge;
        this.chance = chance;

        this.runTaskTimer(plugin, 1L, 10L); // run twice every second
    }
    @Override
    public void run() {
        if(rand.nextInt(chance + 1) == chance && !isDry()) {
            sponge.setData((byte)0);
            sponge.getWorld().playSound(sponge.getLocation(), Sound.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 1.0F, 2.4F);
            this.cancel();
        }
    }

    private boolean isDry() {
        return sponge.getData() == 0;
    }
}
