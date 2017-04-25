package net.senmori.vanillatweaks.enchantment;

import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import java.util.Locale;
import net.minecraft.server.v1_11_R1.EnchantmentSlotType;
import net.minecraft.server.v1_11_R1.EnumItemSlot;
import net.senmori.vanillatweaks.util.ResourceIdentifier;
import org.bukkit.craftbukkit.v1_11_R1.CraftEquipmentSlot;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.inventory.EquipmentSlot;

public abstract class VanillaEnchantment extends Enchantment {

    private ResourceIdentifier identifier;
    private EnchantmentRarity rarity;
    private EnchantmentTarget itemTarget;
    private EquipmentSlot[] validSlots;
    private String unlocalizedName;
    private MinecraftEnchantment mcEnchant;

    public VanillaEnchantment(String unlocalizedName, ResourceIdentifier identifier, EnchantmentRarity rarity, EnchantmentTarget itemTarget, EquipmentSlot[] validSlots) {
        super(++Enchantments.START_ID);
        this.identifier = identifier;
        this.rarity = rarity;
        this.itemTarget = itemTarget;
        this.validSlots = validSlots;
        this.unlocalizedName = unlocalizedName;
        this.mcEnchant = createMinecraftEnchantment(this);
    }

    public int getID() {
        return super.getId();
    }

    public String getName() {
        return identifier.getResourceIdentifier().toUpperCase(Locale.ENGLISH);
    }

    private String getUnlocalizedName() {
        return unlocalizedName;
    }

    public ResourceIdentifier getIdentifier() {
        return this.identifier;
    }

    public EnchantmentRarity getRarity() {
        return rarity;
    }

    public EnchantmentTarget getItemTarget() {
        return itemTarget;
    }

    public EquipmentSlot[] getValidSlots() {
        return validSlots;
    }

    public MinecraftEnchantment getMinecraftEnchantment() {
        return mcEnchant;
    }

    private static MinecraftEnchantment createMinecraftEnchantment(VanillaEnchantment vEnchant) {
        net.minecraft.server.v1_11_R1.Enchantment.Rarity rarity = net.minecraft.server.v1_11_R1.Enchantment.Rarity.values()[vEnchant.getRarity().ordinal()];
        EnumItemSlot[] slots = new EnumItemSlot[vEnchant.getValidSlots().length];
        for(int i = 0; i < slots.length; i++) {
            slots[i] = CraftEquipmentSlot.getNMS(vEnchant.getValidSlots()[i]);
        }
        EnchantmentSlotType type = MinecraftEnchantment.typeMap.get(vEnchant.getItemTarget());
        return new MinecraftEnchantment(vEnchant.getUnlocalizedName(), rarity, type, slots);
    }

    private static class MinecraftEnchantment extends net.minecraft.server.v1_11_R1.Enchantment {
        public static final BiMap<EnchantmentTarget, EnchantmentSlotType> typeMap = new ImmutableBiMap.Builder<EnchantmentTarget, EnchantmentSlotType>()
                .put(EnchantmentTarget.ALL, EnchantmentSlotType.ALL)
                .put(EnchantmentTarget.ARMOR, EnchantmentSlotType.ARMOR)
                .put(EnchantmentTarget.ARMOR_FEET, EnchantmentSlotType.ARMOR_FEET)
                .put(EnchantmentTarget.ARMOR_HEAD, EnchantmentSlotType.ARMOR_HEAD)
                .put(EnchantmentTarget.ARMOR_LEGS, EnchantmentSlotType.ARMOR_LEGS)
                .put(EnchantmentTarget.ARMOR_TORSO, EnchantmentSlotType.ARMOR_CHEST)
                .put(EnchantmentTarget.WEAPON, EnchantmentSlotType.WEAPON)
                .put(EnchantmentTarget.TOOL, EnchantmentSlotType.DIGGER)
                .put(EnchantmentTarget.FISHING_ROD, EnchantmentSlotType.FISHING_ROD)
                .put(EnchantmentTarget.BOW, EnchantmentSlotType.BOW)
                .put(EnchantmentTarget.BREAKABLE, EnchantmentSlotType.WEARABLE)
                .build();


        protected MinecraftEnchantment(Rarity rarity, EnchantmentSlotType itemTarget, EnumItemSlot[] validSlots) {
            super(rarity, itemTarget, validSlots);
        }

        protected MinecraftEnchantment(String unlocalizedName, Rarity rarity, EnchantmentSlotType itemTarget, EnumItemSlot[] validSlots) {
            this(rarity, itemTarget, validSlots);
            this.c(unlocalizedName);
        }
    }
}
