package net.senmori.vanillatweaks.enchantment;

import net.minecraft.server.v1_11_R1.Enchantment;
import net.minecraft.server.v1_11_R1.EnchantmentSlotType;
import net.minecraft.server.v1_11_R1.Enchantments;
import net.minecraft.server.v1_11_R1.Entity;
import net.minecraft.server.v1_11_R1.EntityLiving;
import net.minecraft.server.v1_11_R1.EnumItemSlot;
import net.minecraft.server.v1_11_R1.Item;
import net.minecraft.server.v1_11_R1.ItemStack;
import net.minecraft.server.v1_11_R1.Items;
import net.senmori.vanillatweaks.VanillaTweaks;
import net.senmori.vanillatweaks.tasks.TreeFellerTask;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class TreeFellerEnchantment extends AbstractEnchantment {

    public TreeFellerEnchantment(String name, int maxLevel, boolean isTreasureEnchant, Rarity rarity, EnchantmentSlotType slotType, EnumItemSlot[] validSlots) {
        super(name, maxLevel, isTreasureEnchant, rarity, slotType, validSlots);
    }

    @Override
    protected boolean a(Enchantment enchant) {
        return enchant != Enchantments.C && !enchant.d(); // conflicts with Mending and curses
    }

    @Override
    public boolean canEnchant(ItemStack item) {
        return isAxe(item.getItem());
    }

    @Override
    public void a(EntityLiving user, Entity target, int level) {

    }

    @Override
    public void b(EntityLiving user, Entity attacker, int level) {

    }


    public static void activate(Player user, Location location, int level) {
       new TreeFellerTask(VanillaTweaks.getInstance(), 5, location, user, AbstractEnchantment.TREE_FELLER, level);
    }

    private static boolean isAxe(Item item) {
        return item == Items.DIAMOND_AXE ||
               item == Items.GOLDEN_AXE  ||
               item == Items.IRON_AXE    ||
               item == Items.STONE_AXE  ||
               item == Items.WOODEN_AXE;
    }

}
