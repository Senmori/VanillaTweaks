package net.senmori.vanillatweaks.controllers.tasked;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import net.senmori.vanillatweaks.VanillaTweaks;
import net.senmori.vanillatweaks.controllers.TweakController;
import net.senmori.vanillatweaks.tasks.BurnZombieTask;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

public class BurnBabiesController extends TweakController implements Listener {

    private List<BurnZombieTask> tasks = new ArrayList<>();
    public BurnBabiesController(VanillaTweaks plugin) {
        super(plugin);

        if(!plugin.getTweakConfig().getCanBabyZombiesBurn()) {
            return;
        }
        plugin.getServer().getWorlds().forEach(w -> new BurnZombieTask(plugin, 24, w));
    }

    @Override
    public boolean requiresRestart() {
        return false;
    }

    @Override
    public boolean hasTasks() {
        return true;
    }

    @Override
    public List<BukkitRunnable> getTasks() {
        return tasks.stream().filter(t -> t != null && t instanceof BukkitRunnable).collect(Collectors.toList());
    }
}
