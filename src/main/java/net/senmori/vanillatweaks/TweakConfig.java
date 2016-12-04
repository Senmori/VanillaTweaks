package net.senmori.vanillatweaks;

import java.io.File;
import net.senmori.vanillatweaks.util.FileUtil;
import org.bukkit.configuration.file.FileConfiguration;

public class TweakConfig {
    private VanillaTweaks plugin;

    private FileConfiguration configuration;

    private boolean armorStandArms;
    private boolean canConvertDirt;
    private boolean canSpreadGrass;

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

    private void load() {
        armorStandArms = configuration.getBoolean("armor-stand-arms", true);
        canConvertDirt = configuration.getBoolean("dirt-to-path", true);
        canSpreadGrass = configuration.getBoolean("bonemeal-to-grass", true);
    }

    public boolean canAddArmorStandArms() {
        return armorStandArms;
    }

    public boolean canConvertDirt() {
        return canConvertDirt;
    }

    public boolean canSpreadGrass() {
        return canSpreadGrass;
    }
}
