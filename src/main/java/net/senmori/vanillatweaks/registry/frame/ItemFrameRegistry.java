package net.senmori.vanillatweaks.registry.frame;

import net.senmori.vanillatweaks.VanillaTweaks;
import net.senmori.vanillatweaks.registry.Registry;
import net.senmori.vanillatweaks.registry.frame.behaviour.FrameBehaviour;
import net.senmori.vanillatweaks.registry.frame.behaviour.WrittenBookBehaviour;
import org.bukkit.Material;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public final class ItemFrameRegistry extends Registry<ItemStack, FrameBehaviour> {

    private static ItemFrameRegistry INSTANCE = new ItemFrameRegistry(VanillaTweaks.getInstance());

    private ItemFrameRegistry(VanillaTweaks plugin) {
        super(plugin);
    }

    public static ItemFrameRegistry getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new ItemFrameRegistry(VanillaTweaks.getInstance());
        }
        return INSTANCE;
    }

    public void activate(ItemStack stack, ItemFrame frame, Player whoClicked, org.bukkit.util.Vector clickedPosition) {
        if(isRegistered(stack)) {
            get(stack).activate(frame, whoClicked, clickedPosition);
        }
    }

    @Override
    protected void load() {
        // open book on right click
        register(new ItemStack(Material.WRITTEN_BOOK), new WrittenBookBehaviour());
    }
}
