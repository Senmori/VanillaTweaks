package net.senmori.vanillatweaks.registry.dispenser.behaviour;

import net.senmori.vanillatweaks.VanillaTweaks;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.material.Cauldron;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionType;
import org.bukkit.scheduler.BukkitRunnable;

@SuppressWarnings("deprecation")
public class FillBottleBehaviour implements DispenseBehaviour {

    @Override
    public boolean dispense(Block sourceBlock, ItemStack dispensedItem) {

        org.bukkit.block.Dispenser dispBlock = (org.bukkit.block.Dispenser)sourceBlock.getState();
        org.bukkit.material.Dispenser dispMat = (org.bukkit.material.Dispenser)dispBlock.getData();

        Block cBlock = sourceBlock.getRelative(dispMat.getFacing());

        if(cBlock.getType() == Material.CAULDRON) {
            Cauldron caul = (Cauldron)cBlock.getState().getData();
            byte level = cBlock.getData();
            if(level <= 0) {
                return false;
            }
            cBlock.setData((level <= 0 ? 0 : (--level)));
            cBlock.getState().update();

            new BukkitRunnable() {
                @Override
                public void run() {
                    for(int i = 0; i < dispBlock.getInventory().getSize(); i++) {
                        ItemStack stack = dispBlock.getInventory().getItem(i);
                        if(stack != null && stack.getType() == dispensedItem.getType()) {
                            //check for more than 1
                            if(stack.getAmount() >= 1) {
                                stack.setAmount(stack.getAmount() - 1);
                                ItemStack potion = new ItemStack(Material.POTION);
                                PotionMeta meta = (PotionMeta)potion.getItemMeta();
                                meta.setBasePotionData(new PotionData(PotionType.WATER, false, false));
                                potion.setItemMeta(meta);
                                dispBlock.getInventory().addItem(potion); // water
                            }
                            return;
                        }
                    }
                }
            }.runTaskLater(VanillaTweaks.getInstance(), 1L);
            return true;
        }
        return false;
    }
}
