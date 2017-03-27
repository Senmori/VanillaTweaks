package net.senmori.vanillatweaks.controllers;

import java.util.List;
import net.senmori.vanillatweaks.VanillaTweaks;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.scheduler.BukkitRunnable;

public class ConvertClayController extends TweakController {

    public ConvertClayController(VanillaTweaks plugin) {
        super(plugin);

        if(getPlugin().getTweakConfig().canConvertClay()) {
            getPlugin().getServer().addRecipe(new ShapelessRecipe(new ItemStack(Material.CLAY_BALL, 4)).addIngredient(Material.CLAY));
        }
    }
}
