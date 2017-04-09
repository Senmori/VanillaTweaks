package net.senmori.vanillatweaks.registry.frame;

import net.senmori.vanillatweaks.VanillaTweaks;
import net.senmori.vanillatweaks.registry.Registry;
import net.senmori.vanillatweaks.registry.frame.behaviour.FrameBehaviour;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

public final class ItemFrameRegistry extends Registry<FrameBehaviour> {

    public ItemFrameRegistry(VanillaTweaks plugin) {
        super(plugin);
    }

    public FrameBehaviour get(ItemStack key) {
        if(key != null) {
            return REGISTRY.get(key.getType());
        }
        return null;
    }

    public boolean activate(ItemStack stack, ItemFrame frame, Player whoClicked, org.bukkit.util.Vector clickedPosition, EquipmentSlot handUsed) {
        FrameBehaviour beh = get(stack);
        return beh != null && beh.activate(frame, whoClicked, clickedPosition, handUsed);
    }
}
