package net.senmori.vanillatweaks.enchantment;

import java.lang.reflect.Field;
import java.util.ArrayList;
import net.minecraft.server.v1_11_R1.MinecraftKey;
import net.senmori.vanillatweaks.util.ResourceIdentifier;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.defaults.EnchantCommand;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.inventory.EquipmentSlot;

public class Enchantments {
    public static int START_ID = 80;
    public static final String ENCHANT_DOMAIN = "vanillatweaks";

    //String unlocalizedName, ResourceIdentifier identifier, EnchantmentRarity rarity, EnchantmentTarget itemTarget, EquipmentSlot[] validSlots
    public static final TreeFellerEnchantment TREE_FELLER = new TreeFellerEnchantment("treeFeller", new ResourceIdentifier(ENCHANT_DOMAIN, "tree_feller"),
                                                                                      EnchantmentRarity.RARE, EnchantmentTarget.TOOL, new EquipmentSlot[] {EquipmentSlot.HAND});


    public static void registerEnchantment(VanillaEnchantment enchantment, boolean inject) {
        startAcceptingRegistrations();

        Enchantment.registerEnchantment(enchantment);
        net.minecraft.server.v1_11_R1.Enchantment.enchantments.a(enchantment.getID(), new MinecraftKey(enchantment.getIdentifier().toString()), enchantment.getMinecraftEnchantment());

        if(inject) {
            try {
                Field enchListField = EnchantCommand.class.getDeclaredField("ENCHANTMENT_NAMES");
                enchListField.setAccessible(true); //
                FieldUtils.removeFinalModifier(enchListField, true);
                FieldUtils.writeDeclaredStaticField(EnchantCommand.class, "ENCHANTMENT_NAMES", new ArrayList<String>(), true); // reset list
            } catch(IllegalAccessException | NoSuchFieldException e) {
                Bukkit.getLogger().severe("Error reflecting into EnchantCommand!");
            }
        }
    }

    public static void registerEnchantment(VanillaEnchantment enchantment) {
        registerEnchantment(enchantment, false);
    }

    private static void startAcceptingRegistrations() {
        if(!org.bukkit.enchantments.Enchantment.isAcceptingRegistrations()) {
            try {
                Field registrationField = org.bukkit.enchantments.Enchantment.class.getDeclaredField("acceptingNew");
                registrationField.setAccessible(true);
                registrationField.set(null, Boolean.TRUE);
            } catch(NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    public static void init() {
        //registerEnchantment(TREE_FELLER, true);
    }
}
