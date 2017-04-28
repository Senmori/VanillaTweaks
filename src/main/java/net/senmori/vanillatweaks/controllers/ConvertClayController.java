package net.senmori.vanillatweaks.controllers;

import net.senmori.vanillatweaks.VanillaTweaks;
import net.senmori.vanillatweaks.config.ConfigOption;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;

public class ConvertClayController extends TweakController {

    public ConvertClayController(VanillaTweaks plugin) {
        super(plugin);

        if(ConfigOption.CONVERT_CLAY.getValue()) {
            getPlugin().getServer().addRecipe(new ShapelessRecipe(new ItemStack(Material.CLAY_BALL, 4)).addIngredient(Material.CLAY));
        }
    }
}
