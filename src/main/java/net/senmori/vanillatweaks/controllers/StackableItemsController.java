package net.senmori.vanillatweaks.controllers;

import com.google.common.collect.ImmutableSet;
import net.minecraft.server.v1_12_R1.Items;
import net.senmori.vanillatweaks.VanillaTweaks;

public class StackableItemsController extends TweakController {

    public StackableItemsController(VanillaTweaks plugin) {
        super(plugin);

        ImmutableSet.of(Items.MINECART, Items.CHEST_MINECART, Items.COMMAND_BLOCK_MINECART, Items.FURNACE_MINECART, Items.HOPPER_MINECART, Items.TNT_MINECART)
                            .forEach(item -> item.d(getSettings().MINECART.STACK_SIZE.getValue().intValue()));
    }
}
