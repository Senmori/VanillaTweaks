package net.senmori.vanillatweaks.registry.frame.behaviour;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import net.minecraft.server.v1_11_R1.PacketDataSerializer;
import net.minecraft.server.v1_11_R1.PacketPlayOutCustomPayload;
import net.senmori.vanillatweaks.VanillaTweaks;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_11_R1.entity.CraftPlayer;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class WrittenBookBehaviour implements FrameBehaviour {

    @Override
    public void activate(ItemFrame frame, Player whoClicked, Vector clickedPosition) {
        Bukkit.broadcastMessage("whoClicked: " + whoClicked.getName());
        ItemStack book = frame.getItem().clone();

        int slot = whoClicked.getInventory().getHeldItemSlot();
        ItemStack held = whoClicked.getInventory().getItem(slot);
        Bukkit.broadcastMessage("HeldItem: " + whoClicked.getInventory().getItem(slot).getType().name().toLowerCase());

        whoClicked.getInventory().setItem(slot, book);
        ByteBuf buf = Unpooled.buffer();
        buf.setByte(0, (byte)0);
        buf.writerIndex(1);

        new BukkitRunnable() {
            @Override
            public void run() {
                PacketPlayOutCustomPayload packet = new PacketPlayOutCustomPayload("MC|BOpen", new PacketDataSerializer(buf));
                ((CraftPlayer)whoClicked).getHandle().playerConnection.sendPacket(packet);
                whoClicked.getInventory().setItem(slot, held);
                Bukkit.broadcastMessage("OldItem: " + held.getType().name().toLowerCase());
            }
        }.runTaskLater(VanillaTweaks.getInstance(), 1L);
    }
}
