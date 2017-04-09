package net.senmori.vanillatweaks.registry.dispenser;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import net.senmori.vanillatweaks.VanillaTweaks;
import net.senmori.vanillatweaks.registry.Registry;
import net.senmori.vanillatweaks.registry.RegistryItemStack;
import net.senmori.vanillatweaks.registry.dispenser.behaviour.DispenseBehaviour;
import net.senmori.vanillatweaks.registry.dispenser.behaviour.WaterBucketBehaviour;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

public final class DispenserRegistry implements Registry<DispenseBehaviour>  {

    private static final Map<RegistryItemStack, DispenseBehaviour> REGISTRY = new HashMap<>();
    public DispenserRegistry(VanillaTweaks plugin) {
        register(new ItemStack(Material.WATER_BUCKET), true, new WaterBucketBehaviour());
    }

    public boolean isRegistered(ItemStack stack) {
        Optional<RegistryItemStack> opt = REGISTRY.keySet().stream().findFirst().filter(i -> (i.getStack().getType() == stack.getType()));

        return opt.map(registryItemStack -> registryItemStack.doIgnoreMetadata() || registryItemStack.getStack().equals(stack)).orElse(false);
    }

    public void register(ItemStack stack, boolean ignoreMetadata, DispenseBehaviour behaviour) {
        if(!isRegistered(stack)) {
            REGISTRY.put(new RegistryItemStack(stack, ignoreMetadata), behaviour);
        }
    }

    @Override
    public void register(Material material, DispenseBehaviour behaviour) {
        register(new ItemStack(material), true, behaviour);
    }

    public DispenseBehaviour get(ItemStack key) {
        return REGISTRY.get(key);
    }

    public boolean dispense(ItemStack stack, Block sourceBlock) {
        return get(stack).dispense(sourceBlock, stack);
    }
}
