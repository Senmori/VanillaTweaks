package net.senmori.vanillatweaks;

import java.io.File;
import java.io.IOException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class TweakConfig {
    private VanillaTweaks plugin;

    private FileConfiguration configuration;
    private File file;

    public TweakConfig(VanillaTweaks plugin) {
        this.plugin = plugin;
        init();
    }

    private void init() {
        if(!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdirs();
        }

        file = new File(plugin.getDataFolder(), "config.yml");

        if(!file.exists()) {
            plugin.saveResource("config.yml", true);
        }

        configuration = YamlConfiguration.loadConfiguration(file);
        load();
    }

    boolean canConvertDirt;
    boolean clayConversion;
    boolean stoneToolRecipes;
    boolean editableSigns;
    boolean fixDragonBreath;
    boolean verbose;

    private void load() {
        verbose = configuration.getBoolean("verbose", false);
        loadArmorStand();
        loadGrassSpread();
        loadMinecarts();
        loadSuppressOutput();
        loadZombies();

        canConvertDirt = getBool("seed-convert-grass");
        display("Can Convert Dirt: " + canConvertDirt);

        clayConversion = getBool("clay-conversion");
        display("Can Convert Clay: " + clayConversion);

        stoneToolRecipes = getBool("stone-tool-variant");
        display("Stone Tool Recipes: " + stoneToolRecipes);

        editableSigns = getBool("editable-signs");
        display("Editable Signs: " + editableSigns);

        fixDragonBreath = getBool("fix-dragon-breath");
        display("Fix Dragon Breath: " + fixDragonBreath);
    }

    private void display(String str) {
        if(!verbose) return;
        plugin.getLogger().info(str);
    }

    private String get(String path) {
        return configuration.getString(path);
    }

    private boolean getBool(String path) {
        return Boolean.valueOf(get(path));
    }

    private int getInt(String path) {
        return Integer.valueOf(get(path));
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

    public boolean fixDragonBreath() {
        return fixDragonBreath;
    }

    /*
        ####### ARMOR STANDS #######
     */
    boolean armorStandArms;
    boolean armorStandPlate;
    boolean canQuicklySwap;
    boolean canSwapOffhand;
    private void loadArmorStand() {
        armorStandArms = getBool("armor-stand.show-arms");
        display("Show Armor Stand Arms: " + armorStandArms);

        armorStandPlate = getBool("armor-stand.show-base-plate");
        display("Show Armor Stand Plate: " + armorStandPlate);

        canQuicklySwap = getBool("armor-stand.quick-swap");
        display("Can Quickly Swap: " + canQuicklySwap);

        canSwapOffhand = getBool("armor-stand.offhand-swap");
        display("Can Swap Offhand: " + canSwapOffhand);
    }
    public boolean showArmorStandArms() {
        return armorStandArms;
    }

    public boolean showArmorStandBasePlate() {
        return armorStandPlate;
    }

    public boolean canQuicklySwap() {
        return canQuicklySwap;
    }

    public boolean canSwapOffhand() {
        return canSwapOffhand;
    }

    /*
        ####### GRASS SPREAD #######
     */
    boolean canSpreadGrass;
    int grassSpreadRadius;
    private void loadGrassSpread() {
        canSpreadGrass = getBool("grass-spread.enabled");
        display("Can Spread Grass: " + canSpreadGrass);

        grassSpreadRadius = getInt("grass-spread.radius");
        display("Grass Spread Radius: " + grassSpreadRadius);
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
        minecartModification = getBool("minecart.modification");
        display("Minecart Modification: " + minecartModification);

        minecartMaxStackSize = getInt("minecart.stack-size");
        display("Minecart Max Stack Size: " + minecartMaxStackSize);
    }

    public boolean canModifyMinecarts() {
        return minecartModification;
    }

    public int getMinecartStackSize() {
        return minecartMaxStackSize;
    }

    /*
        ####### SUPPRESS OUTPUT #######
     */
    boolean suppressOut;
    boolean suppressErr;
    private void loadSuppressOutput() {
        suppressOut = getBool("suppress-output.stdout");
        display("Suppress STDOUT: " + suppressOut);

        suppressErr = getBool("suppress-output.stderr");
        display("Suppress STDERR: " + suppressErr);
    }

    public boolean canSuppressOut() {
        return suppressOut;
    }

    public boolean canSuppressErr() {
        return suppressErr;
    }

    /*
        ####### BABY ZOMBIES #######
     */
    boolean burnBabyZombies;
    int zombieBurnLength;
    private void loadZombies() {
        burnBabyZombies = getBool("burn-baby-zombies.enabled");
        display("Burn Baby Zombies: " + burnBabyZombies);

        zombieBurnLength = getInt("burn-baby-zombies.length");
        display("Baby Zombie Burn Length: " + zombieBurnLength);
    }

    public boolean canBabyZombiesBurn() {
        return burnBabyZombies;
    }

    public int getZombieBurnLength() {
        return zombieBurnLength;
    }
}
