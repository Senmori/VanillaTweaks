package net.senmori.vanillatweaks.registry;

import java.util.HashMap;
import java.util.Map;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class Registry<V> {

    protected final Map<Material, V> REGISTRY = new HashMap<>();
    protected final JavaPlugin plugin;

    protected Registry(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public boolean isRegistered(ItemStack stack) {
        return stack != null && REGISTRY.get(stack.getType()) != null;
    }

    /**
     * Register a new behaviour.<br>
     * @param key the {@link ItemStack} to use as a key
     * @param behaviour the behaviour to run
     */
    public void register(ItemStack key, V behaviour) {
        register(key.getType(), behaviour);
    }

    /**
     * Register a new behaviour that automatically ignores metadata<br>
     * @param material the {@link Material} to register
     * @param behaviour the behaviour to run
     */
    public void register(Material material, V behaviour) {
        REGISTRY.put(material, behaviour);
    }

    public abstract V get(ItemStack key);

    public Map<Material, V> getRegistry() {
        return REGISTRY;
    }
}
