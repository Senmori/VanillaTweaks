package net.senmori.vanillatweaks.registry.frame;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import net.senmori.vanillatweaks.VanillaTweaks;
import net.senmori.vanillatweaks.registry.Registry;
import net.senmori.vanillatweaks.registry.RegistryItemStack;
import net.senmori.vanillatweaks.registry.frame.behaviour.FrameBehaviour;
import net.senmori.vanillatweaks.registry.frame.behaviour.WrittenBookBehaviour;
import org.bukkit.Material;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public final class ItemFrameRegistry implements Registry<FrameBehaviour> {

    public static final Map<RegistryItemStack, FrameBehaviour> REGISTRY = new HashMap<>();
    public ItemFrameRegistry(VanillaTweaks plugin) {
        register(new ItemStack(Material.WRITTEN_BOOK), true, new WrittenBookBehaviour());
    }

    public boolean isRegistered(ItemStack stack) {
        Optional<RegistryItemStack> opt = REGISTRY.keySet().stream().findFirst().filter(i -> (i.getStack().getType() == stack.getType()));

        return opt.map(registryItemStack -> registryItemStack.doIgnoreMetadata() || registryItemStack.getStack().equals(stack)).orElse(false);
    }

    public void register(ItemStack key, boolean ignoreMetadata, FrameBehaviour behaviour) {
        if(!isRegistered(key)) {
            REGISTRY.put(new RegistryItemStack(key, ignoreMetadata), behaviour);
        }
    }

    @Override
    public void register(Material material, FrameBehaviour behaviour) {
        register(new ItemStack(material), true, behaviour);
    }

    public FrameBehaviour get(ItemStack key) {
        return REGISTRY.get(key);
    }

    public boolean activate(ItemStack stack, ItemFrame frame, Player whoClicked, org.bukkit.util.Vector clickedPosition) {
        FrameBehaviour beh = get(stack);
        if(beh != null) {
            return beh.activate(frame, whoClicked, clickedPosition);
        }
        return false;
    }
}
