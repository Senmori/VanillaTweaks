package net.senmori.vanillatweaks;

import java.io.File;
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

    public boolean canCraftStoneTools() {
        return configuration.getBoolean("stone-tool-variant", true);
    }

    public int getMinecartStackSize() {
        return configuration.getInt("minecart-stack-size", 16);
    }
}
