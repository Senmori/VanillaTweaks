package net.senmori.vanillatweaks.tasks;

import org.bukkit.World;
import org.bukkit.entity.Zombie;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class BurnZombieTask extends BukkitRunnable {

    final JavaPlugin plugin;
    final World world;
    final int fireTicks; // 4 fireTicks removed per game tick; 6 ticks total = 24
    public BurnZombieTask(JavaPlugin plugin, int fireTicks, World world) {
        this.plugin = plugin;
        this.world = world;
        this.fireTicks = fireTicks;
        this.runTaskTimer(plugin, 1L, 6L);
    }
    @Override
    public void run() {
        world.getEntitiesByClass(Zombie.class).stream()
                                       .filter(Zombie::isBaby)
                                       .filter(z -> z.getWorld().getBlockAt(z.getLocation()).getLightFromSky() == 15)
                                       .forEach(z -> z.setFireTicks(fireTicks));
    }
}
