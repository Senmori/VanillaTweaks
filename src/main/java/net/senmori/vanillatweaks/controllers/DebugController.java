package net.senmori.vanillatweaks.controllers;

import net.senmori.vanillatweaks.VanillaTweaks;
import net.senmori.vanillatweaks.enchantment.Enchantments;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.meta.ItemMeta;

public class DebugController extends TweakController implements Listener {
    private final boolean debug = true;

    public DebugController(VanillaTweaks plugin) {
        super(plugin);

        getPlugin().getServer().getPluginManager().registerEvents(this, getPlugin());
    }


    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        if(!debug) return;

        if(doActivate(event.getPlayer(), Material.DIAMOND_AXE, event.getAction(), Action.RIGHT_CLICK_BLOCK)) {
            ItemMeta meta = event.getPlayer().getInventory().getItemInMainHand().getItemMeta();

            meta.addEnchant(Enchantments.TREE_FELLER, 1, false);
            event.getPlayer().getInventory().getItemInMainHand().setItemMeta(meta);
        }
    }


    private void sendMessage(Player player, String message) {
        player.sendMessage(message);
    }

    private String formatBool(boolean bool) {
        return bool ? ChatColor.GREEN + "true" : ChatColor.RED + "false";
    }

    private boolean doActivate(Player player, Material inHand, Action actionPerformed, Action requiredAction) {
        return player.isSneaking() && player.getInventory().getItemInMainHand().getType() == inHand && actionPerformed == requiredAction;
    }
}
