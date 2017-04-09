package net.senmori.vanillatweaks.controllers;

import net.senmori.vanillatweaks.VanillaTweaks;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.ItemStack;

/**
 * This controller will handle the addition of arms to armor stands
 */
public final class ArmorStandController extends TweakController implements Listener {

    private final ItemStack STEP = new ItemStack(Material.STEP, 1, (byte)0);
    public ArmorStandController(VanillaTweaks plugin) {
        super(plugin);

        plugin.getServer().getPluginManager().registerEvents(this, getPlugin());
    }


    @EventHandler(priority = EventPriority.LOWEST)
    public void onArmorStandInteract(PlayerInteractAtEntityEvent event) {
        if(event.getRightClicked().getType() != EntityType.ARMOR_STAND) return;
        if(!event.getPlayer().isSneaking()) return;

        ArmorStand stand = (ArmorStand)event.getRightClicked();

        // add arms to armor stand
        if(doActivate(event.getPlayer(), Material.STICK, 2, getPlugin().getTweakConfig().addArmorStandArms())) {
            if(!stand.hasArms()) {
                addArms(stand, event.getPlayer(), 2);
            }
        }

        // remove base plate
        if(doActivate(event.getPlayer(), Material.SHEARS, 1, getPlugin().getTweakConfig().removeArmorStandBasePlate())) {
            if(stand.hasBasePlate()) {
                removePlate(stand, event.getPlayer());
            }
        }

        // add base plate
        if(doActivate(event.getPlayer(), STEP, 1, getPlugin().getTweakConfig().addArmorStandBasePlate())) {
            if(!stand.hasBasePlate()) {
                addPlate(stand, event.getPlayer());
            }
        }

    }


    private void addArms(ArmorStand stand, Player whoClicked, int reqAmount) {
        stand.setArms(true);
        if(whoClicked.getGameMode() != GameMode.CREATIVE) {
            whoClicked.getInventory().getItemInMainHand().setAmount(whoClicked.getInventory().getItemInMainHand().getAmount() - reqAmount);
        }
    }

    private boolean doActivate(Player player, Material reqMaterial, int requiredAmount, boolean condition) {
        return player.isSneaking() &&
               player.getInventory().getItemInMainHand().getType() == reqMaterial &&
               player.getInventory().getItemInMainHand().getAmount() >= requiredAmount &&
               condition;
    }

    @SuppressWarnings("deprecation")
    private boolean doActivate(Player player, ItemStack stack, int reqAmount, boolean condition) {
        return condition &&
           player.isSneaking() &&
           player.getInventory().getItemInMainHand().getType() == stack.getType() &&
           player.getInventory().getItemInMainHand().getData().getData() == stack.getData().getData() &&
           player.getInventory().getItemInMainHand().getAmount() >= reqAmount;
    }

    private void removePlate(ArmorStand stand, Player whoClicked) {
        stand.setBasePlate(false);
        if(whoClicked.getGameMode() != GameMode.CREATIVE) {
            whoClicked.getInventory().getItemInMainHand().setDurability((short)(whoClicked.getInventory().getItemInMainHand().getDurability() + 1));
        }

    }

    private void addPlate(ArmorStand stand, Player whoClicked) {
        stand.setBasePlate(true);
        if(whoClicked.getGameMode() != GameMode.CREATIVE) {
            whoClicked.getInventory().getItemInMainHand().setAmount(whoClicked.getInventory().getItemInMainHand().getAmount() - 1);
        }
    }
}
