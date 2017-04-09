package net.senmori.vanillatweaks.controllers;

import net.senmori.vanillatweaks.VanillaTweaks;
import net.senmori.vanillatweaks.registry.frame.ItemFrameRegistry;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.ItemFrame;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;

public class ItemFrameController extends TweakController implements Listener {

    public static ItemFrameRegistry REGISTRY = null;
    public ItemFrameController(VanillaTweaks plugin) {
        super(plugin);
        if(REGISTRY == null) {
            REGISTRY = new ItemFrameRegistry(getPlugin());
        }
        //getRegistry().register(new ItemStack(Material.WRITTEN_BOOK), new WrittenBookBehaviour());
        getPlugin().getServer().getPluginManager().registerEvents(this, getPlugin());
    }


    @EventHandler
    public void onClick(PlayerInteractAtEntityEvent event) {
        if(event.getRightClicked().getType() != EntityType.ITEM_FRAME) return;
        if(!event.getPlayer().isSneaking()) return;

        ItemFrame frame = (ItemFrame)event.getRightClicked();
        if(frame.getItem() == null) {
            return;
        }

        if(getRegistry().isRegistered(frame.getItem())) {
            event.setCancelled(getRegistry().activate(frame.getItem(), frame, event.getPlayer(), event.getClickedPosition()));
        }

    }

    private ItemFrameRegistry getRegistry() {
        return REGISTRY;
    }
}
