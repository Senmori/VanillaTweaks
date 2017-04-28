package net.senmori.vanillatweaks.registry.frame.behaviour;

import net.minecraft.server.v1_11_R1.EnumHand;
import net.senmori.vanillatweaks.VanillaTweaks;
import org.bukkit.craftbukkit.v1_11_R1.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_11_R1.inventory.CraftItemStack;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class WrittenBookBehaviour implements FrameBehaviour {

    @Override
    public boolean activate(ItemFrame frame, Player whoClicked, Vector clickedPosition, EquipmentSlot handUsed) {
        ItemStack book = frame.getItem().clone();

        int slot = whoClicked.getInventory().getHeldItemSlot();
        ItemStack held = whoClicked.getInventory().getItem(slot);
        EnumHand hand = handUsed == EquipmentSlot.HAND ? EnumHand.MAIN_HAND : EnumHand.OFF_HAND;
        whoClicked.getInventory().setItem(slot, book);

        new BukkitRunnable() {
            @Override
            public void run() {
                ((CraftPlayer)whoClicked).getHandle().a(CraftItemStack.asNMSCopy(book), hand);
                whoClicked.getInventory().setItem(slot, held);
            }
        }.runTaskLater(VanillaTweaks.getInstance(), 1L);
        return true;
    }
}
