package net.senmori.vanillatweaks.tasks;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class MinecartSpawnTask extends BukkitRunnable {

    private final Location loc;
    private final EntityType type;

    public MinecartSpawnTask(JavaPlugin plugin, Location location, EntityType type) {
        this.loc = location;
        this.type = type;
        this.runTaskLater(plugin, 1);
    }
    @Override
    public void run() {
        loc.getWorld().spawnEntity(loc, type);
        this.cancel();
    }
}
