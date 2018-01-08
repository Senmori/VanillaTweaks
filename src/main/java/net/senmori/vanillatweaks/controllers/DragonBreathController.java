package net.senmori.vanillatweaks.controllers;

import net.minecraft.server.v1_12_R1.ItemStack;
import net.minecraft.server.v1_12_R1.Items;
import net.senmori.vanillatweaks.VanillaTweaks;

public class DragonBreathController extends TweakController {
    public DragonBreathController(VanillaTweaks plugin) {
        super(plugin);

        if(getSettings().DRAGON_BREATH_FIX.getValue()) {
            Items.DRAGON_BREATH.b(ItemStack.a);
        }
    }
}
