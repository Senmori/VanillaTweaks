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
    private boolean canShaveSnow;

    private boolean verbose;

    @Override
    double getVersion() {
        return 1.1;
    }

    public void load() {
        verbose = getConfig().getBoolean("verbose", false);

        canConvertDirt = getBool("dirt-to-path", true);

        clayConversion = getBool("clay-conversion", true);

        stoneToolRecipes = getBool("stone-tool-variant", true);

        editableSigns = getBool("editable-signs", true);

        fixDragonBreath = getBool("fix-dragon-breath", true);

        canShaveSnow = getBool("shave-snow", true);

        loadArmorStand();
        loadMinecarts();
        loadSuppressOutput();
        loadZombies();
        loadVillagers();
        loadSponge();
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

    public boolean getBool(String path, boolean defaultValue) {
        return Boolean.valueOf(get(path, defaultValue));
    }

    public int getInt(String path, int defaultValue) {
        return Integer.valueOf(get(path, defaultValue));
    }

    public double getDouble(String path, double defaultValue) {
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

    public boolean canShaveSnow() {
        return canShaveSnow;
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


    /*
     ######## TREE FELLER #######
     */
    private boolean treeFellerEnabled, treeFellerMuted, treeFellerSneak, treeFellerIgnoreLog, treefellerIgnoreLeaf;
    private long treeFellerPeriod;

    public void loadTreeFeller() {
        this.treeFellerEnabled = getBool("enchantment.tree_feller.enabled", true);
        this.treeFellerMuted = getBool("enchantment.tree_feller.muted", false);
        this.treeFellerSneak = getBool("enchantment.tree_feller.require-sneaking", false);
        this.treefellerIgnoreLeaf = getBool("enchantment.tree_feller.ignore_leaf_variants", false);
        this.treeFellerIgnoreLog = getBool("enchantment.tree_feller.ignore_log_variants", false);
        this.treeFellerPeriod = (long)getInt("enchantment.tree_feller.period", 5);
    }

    public boolean isTreeFellerEnabled() {
        return treeFellerEnabled;
    }

    public boolean isTreeFellerMuted() {
        return treeFellerMuted;
    }

    public boolean treeFellerSneakRequired() {
        return treeFellerSneak;
    }

    public boolean treeFellerIgnoreLeaf() {
        return treefellerIgnoreLeaf;
    }

    public boolean treeFellerIgnoreLog() {
        return treeFellerIgnoreLog;
    }

    public long getTreeFellerPeriod() {
        return treeFellerPeriod;
    }
}
