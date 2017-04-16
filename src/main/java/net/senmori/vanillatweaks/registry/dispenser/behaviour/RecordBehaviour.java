package net.senmori.vanillatweaks.registry.dispenser.behaviour;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Jukebox;
import org.bukkit.inventory.ItemStack;

public class RecordBehaviour implements DispenseBehaviour {

    @Override
    public boolean dispense(Block sourceBlock, ItemStack dispensedItem) {

        org.bukkit.block.Dispenser dispBlock = (org.bukkit.block.Dispenser)sourceBlock.getState();
        org.bukkit.material.Dispenser dispMat = (org.bukkit.material.Dispenser)dispBlock.getData();

        Block against = sourceBlock.getRelative(dispMat.getFacing());

        if(against.getType() == Material.JUKEBOX) {
            Jukebox box = (Jukebox)against.getState();

            if(box.isPlaying()) { // jukebox has a record
                Material playing = box.getPlaying();

                if(dispBlock.getInventory().firstEmpty() >= 0) {
                    box.setPlaying(Material.AIR);
                    dispBlock.getInventory().addItem(new ItemStack(playing));
                }
                else {
                    box.eject();
                }
            }

            box.setPlaying(dispensedItem.getType());
            box.update(); // update jukebox
        }
        return true;
    }
}
