package net.senmori.vanillatweaks.registry.dispenser;

import net.senmori.vanillatweaks.VanillaTweaks;
import net.senmori.vanillatweaks.registry.Registry;
import net.senmori.vanillatweaks.registry.dispenser.behaviour.DispenseBehaviour;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

public final class DispenserRegistry extends Registry<DispenseBehaviour>  {

    public DispenserRegistry(VanillaTweaks plugin) {
        super(plugin);
    }

    public DispenseBehaviour get(ItemStack key) {

        if(key != null) {
            return REGISTRY.get(key.getData());
        }
        return null;
    }

    public boolean dispense(ItemStack stack, Block sourceBlock) {
        DispenseBehaviour beh = get(stack);
        return beh != null && beh.dispense(sourceBlock, stack);
    }
}
