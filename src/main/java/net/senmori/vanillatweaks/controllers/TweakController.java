package net.senmori.vanillatweaks.controllers;

import java.util.List;
import net.senmori.vanillatweaks.VanillaTweaks;
import org.bukkit.scheduler.BukkitRunnable;

public abstract class TweakController {
    private VanillaTweaks plugin;

    public TweakController(VanillaTweaks plugin) {
        this.plugin = plugin;
    }

    public VanillaTweaks getPlugin() {
        return plugin;
    }
}
