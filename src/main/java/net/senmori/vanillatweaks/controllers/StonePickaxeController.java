package net.senmori.vanillatweaks.controllers;

import com.google.common.collect.Lists;
import java.util.List;
import net.senmori.vanillatweaks.VanillaTweaks;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public class StonePickaxeController extends TweakController implements Listener {

    private ItemStack STONE = new ItemStack(Material.STONE, 1, (short)0);
    private ItemStack GRANITE = new ItemStack(Material.STONE, 1, (short)1);
    private ItemStack DIORITE = new ItemStack(Material.STONE, 1, (short)3);
    private ItemStack ANDESITE = new ItemStack(Material.STONE, 1, (short)5);

    private List<ItemStack> ingredients = Lists.newArrayList(STONE, GRANITE, DIORITE, ANDESITE, new ItemStack(Material.FLINT));

    private List<String[]> patterns = Lists.newArrayList(new String[]{"XXX", " # ", " # "}, new String[]{" X ", " # ", " # "},
                                                         new String[]{"XX ", "X# ", " # "}, new String[]{"XX ", " # ", " # "}, new String[]{" X ", " X ", " # "});
    private List<Material> recipeRewards = Lists.newArrayList(Material.STONE_PICKAXE, Material.STONE_SPADE, Material.STONE_AXE, Material.STONE_HOE, Material.STONE_SWORD);

    public StonePickaxeController(VanillaTweaks plugin) {
        super(plugin);

        if(getPlugin().getTweakConfig().doAddStoneToolVariants()) {
            for(int i = 0; i < patterns.size(); i++) {
                for(ItemStack stack : ingredients) {
                    ShapedRecipe recipe = new ShapedRecipe(new ItemStack(recipeRewards.get(i)));
                    recipe.shape(patterns.get(i)[0], patterns.get(i)[1], patterns.get(i)[2]);
                    recipe.setIngredient('X', stack.getData());
                    recipe.setIngredient('#', Material.STICK);
                    getPlugin().getServer().addRecipe(recipe);
                }
            }
        }
    }


    @EventHandler
    public void onCraft(PrepareItemCraftEvent event) {
        if(event.getRecipe() instanceof ShapedRecipe) {
            ShapedRecipe recipe = (ShapedRecipe)event.getRecipe();

            for(String[] patterns : patterns) {

            }
        }
    }
}
