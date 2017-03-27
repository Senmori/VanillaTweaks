package net.senmori.vanillatweaks.controllers.tasked;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import net.senmori.vanillatweaks.VanillaTweaks;
import net.senmori.vanillatweaks.controllers.TweakController;
import net.senmori.vanillatweaks.tasks.BurnZombieTask;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

public class BurnBabyZombieController extends TweakController implements Listener {

    private List<BurnZombieTask> tasks = new ArrayList<>();
    public BurnBabyZombieController(VanillaTweaks plugin) {
        super(plugin);

        if(!plugin.getTweakConfig().canBabyZombiesBurn()) {
            return;
        }
        plugin.getServer().getWorlds().forEach(w -> new BurnZombieTask(plugin, 1, w));
    }
}
