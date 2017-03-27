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

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;
import net.senmori.vanillatweaks.commands.CommandManager;
import net.senmori.vanillatweaks.controllers.ArmorStandController;
import net.senmori.vanillatweaks.controllers.ConvertClayController;
import net.senmori.vanillatweaks.controllers.DyedItemNamesController;
import net.senmori.vanillatweaks.controllers.GrassConvertController;
import net.senmori.vanillatweaks.controllers.GrassPathController;
import net.senmori.vanillatweaks.controllers.GrassSpreadController;
import net.senmori.vanillatweaks.controllers.TweakController;
import net.senmori.vanillatweaks.controllers.tasked.MinecartController;
import net.senmori.vanillatweaks.controllers.PrintController;
import net.senmori.vanillatweaks.controllers.SignEditController;
import net.senmori.vanillatweaks.controllers.StackableItemsController;
import net.senmori.vanillatweaks.controllers.StonePickaxeController;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class VanillaTweaks extends JavaPlugin {
    public static Logger logger;
    private static VanillaTweaks instance;
    public TweakConfig config;
    private PluginDescriptionFile pdf;
    private CommandManager commandManager;

    private Set<TweakController> controllers = new HashSet<>();

    @Override
    public void onDisable() {
        getServer().getScheduler().cancelTasks(this);

        this.getTweakConfig().save();
        this.config = null;
        instance = null;
    }

    @Override
    public void onEnable() {
        pdf = getDescription();

        logger = getLogger();
        instance = this;

        config = new TweakConfig(this);

        // commands
        commandManager = new CommandManager(this);
        commandManager.setCommandPrefix("vt");

        // init tweak controllers
        initControllers();
    }

    private void initControllers() {
        controllers.add(new ArmorStandController(instance));
        controllers.add(new GrassPathController(instance));
        controllers.add(new GrassConvertController(instance));
        controllers.add(new GrassSpreadController(instance));
        controllers.add(new MinecartController(instance));
        controllers.add(new StackableItemsController(instance));
        controllers.add(new ConvertClayController(instance));
        controllers.add(new StonePickaxeController(instance));
        controllers.add(new SignEditController(instance));
        controllers.add(new DyedItemNamesController(instance));
        controllers.add(new PrintController(instance));
    }

    public TweakConfig getTweakConfig() {
        return config;
    }

    public static VanillaTweaks getInstance() {
        return instance;
    }
}
