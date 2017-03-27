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
    }

    public void save() {
        try {
            configuration.save(new File(plugin.getDataFolder(), "config.yml"));
        } catch(IOException e) {
            plugin.getLogger().warning("Could not save configuration file for " + plugin.getName());
        }
    }

    public boolean canAddArmorStandArms() {
        return configuration.getBoolean("armor-stand-arms", true);
    }

    public boolean canConvertDirt() {
        return configuration.getBoolean("dirt-to-path", true);
    }

    public boolean canSpreadGrass() {
        return configuration.getBoolean("bonemeal-to-grass", true);
    }

    public boolean canModifyMinecarts() {
        return configuration.getBoolean("minecart-modification", true);
    }

    public boolean canConvertClay() {
        return configuration.getBoolean("clay-conversion", true);
    }

    public boolean doAddStoneToolVariants() {
        return configuration.getBoolean("stone-tool-variant", true);
    }

    public boolean canEditSigns() {
        return configuration.getBoolean("editable-signs", true);
    }

    public int getMinecartStackSize() {
        return configuration.getInt("minecart-stack-size", 16);
    }

    public boolean getSuppressOut() {
        return configuration.getBoolean("suppress-stdout", false);
    }

    public boolean getSuppressErr() {
        return configuration.getBoolean("suppress-stderr", false);
    }

    public boolean getCanBabyZombiesBurn() {
        return configuration.getBoolean("burn-baby-zombies", true);
    }

    public boolean shouldFixDragonBreath() {
        return configuration.getBoolean("fix-dragon-breath", true);
    }
}
