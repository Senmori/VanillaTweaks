package net.senmori.vanillatweaks.dispenser;

import org.bukkit.event.block.BlockDispenseEvent;

public interface DispenseBehaviour {


    /**
     * Called when an ItemStack is dispensed from a dispenser<br>
     *
     * @param event - the event that was fired
     */
    void dispense(BlockDispenseEvent event);
}
