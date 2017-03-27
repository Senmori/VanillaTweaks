package net.senmori.vanillatweaks;

import java.io.File;
import java.io.IOException;
import net.senmori.vanillatweaks.util.FileUtil;
import org.bukkit.configuration.file.FileConfiguration;

public class TweakConfig {
    private VanillaTweaks plugin;

    private FileConfiguration configuration;

    public TweakConfig(VanillaTweaks plugin) {
        this.plugin = plugin;
        init();
    }

    private void init() {
        configuration = plugin.getConfig();

        File file = new File(plugin.getDataFolder(), "config.yml");

        if(!file.exists()) {
            file.getParentFile().mkdirs();
            FileUtil.copyFile(plugin.getResource("config.yml"), file);
        }
        configuration = plugin.getConfig();

        load();
    }

    public void save() {
        try {
            configuration.save(new File(plugin.getDataFolder(), "config.yml"));
        } catch(IOException e) {
            plugin.getLogger().warning("Could not save configuration file for " + plugin.getName());
        }
    }

    boolean canConvertDirt;
    boolean clayConversion;
    boolean stoneToolRecipes;
    boolean editableSigns;
    boolean burnBabyZombies;
    boolean fixDragonBreath;

    private void load() {
        loadArmorStand();
        loadGrassSpread();
        loadMinecarts();
        loadSuppressOutput();

        canConvertDirt = Boolean.valueOf(get("seed-convert-grass"));
        clayConversion = Boolean.valueOf(get("clay-conversion"));
        stoneToolRecipes = Boolean.valueOf(get("stone-tool-variant"));
        editableSigns = Boolean.valueOf(get("editable-signs"));
        burnBabyZombies = Boolean.valueOf(get("burn-baby-zombies"));
        fixDragonBreath = Boolean.valueOf(get("fix-dragon-breath"));
    }

    private String get(String path) {
        return configuration.getString(path);
    }

    boolean armorStandArms;
    boolean armorStandPlate;
    private void loadArmorStand() {
        armorStandArms = Boolean.valueOf(get("armor-stand.show-arms"));
        armorStandPlate = Boolean.valueOf(get("armor-stand.show-base-plate"));
    }

    public boolean canConvertDirt() {
        return canConvertDirt;
    }

    public boolean canConvertClay() {
        return clayConversion;
    }

    public boolean doAddStoneToolVariants() {
        return stoneToolRecipes;
    }

    public boolean canEditSigns() {
        return editableSigns;
    }

    public boolean canBabyZombiesBurn() {
        return burnBabyZombies;
    }

    public boolean fixDragonBreath() {
        return fixDragonBreath;
    }

    /*
        ####### ARMOR STANDS #######
     */
    public boolean showArmorStandArms() {
        return armorStandArms;
    }

    public boolean showArmorStandBasePlate() {
        return armorStandPlate;
    }

    /*
        ####### GRASS SPREAD #######
     */
    boolean canSpreadGrass;
    int grassSpreadRadius;
    private void loadGrassSpread() {
        canSpreadGrass = Boolean.valueOf(get("grass-spread.enabled"));
        grassSpreadRadius = Integer.valueOf(get("grass-spread.radius"));
    }
    public boolean canSpreadGrass() {
        return canSpreadGrass;
    }

    public int getGrassSpreadRadius() {
        return grassSpreadRadius;
    }

    /*
        ####### MINECARTS ######
     */
    boolean minecartModification;
    int minecartMaxStackSize;
    private void loadMinecarts() {
        minecartModification = Boolean.valueOf(get("minecart.modification"));
    }

    /*
        ####### SUPPRESS OUTPUT #######
     */
    boolean suppressOut;
    boolean suppressErr;
    private void loadSuppressOutput() {
        suppressOut = Boolean.valueOf(get("suppress-output.stdout"));
        suppressErr = Boolean.valueOf(get("suppress-output.stderr"));
    }

    public boolean canSuppressOut() {
        return suppressOut;
    }

    public boolean canSuppressErr() {
        return suppressErr;
    }
}
