package net.senmori.vanillatweaks.controllers;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Collection;

import java.util.List;
import net.senmori.vanillatweaks.VanillaTweaks;
import net.senmori.vanillatweaks.util.LogHandler;
import net.senmori.vanillatweaks.util.RecipeUtil;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * This controller will handle the addition of arms to armor stands
 */
public final class ArmorStandController extends TweakController implements Listener {
    private final String ARM_LORE = ChatColor.GRAY + "" + ChatColor.ITALIC + "Has Arms";
    private final String SMALL_LORE = ChatColor.GRAY + "" + ChatColor.ITALIC + "Small";

    private final ShapedRecipe armsRecipe;
    private final ShapelessRecipe smallRecipe;

    public ArmorStandController(VanillaTweaks plugin) {
        super(plugin);

        // ArmorStand w/ arms
        ItemStack armorStand = new ItemStack(Material.ARMOR_STAND);
        setLore(ARM_LORE, armorStand);
        armsRecipe = new ShapedRecipe(armorStand);
        armsRecipe.shape("   ",
                         "@#@",
                         "   ");
        armsRecipe.setIngredient('@', Material.STICK);
        armsRecipe.setIngredient('#', Material.ARMOR_STAND);
        armsRecipe.setIngredient(' ', Material.AIR);
        plugin.getServer().addRecipe(armsRecipe);

        // Small ArmorStand
        armorStand = new ItemStack(Material.ARMOR_STAND);
        setLore(SMALL_LORE, armorStand);
        smallRecipe = new ShapelessRecipe(armorStand);
        smallRecipe.addIngredient(Material.ARMOR_STAND);
        plugin.getServer().addRecipe(smallRecipe);

        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onCraft(CraftItemEvent event) {
        if(!getPlugin().config.canAddArmorStandArms()) return;
        if(RecipeUtil.areEqual(event.getRecipe(), armsRecipe)) {
            setLore(ARM_LORE, event.getRecipe().getResult());
        }
        if(RecipeUtil.areEqual(event.getRecipe(), smallRecipe)) {
            setLore(SMALL_LORE, event.getRecipe().getResult());
        }
    }

    @EventHandler
    public void onPlace(PlayerInteractEvent event) {
        if(event.getItem() == null) return;
        if(event.getItem().getType() != Material.ARMOR_STAND) return;
        if(event.getAction() != Action.RIGHT_CLICK_BLOCK) return;
        event.setUseItemInHand(Event.Result.DENY);
        Location loc = event.getClickedBlock().getLocation().add(0.5, 1.0, 0.5);
        loc.setYaw(event.getPlayer().getLocation().getYaw() - 180.0f);
        ArmorStand stand = (ArmorStand)event.getPlayer().getWorld().spawnEntity(loc, EntityType.ARMOR_STAND);
        stand.teleport(loc);
        //LogHandler.info("ARMS: " + hasLore(event.getItem(), ARM_LORE));
        if(hasLore(event.getItem(), ARM_LORE) && getPlugin().config.canAddArmorStandArms()) {
            stand.setArms(true);
        }
        //LogHandler.info("SMALL: " + hasLore(event.getItem(), SMALL_LORE));
        if(hasLore(event.getItem(), SMALL_LORE) && getPlugin().config.canAddArmorStandArms()) {
            stand.setSmall(true);
        }
        event.getItem().setAmount(event.getItem().getAmount() -1);
    }

    @EventHandler
    public void onBreakArmorStand(EntityDeathEvent event) {
        if(!getPlugin().config.canAddArmorStandArms()) return;
        LogHandler.info(event.getEntity().getType() + " has died.");
    }


    private void setLore(String lore, ItemStack stack) {
        ItemMeta meta = stack.getItemMeta();
        List<String> list = meta.hasLore() ? meta.getLore() : new ArrayList<>();
        if(lore == null || lore.isEmpty()) {
            meta.setLore(list);
        } else {
            list.add(lore);
            meta.setLore(list);
        }
        stack.setItemMeta(meta);
    }

    private boolean hasLore(ItemStack stack, String lore) {
        if(!stack.hasItemMeta() || (stack.hasItemMeta() && !stack.getItemMeta().hasLore()) || lore == null || lore.isEmpty()) {
            return false;
        }
        return stack.getItemMeta().getLore().contains(lore);
    }
}
