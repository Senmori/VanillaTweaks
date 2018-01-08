package net.senmori.vanillatweaks.controllers;

import net.senmori.vanillatweaks.VanillaTweaks;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;

public class ConvertClayController extends TweakController {

    public ConvertClayController(VanillaTweaks plugin) {
        super(plugin);

        if(getSettings().CONVERT_CLAY.getValue()) {
            getPlugin().getServer().addRecipe(new ShapelessRecipe(plugin.newKey("clay-conversion"), new ItemStack(Material.CLAY_BALL, 4)).addIngredient(Material.CLAY));
        }
    }
}
