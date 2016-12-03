package net.senmori.vanillatweaks;

import java.io.File;
import net.senmori.vanillatweaks.util.FileUtil;
import org.bukkit.configuration.file.FileConfiguration;

public class TweakConfig {
    private VanillaTweaks plugin;

    private FileConfiguration configuration;

    private boolean armorStandArms;

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
    }

    public boolean canAddArmorStandArms() {
        return armorStandArms;
    }
}
