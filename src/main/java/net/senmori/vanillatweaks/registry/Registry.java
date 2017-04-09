package net.senmori.vanillatweaks.registry;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public interface Registry<V> {

    boolean isRegistered(ItemStack key);

    /**
     * Register a new behaviour.<br>
     * @param key - the {@link ItemStack} to use as a key
     * @param ignoreMetadata - if the behaviour should ignore the {@link ItemStack}'s metadata
     * @param value - the behaviour to run
     */
    void register(ItemStack key, boolean ignoreMetadata, V value);

    /**
     * Register a new behaviour that automatically ignores metadata<br>
     * @param material - the {@link Material} to register
     * @param value - the behaviour to run
     */
    void register(Material material, V value);

    V get(ItemStack key);
}
