package net.senmori.vanillatweaks.controllers;

import net.senmori.vanillatweaks.VanillaTweaks;
import net.senmori.vanillatweaks.config.SettingsManager;

public abstract class TweakController {
    private VanillaTweaks plugin;

    public TweakController(VanillaTweaks plugin) {
        this.plugin = plugin;
    }

    public VanillaTweaks getPlugin() {
        return plugin;
    }

    public SettingsManager getSettings() {
        return getPlugin().getSettingsManager();
    }
}
