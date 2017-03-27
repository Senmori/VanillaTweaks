package net.senmori.vanillatweaks.controllers;

import com.google.common.collect.ImmutableList;
import java.util.List;
import net.senmori.vanillatweaks.VanillaTweaks;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * This controller dictates if grass/dirt turn into grass paths
 */
public final class GrassPathController extends TweakController implements Listener {
    private final ImmutableList<Material> validMaterials = ImmutableList.<Material>builder()
            .add(Material.DIRT).build();
    public GrassPathController(VanillaTweaks plugin) {
        super(plugin);
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @Override
    public boolean requiresRestart() {
        return false;
    }

    @Override
    public boolean hasTasks() {
        return false;
    }

    @Override
    public List<BukkitRunnable> getTasks() {
        return null;
    }

    @EventHandler
    public void onConvertToPath(PlayerInteractEvent event) {
        if(event.getAction() != Action.RIGHT_CLICK_BLOCK) return;
        if(event.getItem() == null) return;
        if(!isSpade(event.getItem().getType())) return;
        if(!validMaterials.contains(event.getClickedBlock().getType())) return;
        if(!getPlugin().config.canSpreadGrass()) return;

        event.getClickedBlock().setType(Material.GRASS_PATH);
        ItemStack heldItem = event.getPlayer().getInventory().getItem(event.getPlayer().getInventory().getHeldItemSlot());
        heldItem.setDurability((short) (heldItem.getDurability() + 1));
    }


    private boolean isSpade(Material material) {
        switch(material) {
            case WOOD_SPADE:
            case IRON_SPADE:
            case GOLD_SPADE:
            case STONE_SPADE:
            case DIAMOND_SPADE:
                return true;
            default:
                return false;
        }
    }
}
