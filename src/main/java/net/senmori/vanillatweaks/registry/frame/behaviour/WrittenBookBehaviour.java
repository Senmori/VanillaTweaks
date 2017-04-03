package net.senmori.vanillatweaks.registry.frame.behaviour;

import net.minecraft.server.v1_11_R1.EnumHand;
import org.bukkit.craftbukkit.v1_11_R1.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_11_R1.inventory.CraftItemStack;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public class WrittenBookBehaviour implements FrameBehaviour {

    @Override
    public void activate(ItemFrame frame, Player whoClicked, Vector clickedPosition) {
        ItemStack book = frame.getItem().clone();

        ItemStack held = whoClicked.getInventory().getItemInMainHand();

        whoClicked.getInventory().setItemInMainHand(book);
        ((CraftPlayer)whoClicked).getHandle().a(CraftItemStack.asNMSCopy(book), EnumHand.MAIN_HAND);
        whoClicked.getInventory().setItemInMainHand(held);
    }
}
