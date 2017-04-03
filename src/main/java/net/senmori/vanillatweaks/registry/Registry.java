package net.senmori.vanillatweaks.registry;

import java.util.HashMap;
import java.util.Map;
import net.senmori.vanillatweaks.VanillaTweaks;

public abstract class Registry<K, V> {
    public final Map<K,V> REGISTRY = new HashMap<>();
    protected final VanillaTweaks plugin;

    protected Registry(VanillaTweaks plugin) {
        load();
        this.plugin = plugin;
        plugin.addRegister(this);
    }

    public boolean isRegistered(K key) {
        return REGISTRY.get(key) != null;
    }

    protected boolean register(K key, V value) {
        if(!isRegistered(key)) {
            REGISTRY.put(key, value);
            return true;
        }
        return false;
    }

    public V get(K key) {
        return REGISTRY.get(key);
    }

    public void clearRegisters() {
        REGISTRY.clear();
    }

     protected abstract void load();
}
