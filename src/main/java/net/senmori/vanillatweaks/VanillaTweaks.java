package net.senmori.vanillatweaks;

import java.util.logging.Logger;
import net.senmori.vanillatweaks.commands.CommandManager;
import net.senmori.vanillatweaks.controllers.ArmorStandController;
import net.senmori.vanillatweaks.controllers.ConvertClayController;
import net.senmori.vanillatweaks.controllers.GrassConvertController;
import net.senmori.vanillatweaks.controllers.GrassPathController;
import net.senmori.vanillatweaks.controllers.GrassSpreadController;
import net.senmori.vanillatweaks.controllers.MinecartController;
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

    @Override
    public void onDisable() {

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
        new ArmorStandController(instance);
        new GrassPathController(instance);
        new GrassConvertController(instance);
        new GrassSpreadController(instance);
        new MinecartController(instance);
        new StackableItemsController(instance);
        new ConvertClayController(instance);
        new StonePickaxeController(instance);
    }

    public TweakConfig getTweakConfig() {
        return config;
    }

    public static VanillaTweaks getInstance() {
        return instance;
    }
}
