package net.senmori.vanillatweaks.tasks;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.BlockFace;
import org.bukkit.craftbukkit.libs.joptsimple.internal.Strings;
import org.bukkit.craftbukkit.v1_11_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_11_R1.entity.CraftZombie;
import org.bukkit.craftbukkit.v1_11_R1.inventory.CraftItemStack;
import org.bukkit.entity.Boat;
import org.bukkit.entity.Zombie;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class BurnZombieTask extends BukkitRunnable {
    private static final Random rand = new Random();

    final JavaPlugin plugin;
    final World world;
    final int fireTicks; // 4 fireTicks removed per game tick; 6 ticks total = 24
    public BurnZombieTask(JavaPlugin plugin, int fireTicks, World world) {
        this.plugin = plugin;
        this.world = world;
        this.fireTicks = fireTicks;
        this.runTaskTimer(plugin, 1L, 20L);
    }
    @Override
    public void run() {
        net.minecraft.server.v1_11_R1.World nmsWorld = ((CraftWorld)world).getHandle();
        if(world.getTime() < 1000 || world.getTime() > 24000) {
            return; // ignore all this during nighttime
        }
        List<Zombie> zombies = world.getEntitiesByClass(Zombie.class).stream()
                                       .filter(Zombie::isBaby)
                                       .filter(z -> !z.getWorld().hasStorm())
                                       .filter(z -> !((CraftZombie)z).getHandle().isInWater())
                                       .filter(z -> z.getWorld().getBlockAt(z.getLocation()).getLightFromSky() == 15)
                                       .collect(Collectors.toList());

        zombies.forEach(z -> {
            boolean flag = true;
            ItemStack helmet = z.getEquipment().getHelmet();

            if(helmet != null) {
                if(helmet.getDurability() > 0 && ! helmet.getItemMeta().isUnbreakable()) {
                    helmet.setDurability((short) ( helmet.getDurability() + rand.nextInt(2) ));

                    if(helmet.getDurability() >= helmet.getType().getMaxDurability()) {
                        helmet.setType(Material.AIR);
                    }
                    flag = false;
                }
            }


            if(flag) {
                if(z.getFireTicks() <= 0) {
                    ((CraftZombie)z).getHandle().setOnFire(1);
                }
            }
        });

        zombies.clear();
    }
}
