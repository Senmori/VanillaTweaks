package net.senmori.vanillatweaks.registry.dispenser;

import java.util.HashMap;
import java.util.Map;
import net.senmori.vanillatweaks.VanillaTweaks;
import net.senmori.vanillatweaks.registry.Registry;
import net.senmori.vanillatweaks.registry.dispenser.behaviour.DispenseBehaviour;
import net.senmori.vanillatweaks.registry.dispenser.behaviour.WaterBucketBehaviour;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

public final class DispenserRegistry implements Registry<ItemStack, DispenseBehaviour>  {

    private static final Map<ItemStack, DispenseBehaviour> REGISTRY = new HashMap<>();
    public DispenserRegistry(VanillaTweaks plugin) {
        register(new ItemStack(Material.WATER_BUCKET), new WaterBucketBehaviour());
    }

    public boolean isRegistered(ItemStack stack) {
        return REGISTRY.containsKey(stack);
    }

    public void register(ItemStack key, DispenseBehaviour behaviour) {
        REGISTRY.put(key, behaviour);
    }

    public DispenseBehaviour get(ItemStack key) {
        return REGISTRY.get(key);
    }

    public void dispense(ItemStack stack, Block sourceBlock) {
        get(stack).dispense(sourceBlock, stack);
    }
}
