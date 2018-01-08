package net.senmori.vanillatweaks.tasks;

import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftZombie;
import org.bukkit.entity.Zombie;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class BurnZombieTask extends BukkitRunnable {
    private static final Random rand = new Random();

    private final JavaPlugin plugin;
    private final UUID worldUUID;
    private final int fireTicks;
    public BurnZombieTask(JavaPlugin plugin, int fireTicks, World world) {
        this.plugin = plugin;
        this.worldUUID = world.getUID();
        this.fireTicks = fireTicks;
        this.runTaskTimer(plugin, 1L, 20L * (fireTicks <= 1 ? 1 : (fireTicks -1)));
    }

    public UUID getWorldUUID() {
        return worldUUID;
    }

    @Override
    public void run() {
        World world = Bukkit.getWorld(getWorldUUID());
        if(world == null) {
            this.cancel(); // world isn't loaded; cancel this runnable
            return;
        }
        if(world.getTime() > 13000) {
            return; // nighttime; ignore
        }
        List<Zombie> zombies = world.getEntitiesByClass(Zombie.class).stream()
                                    .filter(Zombie::isValid)
                                    .filter(z -> z.getHealth() > 0.0D) // only get zombies who are alive
                                    .filter(Zombie::isBaby)
                                    .filter(z -> !z.getWorld().hasStorm()) // ignore if the world has a storm
                                    .filter(z -> !((CraftZombie)z).getHandle().isInWater()) // ignore zombies in water
                                    .filter(z -> z.getWorld().getBlockAt(z.getLocation()).getLightFromSky() == 15) // and only if they can see the sky
                                    .collect(Collectors.toList());
        zombies.forEach(z -> {
            boolean setOnFire = true;
            ItemStack helmet = z.getEquipment().getHelmet();

            if(helmet != null && helmet.getItemMeta() != null && !helmet.getItemMeta().isUnbreakable()) {
                if(helmet.getDurability() < helmet.getType().getMaxDurability()) {
                    helmet.setDurability((short) (helmet.getDurability() + rand.nextInt(2)));

                    if(helmet.getDurability() >= helmet.getType().getMaxDurability()) {
                        helmet.setType(Material.AIR);
                    }
                    setOnFire = false;
                }
                // if the helmet is not damageable, or it's unbreakable then they can't take damage from the sun
            }

            if(setOnFire) {
                ((CraftZombie)z).getHandle().setOnFire(fireTicks);
            }
        });
        zombies.clear();
    }
}
