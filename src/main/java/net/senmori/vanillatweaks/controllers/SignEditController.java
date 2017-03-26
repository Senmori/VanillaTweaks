package net.senmori.vanillatweaks.controllers;

import java.util.HashSet;
import java.util.Set;
import net.minecraft.server.v1_11_R1.TileEntitySign;
import net.senmori.vanillatweaks.VanillaTweaks;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_11_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_11_R1.entity.CraftPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class SignEditController extends TweakController implements Listener {

    private Set<Material> mats = new HashSet<>();
    public SignEditController(VanillaTweaks plugin) {
        super(plugin);
        mats.add(Material.WALL_SIGN);
        mats.add(Material.SIGN_POST);
        getPlugin().getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        if(!getPlugin().getTweakConfig().canEditSigns()) return;
        if(event.getAction() != Action.RIGHT_CLICK_BLOCK) return;
        if(!mats.contains(event.getClickedBlock().getType())) return;
        if(event.getPlayer().getInventory().getItemInMainHand().getType() != Material.AIR) return;

        Location loc = event.getClickedBlock().getLocation();
        CraftWorld world = (CraftWorld)loc.getWorld();
        CraftPlayer cp = (CraftPlayer)event.getPlayer();
        TileEntitySign teSign = (TileEntitySign)world.getTileEntityAt(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());
        teSign.isEditable = true;
        cp.getHandle().openSign(teSign);
    }
}
