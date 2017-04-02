package net.senmori.vanillatweaks;

import java.io.File;
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

    private boolean canConvertDirt;
    private boolean clayConversion;
    private boolean stoneToolRecipes;
    private boolean editableSigns;
    private boolean fixDragonBreath;
    private boolean verbose;

    private void load() {
        verbose = configuration.getBoolean("verbose", false);
        loadArmorStand();
        loadMinecarts();
        loadSuppressOutput();
        loadZombies();
        loadVillagers();

        canConvertDirt = getBool("dirt-to-path", true);

        clayConversion = getBool("clay-conversion", true);

        stoneToolRecipes = getBool("stone-tool-variant", true);

        editableSigns = getBool("editable-signs", true);

        fixDragonBreath = getBool("fix-dragon-breath", true);
    }

    private void printDebug(String str) {
        if(!verbose) return;
        plugin.getLogger().info(str);
    }

    private String get(String path, Object defaultValue) {
        if(!configuration.contains(path)) {
            configuration.set(path, defaultValue);
        }
        String value = configuration.getString(path);
        printDebug(path + " = " + value);
        return value;
    }

    private boolean getBool(String path, boolean defaultValue) {
        return Boolean.valueOf(get(path, defaultValue));
    }

    private int getInt(String path, int defaultValue) {
        return Integer.valueOf(get(path, defaultValue));
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
    private boolean armorStandArms, armorStandPlate, canQuicklySwap, canSwapOffHand;
    private boolean addPlate;
    private void loadArmorStand() {
        armorStandArms = getBool("armor-stand.add-arms", true);
        armorStandPlate = getBool("armor-stand.hide-base-plate", true);
        canQuicklySwap = getBool("armor-stand.quick-swap", true);
        canSwapOffHand = getBool("armor-stand.offhand-swap", true);
        addPlate = getBool("armor-stand.add-base-plate", true);
    }
    public boolean addArmorStandArms() {
        return armorStandArms;
    }

    public boolean removeArmorStandBasePlate() {
        return armorStandPlate;
    }

    public boolean addArmorStandBasePlate() {
        return addPlate;
    }

    public boolean canQuicklySwap() {
        return canQuicklySwap;
    }

    public boolean canSwapOffhand() {
        return canSwapOffHand;
    }

    /*
        ####### MINECARTS ######
     */
    private boolean minecartModification;
    private int minecartMaxStackSize;
    private void loadMinecarts() {
        minecartModification = getBool("minecart.modification", true);
        minecartMaxStackSize = getInt("minecart.stack-size", 16);
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
    private boolean suppressOut;
    private boolean suppressErr;
    private void loadSuppressOutput() {
        suppressOut = getBool("suppress-output.stdout", false);
        suppressErr = getBool("suppress-output.stderr", false);
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
    private boolean burnBabyZombies;
    private int zombieBurnLength;
    private void loadZombies() {
        burnBabyZombies = getBool("burn-baby-zombies.enabled", true);
        zombieBurnLength = getInt("burn-baby-zombies.length", 1);
    }

    public boolean canBabyZombiesBurn() {
        return burnBabyZombies;
    }

    public int getZombieBurnLength() {
        return zombieBurnLength;
    }

    /*
        ##### VILLAGERS ##########
     */
    private String followBlock;
    private boolean villagersCanFollow;
    private void loadVillagers() {
        followBlock = get("villagers.follow-block", "emerald_block");
        villagersCanFollow = getBool("villagers.enabled", true);
    }

    public String getVillagerFollowBlock() {
        return followBlock;
    }

    public boolean getVillagersShouldFollow() {
        return villagersCanFollow;
    }
}
