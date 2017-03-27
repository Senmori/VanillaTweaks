package net.senmori.vanillatweaks.controllers;

import static com.google.common.collect.ImmutableSet.of;
import com.google.common.collect.ImmutableSet;
import java.util.List;
import net.minecraft.server.v1_11_R1.Item;
import net.minecraft.server.v1_11_R1.Items;
import net.senmori.vanillatweaks.VanillaTweaks;
import org.bukkit.Material;
import org.bukkit.scheduler.BukkitRunnable;

public class StackableItemsController extends TweakController {

    public StackableItemsController(VanillaTweaks plugin) {
        super(plugin);

        ImmutableSet.of(Items.MINECART, Items.CHEST_MINECART, Items.COMMAND_BLOCK_MINECART, Items.FURNACE_MINECART, Items.HOPPER_MINECART, Items.TNT_MINECART)
                            .forEach(item -> item.d(getPlugin().getTweakConfig().getMinecartStackSize()));
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
