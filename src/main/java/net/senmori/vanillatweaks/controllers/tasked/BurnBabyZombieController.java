package net.senmori.vanillatweaks.controllers.tasked;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import net.senmori.vanillatweaks.VanillaTweaks;
import net.senmori.vanillatweaks.config.SettingsManager;
import net.senmori.vanillatweaks.controllers.TweakController;
import net.senmori.vanillatweaks.tasks.BurnZombieTask;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.WorldLoadEvent;
import org.bukkit.event.world.WorldUnloadEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class BurnBabyZombieController extends TweakController implements Listener {

    private List<BurnZombieTask> tasks = new ArrayList<>();
    private SettingsManager settings;
    public BurnBabyZombieController(VanillaTweaks plugin) {
        super(plugin);

        if(!plugin.getSettingsManager().BABY_ZOMBIE.ENABLED.getValue()) {
            VanillaTweaks.debug("Baby Zombies disabled");
            return;
        }
        this.settings = plugin.getSettingsManager();
        getPlugin().getServer().getWorlds().forEach(w -> new BurnZombieTask(plugin, settings.BABY_ZOMBIE.FIRE_TICKS.getValue().intValue(), w));
    }


    @EventHandler
    public void onWorldLoad(WorldLoadEvent event) {
        if(!settings.BABY_ZOMBIE.ENABLED.getValue()) {
            tasks.forEach(BukkitRunnable::cancel);
            tasks.clear();
        }
        if(tasks.stream().noneMatch(w -> w.getWorldUUID().equals(event.getWorld().getUID()))) {
            tasks.add(new BurnZombieTask(getPlugin(), settings.BABY_ZOMBIE.FIRE_TICKS.getValue().intValue(), event.getWorld()));
        }
    }

    @EventHandler
    public void onWorldUnload(WorldUnloadEvent event) {
        if(!settings.BABY_ZOMBIE.ENABLED.getValue()) {
            tasks.forEach(BukkitRunnable::cancel);
            tasks.clear();
        }
        tasks.stream().filter(w -> w.getWorldUUID().equals(event.getWorld().getUID()))
                      .collect(Collectors.toList())
                      .forEach(BukkitRunnable::cancel);
    }
}
