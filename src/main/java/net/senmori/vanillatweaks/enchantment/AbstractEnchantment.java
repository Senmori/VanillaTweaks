package net.senmori.vanillatweaks.enchantment;

import net.minecraft.server.v1_11_R1.Enchantment;
import net.minecraft.server.v1_11_R1.EnchantmentSlotType;
import net.minecraft.server.v1_11_R1.Entity;
import net.minecraft.server.v1_11_R1.EntityLiving;
import net.minecraft.server.v1_11_R1.EnumItemSlot;
import net.minecraft.server.v1_11_R1.ItemStack;
import net.senmori.vanillatweaks.util.ResourceIdentifier;

public abstract class AbstractEnchantment extends Enchantment {
    public static int START_ID = 80;
    public static final String ENCHANT_DOMAIN = "vanillatweaks";

    public static final TreeFellerEnchantment TREE_FELLER = new TreeFellerEnchantment("tree_feller", 1, true, Rarity.VERY_RARE, EnchantmentSlotType.DIGGER, new EnumItemSlot[]{EnumItemSlot.MAINHAND});

    private ResourceIdentifier name;
    private int maxLevel;
    private boolean isTreasure;

    protected AbstractEnchantment(Rarity rarity, EnchantmentSlotType itemTarget, EnumItemSlot[] validSlots) {
        super(rarity, itemTarget, validSlots);
    }

    public AbstractEnchantment(String name, int maxLevel, boolean isTreasureEnchant, Rarity rarity, EnchantmentSlotType slotType, EnumItemSlot[] validSlots) {
        this(rarity, slotType, validSlots);

        this.name = new ResourceIdentifier(ENCHANT_DOMAIN, name);
        this.maxLevel = maxLevel;
        this.isTreasure = isTreasureEnchant;
    }

    public int getMaxLevel() {
        return maxLevel;
    }

    public boolean isTreasure() {
        return isTreasure;
    }

    public ResourceIdentifier getIdentifier() {
        return name;
    }

    public final boolean conflictsWith(Enchantment enchant) {
        return this.a(enchant) && enchant.c(this);
    }

    protected abstract boolean a(Enchantment enchant);

    public abstract boolean canEnchant(ItemStack item);

    /**
     * This method is called when a mob is damaged with an item that has this enchantment on it.
     * @param user The user who used this enchantment
     * @param target The entity that was damaged
     * @param level the level of this enchantment
     */
    public abstract void a(EntityLiving user, Entity target, int level);

    /**
     * Whenever an entity that has this enchantment on one of its associated items is damaged this method will be
     * called.
     * @param user The entity that was attacked
     * @param attacker The user who damaged the Entity
     * @param level the level of this enchantment
     */
    public abstract void b(EntityLiving user, Entity attacker, int level);
}
