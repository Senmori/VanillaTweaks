package net.senmori.vanillatweaks.config;

import java.util.Optional;

public class ConfigOption<T> {
    public static final ConfigOption<Optional> DEFAULT_OPTION = new ConfigOption<Optional>("__null__", null);



    public static final ConfigOption<Boolean> VERBOSE = new ConfigOption<Boolean>("verbose", false);

    public static final ConfigOption<Boolean> CONVERT_DIRT = new ConfigOption<Boolean>("dirt-to-path", true);

    public static final ConfigOption<Boolean> CONVERT_CLAY = new ConfigOption<Boolean>("clay-conversion", true);

    public static final ConfigOption<Boolean> STONE_TOOL_RECIPES = new ConfigOption<Boolean>("stone-tool-variant", true);

    public static final ConfigOption<Boolean> EDITABLE_SIGNS = new ConfigOption<Boolean>("editable-signs", true);

    public static final ConfigOption<Boolean> DRAGON_BREATH_FIX = new ConfigOption<Boolean>("fix-dragon-breath", true);

    public static final ConfigOption<Boolean> SHAVE_SNOW = new ConfigOption<Boolean>("shave-snow", true);



    public static final ConfigOption<Boolean> ARMOR_STAND_ADD_ARMS = new ConfigOption<Boolean>("armor-stand", "add-arms", true);

    public static final ConfigOption<Boolean> ARMOR_STAND_ADD_PLATE = new ConfigOption<Boolean>("armor-stand", "add-base-plate", true);

    public static final ConfigOption<Boolean> ARMOR_STAND_REMOVE_PLATE = new ConfigOption<Boolean>("armor-stand", "hide-base-plate", true);

    public static final ConfigOption<Boolean> ARMOR_STAND_QUICK_SWAP = new ConfigOption<Boolean>("armor-stand", "quick-swap", true);

    public static final ConfigOption<Boolean> ARMOR_STAND_OFFHAND_SWAMP = new ConfigOption<Boolean>("armor-stand", "offhand-swap", true);



    public static final ConfigOption<Boolean> MINECART_MODIFICATIONS = new ConfigOption<Boolean>("minecart", "modifications", true);

    public static final ConfigOption<Integer> MINECART_STACK_SIZE = new ConfigOption<Integer>("minecart", "stack-size", 16);



    public static final ConfigOption<Boolean> BABY_ZOMBIE_BURN_ENABLED = new ConfigOption<Boolean>("burn-baby-zombies", "enabled", true);

    public static final ConfigOption<Integer> BABY_ZOMBIE_BURN_LENGTH = new ConfigOption<Integer>("burn-baby-zombies", "length", 1);



    public static final ConfigOption<String> VILLAGER_FOLLOW_BLOCK = new ConfigOption<String>("villagers", "follow-block", "emerald_block");

    public static final ConfigOption<Boolean> VILLAGER_FOLLOW_ENABLED = new ConfigOption<Boolean>("villagers", "enabled", true);



    public static final ConfigOption<Boolean> SPONGE_DRY_ENABLED = new ConfigOption<Boolean>("sponge", "enabled", true);

    public static final ConfigOption<Integer> SPONGE_DRY_CHANCE = new ConfigOption<Integer>("sponge", "dry-chance", 7);

    private String nodePath;
    private T value;
    public ConfigOption(String parent, T defaultValue) {
        this(parent, "", defaultValue);
    }

    public ConfigOption(String parent, String child, T defaultValue) {
        this.nodePath = parent;
        if(child != null && !child.isEmpty()) {
            nodePath = parent + "." + child;
        }
        this.value = defaultValue;
    }

    public void set(T value) {
        this.value = value;
    }

    public String getNode() {
        return nodePath;
    }

    public T getValue() {
        return value;
    }


    public boolean equals(Object other) {
        if(other instanceof ConfigOption) {
            ConfigOption that = (ConfigOption)other;
            return this.nodePath.equals(that.nodePath);
        }
        return false;
    }
}
