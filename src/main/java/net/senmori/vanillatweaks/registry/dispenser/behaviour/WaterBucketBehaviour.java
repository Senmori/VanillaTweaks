package net.senmori.vanillatweaks.registry.dispenser.behaviour;

import net.senmori.vanillatweaks.VanillaTweaks;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.Cauldron;
import org.bukkit.scheduler.BukkitRunnable;

public class WaterBucketBehaviour implements DispenseBehaviour {

    @Override
    public void dispense(Block sourceBlock, ItemStack dispensedItem) {

        org.bukkit.block.Dispenser dispBlock = (org.bukkit.block.Dispenser)sourceBlock.getState();
        org.bukkit.material.Dispenser dispMat = (org.bukkit.material.Dispenser)dispBlock.getData();

        Block cBlock = sourceBlock.getRelative(dispMat.getFacing());

        if(cBlock.getType() == Material.CAULDRON && dispensedItem.getType() == Material.WATER_BUCKET && cBlock.getData() < 3) {
            Cauldron caul = (Cauldron)cBlock.getState().getData();
            if(!caul.isFull()) {
                cBlock.setData((byte)3);
                cBlock.getState().update();

                new BukkitRunnable() {
                    @Override
                    public void run() {
                        for(int i = 0; i < dispBlock.getInventory().getSize(); i++) {
                            ItemStack stack = dispBlock.getInventory().getItem(i);
                            if(stack != null && stack.getType() == Material.WATER_BUCKET) {
                                stack.setType(Material.BUCKET);
                                return;
                            }
                        }
                    }
                }.runTaskLater(VanillaTweaks.getInstance(), 1L);
            }
        }
    }
}
