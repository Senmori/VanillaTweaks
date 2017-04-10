package net.senmori.vanillatweaks.controllers;

import com.google.common.collect.ImmutableList;
import net.minecraft.server.v1_11_R1.BlockPosition;
import net.minecraft.server.v1_11_R1.EntityPlayer;
import net.minecraft.server.v1_11_R1.EnumHand;
import net.minecraft.server.v1_11_R1.PacketPlayOutAnimation;
import net.minecraft.server.v1_11_R1.SoundCategory;
import net.minecraft.server.v1_11_R1.SoundEffects;
import net.minecraft.server.v1_11_R1.WorldServer;
import net.senmori.vanillatweaks.VanillaTweaks;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_11_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_11_R1.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_11_R1.inventory.CraftItemStack;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

/**
 * This controller dictates if grass/dirt turn into grass paths
 */
public final class GrassPathController extends TweakController implements Listener {
    private final ImmutableList<Material> validMaterials = ImmutableList.<Material>builder()
            .add(Material.DIRT).build();

    private final ItemStack DIRT = new ItemStack(Material.DIRT, 1, (short)0);
    public GrassPathController(VanillaTweaks plugin) {
        super(plugin);
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onConvertToPath(PlayerInteractEvent event) {
        if(event.getAction() != Action.RIGHT_CLICK_BLOCK) return;
        if(event.getItem() == null) return;
        if(!isSpade(event.getItem().getType())) return;
        if(!validMaterials.contains(event.getClickedBlock().getType())) return;
        if(!getPlugin().getTweakConfig().canConvertDirt()) return;

        EnumHand hand = event.getHand() == EquipmentSlot.HAND ? EnumHand.MAIN_HAND : EnumHand.OFF_HAND;
        WorldServer wServer = ((CraftWorld)event.getPlayer().getWorld()).getHandle();
        EntityPlayer human = ((CraftPlayer)event.getPlayer()).getHandle();

        wServer.getTracker().sendPacketToEntity(human, new PacketPlayOutAnimation(human, hand == EnumHand.MAIN_HAND ? 0 : 3)); // send swing arm packet

        net.minecraft.server.v1_11_R1.World nmsWorld = ((CraftWorld)event.getPlayer().getWorld()).getHandle();
        BlockPosition pos = new BlockPosition(event.getPlayer().getLocation().getX(),event.getPlayer().getLocation().getY(),event.getPlayer().getLocation().getZ());
        nmsWorld.a(human, pos, SoundEffects.fy, SoundCategory.BLOCKS, 1.0F, 1.0F); // play appropriate sounds


        event.getClickedBlock().setType(Material.GRASS_PATH);

        net.minecraft.server.v1_11_R1.ItemStack nmsStack = CraftItemStack.asNMSCopy(event.getItem());
       nmsStack.damage(1, human); // apply damage,and increase statistics as appropriate

        int slot = event.getPlayer().getInventory().getHeldItemSlot();
        event.getPlayer().getInventory().setItem(slot, CraftItemStack.asBukkitCopy(nmsStack));
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
