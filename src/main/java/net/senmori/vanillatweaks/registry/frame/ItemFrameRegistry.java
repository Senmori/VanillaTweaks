package net.senmori.vanillatweaks.registry.frame;

import java.util.HashMap;
import java.util.Map;
import net.senmori.vanillatweaks.VanillaTweaks;
import net.senmori.vanillatweaks.registry.Registry;
import net.senmori.vanillatweaks.registry.frame.behaviour.FrameBehaviour;
import net.senmori.vanillatweaks.registry.frame.behaviour.WrittenBookBehaviour;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public final class ItemFrameRegistry implements Registry<ItemStack, FrameBehaviour> {

    public static final Map<ItemStack, FrameBehaviour> REGISTRY = new HashMap<>();
    public ItemFrameRegistry(VanillaTweaks plugin) {

        register(new ItemStack(Material.WRITTEN_BOOK), new WrittenBookBehaviour());
    }

    public boolean isRegistered(ItemStack stack) {
        return REGISTRY.containsKey(stack);
    }

    public void register(ItemStack key, FrameBehaviour behaviour) {
        REGISTRY.put(key, behaviour);
    }

    public FrameBehaviour get(ItemStack key) {
        return REGISTRY.get(key);
    }

    public void activate(ItemStack stack, ItemFrame frame, Player whoClicked, org.bukkit.util.Vector clickedPosition) {
        get(stack).activate(frame, whoClicked, clickedPosition);
        Bukkit.broadcastMessage("Post-Activate in Registry");
    }
}
