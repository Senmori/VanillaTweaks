package net.senmori.vanillatweaks.controllers;

import net.senmori.vanillatweaks.VanillaTweaks;
import net.senmori.vanillatweaks.registry.frame.ItemFrameRegistry;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.ItemFrame;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;

public class ItemFrameController extends TweakController implements Listener {

    public static ItemFrameRegistry REGISTRY;
    public ItemFrameController(VanillaTweaks plugin) {
        super(plugin);
        REGISTRY = ItemFrameRegistry.getInstance();
        getPlugin().getServer().getPluginManager().registerEvents(this,getPlugin());
    }


    @EventHandler(priority = EventPriority.LOWEST)
    public void onClick(PlayerInteractAtEntityEvent event) {
        if(event.getRightClicked().getType() != EntityType.ITEM_FRAME) return;

        ItemFrame frame = (ItemFrame)event.getRightClicked();
        if(frame.getItem() == null) {
            return;
        }

        if(frame.getItem() != null && REGISTRY.isRegistered(frame.getItem())) {
            event.setCancelled(true);
            ItemFrameController.REGISTRY.activate(frame.getItem(), frame, event.getPlayer(), event.getClickedPosition());
        }
    }

    @Override
    public boolean hasRegistry() {
        return true;
    }

    @Override
    public ItemFrameRegistry getRegistry() {
        return REGISTRY;
    }
}
