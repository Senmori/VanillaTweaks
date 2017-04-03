package net.senmori.vanillatweaks.registry.dispenser.behaviour;

import net.senmori.vanillatweaks.registry.Behaviour;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

public interface DispenseBehaviour extends Behaviour {


    /**
     * Called when an ItemStack is dispensed from a dispenser<br>
     *
     * @param event - the event that was fired
     */
    void dispense(Block sourceBlock, ItemStack dispensedItem);
}
