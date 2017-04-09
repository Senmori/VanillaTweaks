package net.senmori.vanillatweaks.controllers.tasked;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import net.senmori.vanillatweaks.VanillaTweaks;
import net.senmori.vanillatweaks.controllers.TweakController;
import net.senmori.vanillatweaks.tasks.BurnZombieTask;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.WorldLoadEvent;
import org.bukkit.event.world.WorldUnloadEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class BurnBabyZombieController extends TweakController implements Listener {

    private List<BurnZombieTask> tasks = new ArrayList<>();
    public BurnBabyZombieController(VanillaTweaks plugin) {
        super(plugin);

        if(!getPlugin().getTweakConfig().canBabyZombiesBurn()) {
            return;
        }
        getPlugin().getServer().getWorlds().forEach(w -> new BurnZombieTask(plugin, getPlugin().getTweakConfig().getZombieBurnLength(), w));
    }


    @EventHandler
    public void onWorldLoad(WorldLoadEvent event) {
        if(tasks.stream().noneMatch(w -> w.getWorldUUID().equals(event.getWorld().getUID()))) {
            tasks.add(new BurnZombieTask(getPlugin(), getPlugin().getTweakConfig().getZombieBurnLength(), event.getWorld()));
        }
    }

    @EventHandler
    public void onWorldUnload(WorldUnloadEvent event) {
        if(!getPlugin().getTweakConfig().canBabyZombiesBurn()) {
            tasks.forEach(BukkitRunnable::cancel);
        }
        if(tasks.stream().anyMatch(w -> w.getWorldUUID().equals(event.getWorld().getUID()))) {
            tasks.stream().filter(w -> w.getWorldUUID().equals(event.getWorld().getUID())).collect(Collectors.toList()).forEach(BukkitRunnable::cancel);
        }
    }
}
