package net.senmori.vanillatweaks.controllers;

import net.senmori.vanillatweaks.VanillaTweaks;

public abstract class TweakController {
    private VanillaTweaks plugin;

    public TweakController(VanillaTweaks plugin) {
        this.plugin = plugin;
        plugin.addController(this);
    }

    public VanillaTweaks getPlugin() {
        return plugin;
    }
}
