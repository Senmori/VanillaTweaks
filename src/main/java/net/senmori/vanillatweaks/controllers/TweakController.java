package net.senmori.vanillatweaks.controllers;

import net.senmori.vanillatweaks.VanillaTweaks;
import net.senmori.vanillatweaks.registry.Registry;

public abstract class TweakController {
    private VanillaTweaks plugin;

    public TweakController(VanillaTweaks plugin) {
        this.plugin = plugin;
        plugin.addController(this);
    }

    public VanillaTweaks getPlugin() {
        return plugin;
    }

    public boolean hasRegistry() {
        return false;
    }

    public <E extends Registry> E getRegistry() {
        return null;
    }
}
