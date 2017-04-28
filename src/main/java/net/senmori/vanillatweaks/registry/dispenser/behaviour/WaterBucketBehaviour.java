package net.senmori.vanillatweaks.registry.dispenser.behaviour;

import net.senmori.vanillatweaks.VanillaTweaks;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.Cauldron;
import org.bukkit.scheduler.BukkitRunnable;

@SuppressWarnings("deprecation")
public class WaterBucketBehaviour implements DispenseBehaviour {

    @Override
    public boolean dispense(Block sourceBlock, ItemStack dispensedItem) {
        org.bukkit.block.Dispenser dispBlock = (org.bukkit.block.Dispenser)sourceBlock.getState();
        org.bukkit.material.Dispenser dispMat = (org.bukkit.material.Dispenser)dispBlock.getData();

        Block cBlock = sourceBlock.getRelative(dispMat.getFacing());

        if(cBlock.getType() == Material.CAULDRON) {
            Cauldron caul = (Cauldron)cBlock.getState().getData();
            if(caul.isFull()) {
                return false;
            }
            cBlock.setData((byte)3); // full
            cBlock.getState().update();

            new BukkitRunnable() {
                @Override
                public void run() {
                    int slot = dispBlock.getInventory().first(dispensedItem.getType());
                    dispBlock.getInventory().getItem(slot).setType(Material.BUCKET);
                }
            }.runTaskLater(VanillaTweaks.getInstance(), 1L);
            return true;
        }
        return false;
    }
}
