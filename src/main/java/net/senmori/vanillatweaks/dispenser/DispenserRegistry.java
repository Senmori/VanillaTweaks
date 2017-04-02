package net.senmori.vanillatweaks.dispenser;

import java.util.HashMap;
import java.util.Map;
import net.senmori.vanillatweaks.VanillaTweaks;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.Cauldron;
import org.bukkit.scheduler.BukkitRunnable;

public final class DispenserRegistry {

    public static final Map<ItemStack, DispenseBehaviour> REGISTRY = new HashMap<>();
    static {
        load();
    }

    public static boolean isRegistered(ItemStack stack) {
        return REGISTRY.get(stack) != null;
    }

    public static boolean register(ItemStack stack, DispenseBehaviour behaviour) {
       return !isRegistered(stack) && REGISTRY.put(stack, behaviour) == null;
    }

    public static void dispense(ItemStack dispensedItem, BlockDispenseEvent event) {
        if(isRegistered(dispensedItem)) {
            REGISTRY.get(dispensedItem).dispense(event);
        }
    }

    @SuppressWarnings("deprecation")
    private static void load() {
        // water buckets fill up cauldrons
        register(new ItemStack(Material.WATER_BUCKET), event -> {
            Block sourceBlock = event.getBlock();

            org.bukkit.block.Dispenser dispBlock = (org.bukkit.block.Dispenser)sourceBlock.getState();
            org.bukkit.material.Dispenser dispMat = (org.bukkit.material.Dispenser)dispBlock.getData();

            Block cBlock = sourceBlock.getRelative(dispMat.getFacing());

            if(cBlock.getType() == Material.CAULDRON && event.getItem().getType() == Material.WATER_BUCKET && cBlock.getData() < 3) {
                Cauldron caul = (Cauldron)cBlock.getState().getData();
                if(!caul.isFull()) {
                    event.setCancelled(true);
                    cBlock.setData((byte)3);
                    cBlock.getState().update();

                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            for(int i = 0; i < dispBlock.getInventory().getSize(); i++) {
                                ItemStack stack = dispBlock.getInventory().getItem(i);
                                if(stack != null && stack.getType() == Material.WATER_BUCKET) {
                                    stack.setType(Material.BUCKET);
                                    return;
                                }
                            }
                        }
                    }.runTaskLater(VanillaTweaks.getInstance(), 1L);
                }
            }
        });
    }
}
