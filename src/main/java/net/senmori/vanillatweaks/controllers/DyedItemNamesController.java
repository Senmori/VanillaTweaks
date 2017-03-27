package net.senmori.vanillatweaks.controllers;

import com.google.common.collect.ImmutableMap;
import java.util.List;
import net.senmori.vanillatweaks.VanillaTweaks;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_11_R1.inventory.CraftItemStack;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.Dye;
import org.bukkit.scheduler.BukkitRunnable;

public class DyedItemNamesController extends TweakController implements Listener {

    private final ImmutableMap<DyeColor,ChatColor> colors;
    public DyedItemNamesController(VanillaTweaks plugin) {
        super(plugin);
        colors = new ImmutableMap.Builder<DyeColor, ChatColor>()
                        .put(DyeColor.WHITE, ChatColor.WHITE)
                        .put(DyeColor.BLACK, ChatColor.BLACK)
                        .put(DyeColor.RED, ChatColor.RED)
                        .put(DyeColor.GREEN, ChatColor.GREEN)
                         .put(DyeColor.BLUE, ChatColor.BLUE)
                         .put(DyeColor.PURPLE, ChatColor.LIGHT_PURPLE)
                         .put(DyeColor.GRAY, ChatColor.GRAY)
                         .put(DyeColor.YELLOW, ChatColor.YELLOW)
                .build();

        getPlugin().getServer().getPluginManager().registerEvents(this, plugin);
    }

    @Override
    public boolean requiresRestart() {
        return true;
    }

    @Override
    public boolean hasTasks() {
        return false;
    }

    @Override
    public List<BukkitRunnable> getTasks() {
        return null;
    }

    @EventHandler
    public void onItemChange(PrepareAnvilEvent event) {
        ItemStack left = event.getInventory().getItem(0);
        ItemStack right = event.getInventory().getItem(1);
        ItemStack result = event.getResult();

        if(left != null && right != null) {
            if(right.getType().equals(Material.INK_SACK)) {
                Dye dye = (Dye)right.getData();
                ChatColor color = null;

                if(colors.containsKey(dye.getColor())) {
                    color = colors.get(dye.getColor());
                }

                if(color != null) {

                    ItemStack clone = left.clone();
                    ItemMeta meta = clone.getItemMeta();
                    if(meta.hasDisplayName()) {
                        meta.setDisplayName(ChatColor.RESET + "" + color + meta.getDisplayName());
                    } else {
                        net.minecraft.server.v1_11_R1.ItemStack nmsStack = CraftItemStack.asNMSCopy(clone);
                        meta.setDisplayName(ChatColor.RESET + "" + color + nmsStack.getName());
                    }
                    clone.setItemMeta(meta);
                    event.setResult(clone);
                }
            }
        }
    }
}
