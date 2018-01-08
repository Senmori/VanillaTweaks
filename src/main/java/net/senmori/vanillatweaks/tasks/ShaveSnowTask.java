package net.senmori.vanillatweaks.tasks;

import net.minecraft.server.v1_12_R1.BlockPosition;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_12_R1.CraftWorld;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class ShaveSnowTask extends BukkitRunnable {

    private Block block;
    public ShaveSnowTask(JavaPlugin plugin, Block block) {
        this.block = block;

        this.runTaskLater(plugin, 1L);
    }
    @Override
    public void run() {
        if(block.getType() == Material.SNOW_BLOCK) {
            block.setType(Material.SNOW);
            block.setData((byte)6); // one less than a full block
            block.getState().update();
        } else {
            byte layers = block.getData();
            if(layers < 1) {
                ((CraftWorld)block.getWorld()).getHandle().setAir(new BlockPosition(block.getX(), block.getY(), block.getZ())); // set to air
            } else {
                block.setData(--layers);
                block.getWorld().playSound(block.getLocation(), Sound.BLOCK_SNOW_BREAK, org.bukkit.SoundCategory.BLOCKS, 1.0f, 1.0f);
                block.getState().update();
            }
        }
        this.cancel();
    }
}
