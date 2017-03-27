package net.senmori.vanillatweaks.controllers;

import net.minecraft.server.v1_11_R1.ItemStack;
import net.minecraft.server.v1_11_R1.Items;
import net.senmori.vanillatweaks.VanillaTweaks;

public class DragonBreathController extends TweakController {
    public DragonBreathController(VanillaTweaks plugin) {
        super(plugin);

        if(plugin.getTweakConfig().fixDragonBreath()) {
            Items.DRAGON_BREATH.b(ItemStack.a);
        }
    }
}
