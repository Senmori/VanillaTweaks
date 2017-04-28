package net.senmori.vanillatweaks.controllers.tasked;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import net.senmori.vanillatweaks.VanillaTweaks;
import net.senmori.vanillatweaks.config.ConfigOption;
import net.senmori.vanillatweaks.controllers.TweakController;
import net.senmori.vanillatweaks.tasks.BurnZombieTask;
import net.senmori.vanillatweaks.util.LogHandler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.WorldLoadEvent;
import org.bukkit.event.world.WorldUnloadEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class BurnBabyZombieController extends TweakController implements Listener {

    private List<BurnZombieTask> tasks = new ArrayList<>();
    public BurnBabyZombieController(VanillaTweaks plugin) {
        super(plugin);

        if(!ConfigOption.BABY_ZOMBIE_BURN_ENABLED.getValue()) {
            LogHandler.debug("Baby Zombies disabled");
            return;
        }
        getPlugin().getServer().getWorlds().forEach(w -> new BurnZombieTask(plugin, ConfigOption.BABY_ZOMBIE_BURN_LENGTH.getValue(), w));
    }


    @EventHandler
    public void onWorldLoad(WorldLoadEvent event) {
        if(!ConfigOption.BABY_ZOMBIE_BURN_ENABLED.getValue()) {
            tasks.forEach(BukkitRunnable::cancel);
            tasks.clear();
        }
        if(tasks.stream().noneMatch(w -> w.getWorldUUID().equals(event.getWorld().getUID()))) {
            tasks.add(new BurnZombieTask(getPlugin(), ConfigOption.BABY_ZOMBIE_BURN_LENGTH.getValue(), event.getWorld()));
        }
    }

    @EventHandler
    public void onWorldUnload(WorldUnloadEvent event) {
        if(!ConfigOption.BABY_ZOMBIE_BURN_ENABLED.getValue()) {
            tasks.forEach(BukkitRunnable::cancel);
            tasks.clear();
        }
        tasks.stream().filter(w -> w.getWorldUUID().equals(event.getWorld().getUID()))
                      .collect(Collectors.toList())
                      .forEach(BukkitRunnable::cancel);
    }
}
