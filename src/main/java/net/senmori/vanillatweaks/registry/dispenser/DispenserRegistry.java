package net.senmori.vanillatweaks.registry.dispenser;

import net.senmori.vanillatweaks.VanillaTweaks;
import net.senmori.vanillatweaks.registry.Registry;
import net.senmori.vanillatweaks.registry.dispenser.behaviour.DispenseBehaviour;
import net.senmori.vanillatweaks.registry.dispenser.behaviour.WaterBucketBehaviour;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

public final class DispenserRegistry extends Registry<ItemStack, DispenseBehaviour> {

    private static DispenserRegistry INSTANCE = new DispenserRegistry(VanillaTweaks.getInstance());

    private DispenserRegistry(VanillaTweaks plugin) {
        super(plugin);
    }

    public static DispenserRegistry getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new DispenserRegistry(VanillaTweaks.getInstance());
        }
        return INSTANCE;
    }

    public void dispense(ItemStack stack, Block sourceBlock) {
        if(isRegistered(stack)) {
            get(stack).dispense(sourceBlock, stack);
        }
    }

    @SuppressWarnings("deprecation")
    protected void load() {
        // water buckets fill up cauldrons
        super.register(new ItemStack(Material.WATER_BUCKET), new WaterBucketBehaviour());
    }
}
