package net.senmori.vanillatweaks.config;

import net.senmori.vanillatweaks.VanillaTweaks;

public class TweakConfig extends Configuration {
    private VanillaTweaks plugin;

    public TweakConfig(VanillaTweaks plugin) {
        super(plugin);
        init("config.yml");
        update();
    }

    private boolean canConvertDirt;
    private boolean clayConversion;
    private boolean stoneToolRecipes;
    private boolean editableSigns;
    private boolean fixDragonBreath;

    private boolean verbose;

    @Override
    double getVersion() {
        return 1.1;
    }

    public void load() {
        verbose = getConfig().getBoolean("verbose", false);
        loadArmorStand();
        loadMinecarts();
        loadSuppressOutput();
        loadZombies();
        loadVillagers();
        loadSponge();

        canConvertDirt = getBool("dirt-to-path", true);

        clayConversion = getBool("clay-conversion", true);

        stoneToolRecipes = getBool("stone-tool-variant", true);

        editableSigns = getBool("editable-signs", true);

        fixDragonBreath = getBool("fix-dragon-breath", true);
    }

    @Override
    void update() {
        setNodeProtected("verbose");

        deleteMarkedNodes();
    }

    private void printDebug(String str) {
        if(!verbose) return;
        plugin.getLogger().info("[DEBUG] " + str);
    }

    private String get(String path, Object defaultValue) {
        if(!getConfig().contains(path)) {
            getConfig().set(path, defaultValue);
        }
        String value = getConfig().getString(path);
        printDebug(path + " = " + value);
        return value;
    }

    private boolean getBool(String path, boolean defaultValue) {
        return Boolean.valueOf(get(path, defaultValue));
    }

    private int getInt(String path, int defaultValue) {
        return Integer.valueOf(get(path, defaultValue));
    }

    private double getDouble(String path, double defaultValue) {
        return Double.parseDouble(get(path, defaultValue));
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

    /*
        ###### SPONGE ##########
     */
    private boolean spongeDriesInNether;
    private int spongeDryChance;
    public void loadSponge() {
        spongeDriesInNether = getBool("sponge.enabled", true);
        spongeDryChance = getInt("sponge.dry-chance", 7);
    }

    public boolean canSpongeDryInNether() {
        return spongeDriesInNether;
    }

    public int getSpongeDryChance() {
        return spongeDryChance;
    }
}
