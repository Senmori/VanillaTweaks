package net.senmori.vanillatweaks.controllers;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import java.util.List;
import java.util.Map;
import net.senmori.vanillatweaks.VanillaTweaks;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public class StonePickaxeController extends TweakController {
    private final Map<Material, String[]> recipeMap;
    private final List<ItemStack> ingredients;
    public StonePickaxeController(VanillaTweaks plugin) {
        super(plugin);

        recipeMap = ImmutableMap.<Material,String[]>builder()
                .put(Material.STONE_PICKAXE, new String[]{"XXX", " # ", " # "})
                .put(Material.STONE_SPADE, new String[]{" X ", " # ", " # "})
                .put(Material.STONE_AXE, new String[]{"XX ", "X# ", " # "})
                .put(Material.STONE_HOE, new String[]{"XX ", " # ", " # "})
                .put(Material.STONE_SWORD, new String[]{" X ", " X ", " # "})
                .build();
        ingredients = ImmutableList.<ItemStack>builder()
                .add(new ItemStack(Material.STONE, 1, (short)0))
                .add(new ItemStack(Material.STONE, 1, (short)1))
                .add(new ItemStack(Material.STONE, 1, (short)3))
                .add(new ItemStack(Material.STONE, 1, (short)5))
                .add(new ItemStack(Material.FLINT))
                .build();

        if(getPlugin().getTweakConfig().doAddStoneToolVariants()) {
            for(ItemStack stack : ingredients) {
                recipeMap.forEach( (mat, shape) -> {
                    ShapedRecipe recipe = new ShapedRecipe(new ItemStack(mat));
                    recipe.shape(shape[0], shape[1], shape[2]);
                    recipe.setIngredient('X', stack.getData());
                    recipe.setIngredient('#', Material.STICK);
                    getPlugin().getServer().addRecipe(recipe);
                });
            }
        }
    }
}
