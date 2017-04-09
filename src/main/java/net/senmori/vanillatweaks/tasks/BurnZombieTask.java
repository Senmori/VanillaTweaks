package net.senmori.vanillatweaks.tasks;

import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_11_R1.entity.CraftZombie;
import org.bukkit.entity.Zombie;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class BurnZombieTask extends BukkitRunnable {
    private static final Random rand = new Random();

    final JavaPlugin plugin;
    final UUID worldUUID;
    final int fireTicks;
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
        }
        if(world.getTime() < 1000 || world.getTime() > 24000) {
            return; // ignore all this during nighttime
        }
        List<Zombie> zombies = world.getEntitiesByClass(Zombie.class).stream()
                                       .filter(Zombie::isBaby)
                                       .filter(z -> !z.getWorld().hasStorm()) // ignore if the world has a storm
                                       .filter(z -> !((CraftZombie)z).getHandle().isInWater()) // ignore zombies in water
                                       .filter(z -> z.getWorld().getBlockAt(z.getLocation()).getLightFromSky() == 15) // and only if they can see the sky
                                       .collect(Collectors.toList());
        zombies.forEach(z -> {
            boolean damage = false;
            ItemStack helmet = z.getEquipment().getHelmet();

            if(helmet != null) {
                if(helmet.getDurability() > 0 && !helmet.getItemMeta().isUnbreakable()) {
                    helmet.setDurability((short) ( helmet.getDurability() + rand.nextInt(2) ));

                    if(helmet.getDurability() >= helmet.getType().getMaxDurability()) {
                        helmet.setType(Material.AIR);
                    }
                    damage = false;
                }
                // if the helmet is not damageable, or it's unbreakable then they can't take damage from the sun
            } else {
                damage = true;
            }

            if(damage) {
                ((CraftZombie)z).getHandle().setOnFire(fireTicks);
            }
        });

        zombies.clear();
    }
}
