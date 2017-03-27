package net.senmori.vanillatweaks.controllers;

import net.minecraft.server.v1_11_R1.EntityArmorStand;
import net.minecraft.server.v1_11_R1.EntityPlayer;
import net.minecraft.server.v1_11_R1.EnumItemSlot;
import net.senmori.vanillatweaks.VanillaTweaks;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_11_R1.entity.CraftArmorStand;
import org.bukkit.craftbukkit.v1_11_R1.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_11_R1.inventory.CraftItemStack;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

public class QuickSwapController extends TweakController implements Listener {

    public QuickSwapController(VanillaTweaks plugin) {
        super(plugin);
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onSwap(PlayerInteractAtEntityEvent event) {
        if(!getPlugin().getTweakConfig().canQuicklySwap()) return;
        if(event.getRightClicked().getType() != EntityType.ARMOR_STAND) return;
        if(!event.getPlayer().isSneaking()) return;
        event.setCancelled(true);
        Player player = event.getPlayer();
        ArmorStand stand = (ArmorStand)event.getRightClicked();

        swapSlot(player, stand, EnumItemSlot.HEAD);
        swapSlot(player, stand, EnumItemSlot.CHEST);
        swapSlot(player, stand, EnumItemSlot.LEGS);
        swapSlot(player, stand, EnumItemSlot.FEET);
        if(getPlugin().getTweakConfig().canSwapOffhand()) {
            swapSlot(player, stand, EnumItemSlot.OFFHAND);
        }
    }

    private void swapSlot(Player player, ArmorStand stand, EnumItemSlot slot) {
        EntityPlayer entPlayer = ((CraftPlayer)player).getHandle();
        EntityArmorStand armor = ((CraftArmorStand)stand).getHandle();

        net.minecraft.server.v1_11_R1.ItemStack playerItem = entPlayer.getEquipment(slot);
        net.minecraft.server.v1_11_R1.ItemStack armorItem = armor.getEquipment(slot);

        entPlayer.setSlot(slot, armorItem);
        armor.setSlot(slot, playerItem);
        System.out.println("Swapped " + playerItem.getName() + " with " + armorItem.getName() + " on " + player.getName());
    }
}