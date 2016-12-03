package net.senmori.vanillatweaks;

import java.util.logging.Logger;
import net.senmori.vanillatweaks.commands.CommandManager;
import net.senmori.vanillatweaks.controllers.ArmorStandController;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class VanillaTweaks extends JavaPlugin {
    public static Logger logger;
    public static String name;
    private static VanillaTweaks instance;
    public TweakConfig config;
    private PluginDescriptionFile pdf;
    private CommandManager commandManager;

    public static VanillaTweaks getInstance() {
        return instance;
    }

    @Override
    public void onDisable() {

    }

    @Override
    public void onEnable() {
        pdf = getDescription();
        name = pdf.getName();

        logger = getLogger();
        instance = this;

        config = new TweakConfig(this);

        // commands
        commandManager = new CommandManager(this);
        commandManager.setCommandPrefix("vt");

        // tweak controllers
        initControllers();
    }

    private void initControllers() {
        //if(config.canAddArmorStandArms()) {
            new ArmorStandController(instance);
        //}
    }

    public TweakConfig getTweakConfig() {
        return config;
    }
}
