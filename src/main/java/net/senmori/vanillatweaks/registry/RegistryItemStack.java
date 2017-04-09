package net.senmori.vanillatweaks.registry;

import org.bukkit.inventory.ItemStack;

public class RegistryItemStack {

    private final ItemStack stack;
    private final boolean ignoreMetadata;

    public RegistryItemStack(ItemStack stack, boolean ignoreMetadata) {
        this.stack = stack;
        this.ignoreMetadata = ignoreMetadata;
    }

    public ItemStack getStack() {
        return stack;
    }

    public boolean doIgnoreMetadata() {
        return ignoreMetadata;
    }
}
