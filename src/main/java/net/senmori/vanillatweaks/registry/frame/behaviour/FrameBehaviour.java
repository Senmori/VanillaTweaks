package net.senmori.vanillatweaks.registry.frame.behaviour;

import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.util.Vector;

public interface FrameBehaviour {

    /**
     * Called when an item frame is right clicked.<br>
     *
     * @param frame - the {@link ItemFrame} clicked
     * @param whoClicked - the {@link Player} who clicked the item frame
     * @param clickedPosition - where the player clicked this item frame {@link Vector}
     */
    boolean activate(ItemFrame frame, Player whoClicked, Vector clickedPosition, EquipmentSlot handUsed);
}
