package net.senmori.vanillatweaks.registry.dispenser;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import net.senmori.vanillatweaks.VanillaTweaks;
import net.senmori.vanillatweaks.registry.Registry;
import net.senmori.vanillatweaks.registry.RegistryItemStack;
import net.senmori.vanillatweaks.registry.dispenser.behaviour.DispenseBehaviour;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

public final class DispenserRegistry implements Registry<DispenseBehaviour>  {

    private static final Map<RegistryItemStack, DispenseBehaviour> REGISTRY = new HashMap<>();


    public DispenserRegistry(VanillaTweaks plugin) {
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
        RegistryItemStack stack = getRegistryItemStack(key);

        if(stack != null) {
            return REGISTRY.get(stack);
        }
        return null;
    }

    private RegistryItemStack getRegistryItemStack(ItemStack key) {
        if(key == null || !isRegistered(key)) {
            return null;
        }
        Optional<RegistryItemStack> opt = REGISTRY.keySet().stream().findFirst().filter(i -> (i.getStack().getType() == key.getType()));

        if(opt.isPresent()) {
            if(!opt.get().doIgnoreMetadata()) {
                return opt.get().getStack().equals(key) ? opt.get() : null;
            }
            return opt.get();
        }
        return null;
    }

    public boolean dispense(ItemStack stack, Block sourceBlock) {
        DispenseBehaviour beh = get(stack);
        if(beh != null) {
            return beh.dispense(sourceBlock, stack);
        }
        return false;
    }
}
