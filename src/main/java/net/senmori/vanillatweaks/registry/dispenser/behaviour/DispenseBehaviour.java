package net.senmori.vanillatweaks.registry.dispenser.behaviour;

import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

public interface DispenseBehaviour {


    /**
     * Called when an ItemStack is dispensed from a dispenser<br>
     *
     * @param sourceBlock the dispenser the ItemStack was dispensed from
     * @param dispensedItem the dispensed ItemStack
     */
    boolean dispense(Block sourceBlock, ItemStack dispensedItem);
}
