package net.senmori.vanillatweaks.config;

import net.senmori.vanillatweaks.VanillaTweaks;

public class TweakConfig extends Configuration {
    private VanillaTweaks plugin;

    public TweakConfig(VanillaTweaks plugin) {
        super(plugin);
        init("config.yml");
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
        ConfigOptions.getOptions().parallelStream().forEach(configOption -> {
            configOption.set(get(configOption.getNode(), configOption.getValue()));
        });
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
}
