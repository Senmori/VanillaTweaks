package net.senmori.vanillatweaks.registry.dispenser.behaviour;

import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

public interface DispenseBehaviour {


    /**
     * Called when an ItemStack is dispensed from a dispenser<br>
     *
     * @param event - the event that was fired
     */
    boolean dispense(Block sourceBlock, ItemStack dispensedItem);
}
