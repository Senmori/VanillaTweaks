package net.senmori.vanillatweaks.enchantment;

import net.senmori.vanillatweaks.util.ResourceIdentifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.inventory.ItemStack;

public abstract class AbstractEnchantment extends Enchantment {
    public static int START_ID = 101;
    private static final String ENCHANT_DOMAIN = "vanillatweaks";

    public static final TreeFellerEnchantment TREE_FELLER = new TreeFellerEnchantment("tree_feller", ++START_ID, 1);

    private ResourceIdentifier name;
    private int maxLevel;

    public AbstractEnchantment(String name, int id, int maxLevel) {
        super(id);
        this.name = new ResourceIdentifier(ENCHANT_DOMAIN, name);
        this.maxLevel = maxLevel;
    }

    public int getID() {
        return super.getId();
    }

    public int getMaxLevel() {
        return maxLevel;
    }

    public int getStartLevel() {
        return 1;
    }

    public boolean isTreasure() {
        return false;
    }

    public boolean isCursed() {
        return false;
    }

    public String getName() {
        return this.name.getResourceIdentifier();
    }

    public abstract boolean canEnchantItem(ItemStack item);

    public abstract boolean conflictsWith(Enchantment enchant);

    public abstract EnchantmentTarget getItemTarget();

    public ResourceIdentifier getIdentifier() {
        return name;
    }
}
