package net.senmori.vanillatweaks.controllers;

import com.google.common.collect.Lists;
import java.util.List;
import net.senmori.vanillatweaks.VanillaTweaks;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.scheduler.BukkitRunnable;

public class StonePickaxeController extends TweakController {

    private ItemStack STONE = new ItemStack(Material.STONE, 1, (short)0);
    private ItemStack GRANITE = new ItemStack(Material.STONE, 1, (short)1);
    private ItemStack DIORITE = new ItemStack(Material.STONE, 1, (short)3);
    private ItemStack ANDESITE = new ItemStack(Material.STONE, 1, (short)5);

    public StonePickaxeController(VanillaTweaks plugin) {
        super(plugin);

        if(getPlugin().getTweakConfig().doAddStoneToolVariants()) {
            List<ItemStack> ingredients = Lists.newArrayList(STONE, GRANITE, DIORITE, ANDESITE, new ItemStack(Material.FLINT));

            String[][] patterns = new String[][] {{"XXX", " # ", " # "}, {"X", "#", "#"}, {"XX", "X#", " #"}, {"XX", " #", " #"}, {"X", "X", "#"}};
            ItemStack[] items = {new ItemStack(Material.STONE_PICKAXE), new ItemStack(Material.STONE_SPADE), new ItemStack(Material.STONE_AXE), new ItemStack(Material.STONE_HOE), new ItemStack(Material.STONE_SWORD)};

            for(int i = 0; i < patterns.length; i++) {
                for(ItemStack stack : ingredients) {
                    ShapedRecipe recipe = new ShapedRecipe(items[i]);
                    recipe.shape(patterns[i][0], patterns[i][1], patterns[i][2]);
                    recipe.setIngredient('X', stack.getData());
                    recipe.setIngredient('#', Material.STICK);
                    getPlugin().getServer().addRecipe(recipe);
                }
            }
        }
    }

    @Override
    public boolean requiresRestart() {
        return true;
    }

    @Override
    public boolean hasTasks() {
        return false;
    }

    @Override
    public List<BukkitRunnable> getTasks() {
        return null;
    }
}
