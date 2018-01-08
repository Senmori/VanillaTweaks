package net.senmori.vanillatweaks.controllers;

import java.util.Set;
import java.util.stream.Collectors;

import com.google.common.collect.Sets;
import net.minecraft.server.v1_12_R1.EntityPlayer;
import net.minecraft.server.v1_12_R1.EnumHand;
import net.minecraft.server.v1_12_R1.ItemStack;
import net.minecraft.server.v1_12_R1.PacketPlayOutAnimation;
import net.minecraft.server.v1_12_R1.WorldServer;
import net.senmori.vanillatweaks.VanillaTweaks;
import net.senmori.vanillatweaks.tasks.ShaveSnowTask;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.craftbukkit.v1_12_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.scheduler.BukkitRunnable;

public class ShaveSnowController extends TweakController implements Listener {

    private Set<ShaveSnowTask> tasks = Sets.newHashSet();

    public ShaveSnowController(VanillaTweaks plugin) {
        super(plugin);
        getPlugin().getServer().getPluginManager().registerEvents(this, getPlugin());
    }


    @EventHandler
    public void onShave(PlayerInteractEvent event) {
        if(!getSettings().SHAVE_SNOW.getValue()) return;
        if(event.getAction() != Action.RIGHT_CLICK_BLOCK) return;
        if(event.getClickedBlock().getType() != Material.SNOW_BLOCK && event.getClickedBlock().getType() != Material.SNOW) return;
        if(event.getBlockFace() != BlockFace.UP) return;
        if(event.getItem() == null) return;
        if(!isSpade(event.getItem().getType())) return; // not a shovel

        Player player = event.getPlayer();
        addTask(event.getClickedBlock().getLocation(), new ShaveSnowTask(getPlugin(), event.getClickedBlock()));

        EnumHand hand = event.getHand() == EquipmentSlot.HAND ? EnumHand.MAIN_HAND : EnumHand.OFF_HAND;
        WorldServer wServer = ((CraftWorld)player.getWorld()).getHandle();
        EntityPlayer human = ((CraftPlayer)player).getHandle();

        wServer.getTracker().sendPacketToEntity(human, new PacketPlayOutAnimation(human, hand == EnumHand.MAIN_HAND ? 0 : 3)); // send swing arm packet

        ItemStack nmsStack = CraftItemStack.asNMSCopy(event.getItem());
        nmsStack.damage(1, human);

        if(event.getHand() == EquipmentSlot.HAND)
            event.getPlayer().getInventory().setItemInMainHand(CraftItemStack.asBukkitCopy(nmsStack));
        else
            event.getPlayer().getInventory().setItemInOffHand(CraftItemStack.asBukkitCopy(nmsStack));
    }

    private void addTask(Location loc, ShaveSnowTask task) {
        if(!tasks.contains(task)) {
            tasks.add(task);
        }
        new BukkitRunnable() {
            @Override
            public void run() {
                tasks.stream()
                     .filter(BukkitRunnable::isCancelled)
                     .collect(Collectors.toList())
                     .forEach(tasks::remove);
            }
        }.runTaskLater(getPlugin(), 1L);
    }


    private boolean isSpade(Material material) {
        switch(material) {
            case WOOD_SPADE:
            case IRON_SPADE:
            case GOLD_SPADE:
            case STONE_SPADE:
            case DIAMOND_SPADE:
                return true;
            default:
                return false;
        }
    }
}
