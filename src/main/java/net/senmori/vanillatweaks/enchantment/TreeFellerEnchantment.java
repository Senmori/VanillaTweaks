package net.senmori.vanillatweaks.enchantment;

import net.senmori.vanillatweaks.VanillaTweaks;
import net.senmori.vanillatweaks.tasks.TreeFellerTask;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Player;

public class TreeFellerEnchantment extends AbstractEnchantment {

    public TreeFellerEnchantment(String name, int id, int maxLevel) {
        super(name, id, maxLevel);
    }

    @Override
    public boolean canEnchantItem(org.bukkit.inventory.ItemStack item) {
        return isAxe(item.getType());
    }

    @Override
    public boolean conflictsWith(org.bukkit.enchantments.Enchantment enchant) {
        return enchant == Enchantment.MENDING || enchant.isCursed();
    }

    @Override
    public EnchantmentTarget getItemTarget() {
        return EnchantmentTarget.TOOL;
    }

    public boolean isTreasure() {
        return true;
    }

    public static void activate(Player user, Location location, int level) {
        new TreeFellerTask(VanillaTweaks.getInstance(), VanillaTweaks.getInstance().getTweakConfig().getTreeFellerPeriod(), location, user, AbstractEnchantment.TREE_FELLER, level);
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
