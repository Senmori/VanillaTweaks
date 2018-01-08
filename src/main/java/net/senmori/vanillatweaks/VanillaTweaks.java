/**
 * This class was created by Senmori.
 * Get the Source Code in github:
 * https://github.com/Senmori/VanillaTweaks
 *
 * VanillaTweaks is Open Source and distributed under the
 * CC-BY-NC-SA 3.0 License: https://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB
 *
 * This plugin is based off the Quark mod by Vazkii.
 * This plugin is not endorsed by Vazkii in any way.
 * Get the Quark mod here: https://minecraft.curseforge.com/projects/quark
 * Website: http://quark.vazkii.us/
 * Source Code: https://github.com/Vazkii/Quark
 */
package net.senmori.vanillatweaks;

import java.io.File;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

import net.senmori.vanillatweaks.config.SettingsManager;
import net.senmori.vanillatweaks.controllers.ArmorStandController;
import net.senmori.vanillatweaks.controllers.ConvertClayController;
import net.senmori.vanillatweaks.controllers.DispenserController;
import net.senmori.vanillatweaks.controllers.DragonBreathController;
import net.senmori.vanillatweaks.controllers.GrassPathController;
import net.senmori.vanillatweaks.controllers.ItemFrameController;
import net.senmori.vanillatweaks.controllers.PrintController;
import net.senmori.vanillatweaks.controllers.QuickSwapController;
import net.senmori.vanillatweaks.controllers.ShaveSnowController;
import net.senmori.vanillatweaks.controllers.SignEditController;
import net.senmori.vanillatweaks.controllers.SpongeController;
import net.senmori.vanillatweaks.controllers.StackableItemsController;
import net.senmori.vanillatweaks.controllers.StonePickaxeController;
import net.senmori.vanillatweaks.controllers.TweakController;
import net.senmori.vanillatweaks.controllers.VillagerController;
import net.senmori.vanillatweaks.controllers.tasked.BurnBabyZombieController;
import net.senmori.vanillatweaks.controllers.tasked.MinecartController;
import org.bukkit.NamespacedKey;
import org.bukkit.plugin.java.JavaPlugin;

public class VanillaTweaks extends JavaPlugin {
    private static final boolean DEBUG = Boolean.parseBoolean(System.getProperty("vtDebug", "false"));
    public static Logger logger;
    private static VanillaTweaks instance;


    private SettingsManager settingsManager;

    @Override
    public void onDisable() {
        getServer().getScheduler().cancelTasks(this);

        this.settingsManager.saveConfig();
        instance = null;
    }

    @Override
    public void onEnable() {
        logger = getLogger();
        instance = this;

        settingsManager = new SettingsManager(this, new File(getDataFolder(), "config.yml"));

        // init tweak controllers
        initControllers();
    }

    private void initControllers() {
        new ArmorStandController(instance);
        new GrassPathController(instance);
        new MinecartController(instance);
        new StackableItemsController(instance);
        new ConvertClayController(instance);
        new StonePickaxeController(instance);
        new SignEditController(instance);
        new PrintController(instance);
        new BurnBabyZombieController(instance);
        new DragonBreathController(instance);
        new QuickSwapController(instance);
        new VillagerController(instance);
        new DispenserController(instance);
        new ItemFrameController(instance);
        new SpongeController(instance);
        new ShaveSnowController(instance);
    }

    public NamespacedKey newKey(String key) {
        return new NamespacedKey(this, key);
    }

    public SettingsManager getSettingsManager() {
        return settingsManager;
    }

    public static VanillaTweaks getInstance() {
        return instance;
    }

    public static boolean isDebugMode() {
        return DEBUG;
    }

    public static void debug(String message) {
        if(isDebugMode()) {
            logger.info("[Debug] " + message);
        }
    }
}
