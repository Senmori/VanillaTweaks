package net.senmori.vanillatweaks.config;

import com.google.common.collect.Sets;
import java.util.Set;
import net.senmori.vanillatweaks.VanillaTweaks;
import org.bukkit.Bukkit;

public class TweakConfig extends Configuration {
    private VanillaTweaks plugin;

    private Set<ConfigOption> OPTIONS = Sets.newHashSet();
    public TweakConfig(VanillaTweaks plugin) {
        super(plugin);
        init("config.yml");
        load();
        update();
    }

    @Override
    double getVersion() {
        return 1.1;
    }

    public void load() {
        // Single options (i.e. not in a ConfigurationSection)
        ConfigOption.VERBOSE.set(getBool(ConfigOption.VERBOSE.getNode(), ConfigOption.VERBOSE.getValue()));
        addOption(ConfigOption.VERBOSE);

        ConfigOption.CONVERT_DIRT.set(getBool(ConfigOption.CONVERT_DIRT.getNode(), ConfigOption.CONVERT_DIRT.getValue()));
        addOption(ConfigOption.CONVERT_DIRT);

        ConfigOption.CONVERT_CLAY.set(getBool(ConfigOption.CONVERT_CLAY.getNode(), ConfigOption.CONVERT_CLAY.getValue()));
        addOption(ConfigOption.CONVERT_CLAY);

        ConfigOption.STONE_TOOL_RECIPES.set(getBool(ConfigOption.STONE_TOOL_RECIPES.getNode(), ConfigOption.STONE_TOOL_RECIPES.getValue()));
        addOption(ConfigOption.STONE_TOOL_RECIPES);

        ConfigOption.EDITABLE_SIGNS.set(getBool(ConfigOption.EDITABLE_SIGNS.getNode(), ConfigOption.EDITABLE_SIGNS.getValue()));
        addOption(ConfigOption.EDITABLE_SIGNS);

        ConfigOption.DRAGON_BREATH_FIX.set(getBool(ConfigOption.DRAGON_BREATH_FIX.getNode(), ConfigOption.DRAGON_BREATH_FIX.getValue()));
        addOption(ConfigOption.DRAGON_BREATH_FIX);

        ConfigOption.SHAVE_SNOW.set(getBool(ConfigOption.SHAVE_SNOW.getNode(), ConfigOption.SHAVE_SNOW.getValue()));
        addOption(ConfigOption.SHAVE_SNOW);

        // Sponge
        ConfigOption.SPONGE_DRY_ENABLED.set(getBool(ConfigOption.SPONGE_DRY_ENABLED.getNode(), ConfigOption.SPONGE_DRY_ENABLED.getValue()));
        addOption(ConfigOption.SPONGE_DRY_ENABLED);

        ConfigOption.SPONGE_DRY_CHANCE.set(getInt(ConfigOption.SPONGE_DRY_CHANCE.getNode(), ConfigOption.SPONGE_DRY_CHANCE.getValue()));
        addOption(ConfigOption.SPONGE_DRY_CHANCE);

        // Villagers
        ConfigOption.VILLAGER_FOLLOW_ENABLED.set(getBool(ConfigOption.VILLAGER_FOLLOW_ENABLED.getNode(), ConfigOption.VILLAGER_FOLLOW_ENABLED.getValue()));
        addOption(ConfigOption.VILLAGER_FOLLOW_ENABLED);

        ConfigOption.VILLAGER_FOLLOW_BLOCK.set(get(ConfigOption.VILLAGER_FOLLOW_BLOCK.getNode(), ConfigOption.VILLAGER_FOLLOW_BLOCK.getValue()));
        addOption(ConfigOption.VILLAGER_FOLLOW_BLOCK);

        // Baby Zombies
        ConfigOption.BABY_ZOMBIE_BURN_ENABLED.set(getBool(ConfigOption.BABY_ZOMBIE_BURN_ENABLED.getNode(), ConfigOption.BABY_ZOMBIE_BURN_ENABLED.getValue()));
        addOption(ConfigOption.BABY_ZOMBIE_BURN_ENABLED);

        ConfigOption.BABY_ZOMBIE_BURN_LENGTH.set(getInt(ConfigOption.BABY_ZOMBIE_BURN_LENGTH.getNode(), ConfigOption.BABY_ZOMBIE_BURN_LENGTH.getValue()));
        addOption(ConfigOption.BABY_ZOMBIE_BURN_LENGTH);

        // Minecarts
        ConfigOption.MINECART_MODIFICATIONS.set(getBool(ConfigOption.MINECART_MODIFICATIONS.getNode(), ConfigOption.MINECART_MODIFICATIONS.getValue()));
        addOption(ConfigOption.MINECART_MODIFICATIONS);

        ConfigOption.MINECART_STACK_SIZE.set(getInt(ConfigOption.MINECART_STACK_SIZE.getNode(), ConfigOption.MINECART_STACK_SIZE.getValue()));
        addOption(ConfigOption.MINECART_STACK_SIZE);

        // Armor Stand
        ConfigOption.ARMOR_STAND_ADD_ARMS.set(getBool(ConfigOption.ARMOR_STAND_ADD_ARMS.getNode(), ConfigOption.ARMOR_STAND_ADD_ARMS.getValue()));
        addOption(ConfigOption.ARMOR_STAND_ADD_ARMS);

        ConfigOption.ARMOR_STAND_ADD_PLATE.set(getBool(ConfigOption.ARMOR_STAND_ADD_PLATE.getNode(), ConfigOption.ARMOR_STAND_ADD_PLATE.getValue()));
        addOption(ConfigOption.ARMOR_STAND_ADD_PLATE);

        ConfigOption.ARMOR_STAND_REMOVE_PLATE.set(getBool(ConfigOption.ARMOR_STAND_REMOVE_PLATE.getNode(), ConfigOption.ARMOR_STAND_REMOVE_PLATE.getValue()));
        addOption(ConfigOption.ARMOR_STAND_REMOVE_PLATE);

        ConfigOption.ARMOR_STAND_QUICK_SWAP.set(getBool(ConfigOption.ARMOR_STAND_QUICK_SWAP.getNode(), ConfigOption.ARMOR_STAND_QUICK_SWAP.getValue()));
        addOption(ConfigOption.ARMOR_STAND_QUICK_SWAP);

        ConfigOption.ARMOR_STAND_OFFHAND_SWAMP.set(getBool(ConfigOption.ARMOR_STAND_OFFHAND_SWAMP.getNode(), ConfigOption.ARMOR_STAND_OFFHAND_SWAMP.getValue()));
        addOption(ConfigOption.ARMOR_STAND_OFFHAND_SWAMP);

    }

    @Override
    void update() {
        setNodeProtected("verbose");

        deleteMarkedNodes();
    }

    private void printDebug(String str) {
        if(!ConfigOption.VERBOSE.getValue()) return;
        if(str == null) {
            str = "null";
        }
        Bukkit.getLogger().info("[DEBUG] " + str);
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

    public boolean addOption(ConfigOption option) {
        return OPTIONS.add(option);
    }
}
