package net.senmori.vanillatweaks.controllers;

import net.minecraft.server.v1_12_R1.ItemStack;
import net.minecraft.server.v1_12_R1.Items;
import net.senmori.vanillatweaks.VanillaTweaks;
import net.senmori.vanillatweaks.config.ConfigOption;

public class DragonBreathController extends TweakController {
    public DragonBreathController(VanillaTweaks plugin) {
        super(plugin);

        if(ConfigOption.DRAGON_BREATH_FIX.getValue()) {
            Items.DRAGON_BREATH.b(ItemStack.a);
        }
    }
}
