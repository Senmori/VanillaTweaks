package net.senmori.vanillatweaks.enchantment;

import net.senmori.vanillatweaks.util.ResourceIdentifier;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

public class TreeFellerEnchantment extends VanillaEnchantment {

    public TreeFellerEnchantment(String unlocalizedName, ResourceIdentifier identifier, EnchantmentRarity rarity, EnchantmentTarget itemTarget, EquipmentSlot[] validSlots) {
        super(unlocalizedName, identifier, rarity, itemTarget, validSlots);
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public int getStartLevel() {
        return 1;
    }

    @Override
    public boolean isTreasure() {
        return true;
    }

    @Override
    public boolean isCursed() {
        return false;
    }

    @Override
    public boolean conflictsWith(Enchantment enchantment) {
        return enchantment == Enchantment.MENDING || enchantment.isCursed();
    }

    @Override
    public boolean canEnchantItem(ItemStack itemStack) {
        return isAxe(itemStack.getType());
    }

    private static boolean isAxe(Material material) {
        switch(material) {
            case DIAMOND_AXE:
            case GOLD_AXE:
            case IRON_AXE:
            case STONE_AXE:
            case WOOD_AXE:
                return true;
            default:
                return false;
        }
    }
}
