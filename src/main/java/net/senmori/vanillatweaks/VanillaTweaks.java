package net.senmori.vanillatweaks;

import java.util.logging.Logger;
import net.senmori.vanillatweaks.commands.CommandManager;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class VanillaTweaks extends JavaPlugin {
    private static VanillaTweaks instance;
    public static Logger logger;

    private PluginDescriptionFile pdf;
    public static String name;

    private CommandManager commandManager;

    @Override
    public void onDisable() {

    }

    @Override
    public void onEnable() {
        pdf = getDescription();
        name = pdf.getName();

        logger = getLogger();
        instance = this;

        // commands
        commandManager = new CommandManager(this);
        commandManager.setCommandPrefix("vt");

        // tweak controllers
    }
}
