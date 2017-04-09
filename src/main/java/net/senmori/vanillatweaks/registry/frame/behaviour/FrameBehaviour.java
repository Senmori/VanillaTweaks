package net.senmori.vanillatweaks.registry.frame.behaviour;

import net.senmori.vanillatweaks.registry.Behaviour;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public interface FrameBehaviour extends Behaviour {

    /**
     * Called when an item frame is right clicked.<br>
     * @param frame - the {@link ItemFrame} clicked
     * @param whoClicked - the {@link Player} who clicked the item frame
     * @param clickedPosition - where the player clicked this item frame {@link Vector}
     */
    boolean activate(ItemFrame frame, Player whoClicked, Vector clickedPosition);
}
