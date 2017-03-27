package net.senmori.vanillatweaks.controllers;

import net.minecraft.server.v1_11_R1.EntityArmorStand;
import net.minecraft.server.v1_11_R1.EntityPlayer;
import net.minecraft.server.v1_11_R1.EnumItemSlot;
import net.senmori.vanillatweaks.VanillaTweaks;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_11_R1.entity.CraftArmorStand;
import org.bukkit.craftbukkit.v1_11_R1.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_11_R1.inventory.CraftItemStack;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.ItemStack;

public class QuickSwapController extends TweakController implements Listener {

    public QuickSwapController(VanillaTweaks plugin) {
        super(plugin);
    }

    @EventHandler
    public void onSwap(PlayerInteractAtEntityEvent event) {
        if(!getPlugin().getTweakConfig().canQuicklySwap()) return;
        if(event.getRightClicked().getType() != EntityType.ARMOR_STAND) return;
        if(!event.getPlayer().isSneaking()) return;
        System.out.println("TESTING 123");
        System.err.println("TESTING 123 ERROR");
        event.setCancelled(true);
        ArmorStand stand = (ArmorStand)event.getRightClicked();
        swapSlot(event.getPlayer(), stand, EnumItemSlot.HEAD);
        swapSlot(event.getPlayer(), stand, EnumItemSlot.CHEST);
        swapSlot(event.getPlayer(), stand, EnumItemSlot.LEGS);
        swapSlot(event.getPlayer(), stand, EnumItemSlot.FEET);
        if(getPlugin().getTweakConfig().canSwapOffhand()) {
            swapSlot(event.getPlayer(), stand, EnumItemSlot.OFFHAND);
        }
    }

    private void swapSlot(Player player, ArmorStand stand, EnumItemSlot slot) {
        EntityPlayer p = ((CraftPlayer)player).getHandle();
        EntityArmorStand armorStand = ((CraftArmorStand)stand).getHandle();
        ItemStack pItem = CraftItemStack.asBukkitCopy(p.getEquipment(slot));
        ItemStack aItem = CraftItemStack.asBukkitCopy(armorStand.getEquipment(slot));

        p.setEquipment(slot, CraftItemStack.asNMSCopy(aItem));
        armorStand.setEquipment(slot, CraftItemStack.asNMSCopy(pItem));
    }
}
