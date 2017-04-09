package net.senmori.vanillatweaks.registry.frame.behaviour;

import net.minecraft.server.v1_11_R1.EnumHand;
import net.senmori.vanillatweaks.VanillaTweaks;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_11_R1.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_11_R1.inventory.CraftItemStack;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class WrittenBookBehaviour implements FrameBehaviour {

    @Override
    public boolean activate(ItemFrame frame, Player whoClicked, Vector clickedPosition) {
        Bukkit.broadcastMessage("whoClicked: " + whoClicked.getName());
        ItemStack book = frame.getItem().clone();


        int slot = whoClicked.getInventory().getHeldItemSlot();
        ItemStack held = whoClicked.getInventory().getItem(slot);
        EnumHand hand = whoClicked.getInventory().getItemInMainHand().equals(held) ? EnumHand.MAIN_HAND : EnumHand.OFF_HAND;
        Bukkit.broadcastMessage("HeldItem: " + whoClicked.getInventory().getItem(slot).getType().name().toLowerCase());

        whoClicked.getInventory().setItem(slot, book);


        new BukkitRunnable() {
            @Override
            public void run() {
                ((CraftPlayer)whoClicked).getHandle().a(CraftItemStack.asNMSCopy(book), hand);
                whoClicked.getInventory().setItem(slot, held);
                Bukkit.broadcastMessage("OldItem: " + held.getType().name().toLowerCase());
            }
        }.runTaskLater(VanillaTweaks.getInstance(), 1L);

        return true;
    }
}
