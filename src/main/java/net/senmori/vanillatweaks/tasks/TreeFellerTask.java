package net.senmori.vanillatweaks.tasks;

import net.minecraft.server.v1_11_R1.Enchantment;
import net.senmori.vanillatweaks.VanillaTweaks;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class TreeFellerTask extends BukkitRunnable {

    private final Location startLocation;
    private final Player user;
    private final int enchantLevel;
    private final Enchantment enchant;
    private final JavaPlugin plugin;
    private final boolean muted = VanillaTweaks.getInstance().getTweakConfig().isTreeFellerMuted();
    private final boolean ignoreLogVariants = VanillaTweaks.getInstance().getTweakConfig().treeFellerIgnoreLogVariants();
    private final boolean ignoreLeafVariants = VanillaTweaks.getInstance().getTweakConfig().treeFellerIgnoreLeafVariants();

    public TreeFellerTask(JavaPlugin plugin, long period, Location startLocation, Player user, Enchantment nmsEnchant, int enchantLevel) {
        this.startLocation = startLocation;
        this.user = user;
        this.enchant = nmsEnchant;
        this.enchantLevel = enchantLevel;
        this.plugin = plugin;

        this.runTaskTimer(plugin, 1L, period);
    }

    @Override
    public void run() {

    }
}
