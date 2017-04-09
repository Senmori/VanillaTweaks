package net.senmori.vanillatweaks.config;

import com.sun.istack.internal.NotNull;
import java.io.File;
import java.util.HashSet;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class Configuration {

    private final JavaPlugin plugin;
    private File file;
    private FileConfiguration config;

    /** A set of protected nodes that cannot be deleted automatically */
    protected HashSet<String> protectedNodes = new HashSet<>();

    /** A set of nodes that will be deleted on the next startup/reload */
    protected HashSet<String> marked = new HashSet<>();
    public Configuration(JavaPlugin plugin) {
        this.plugin = plugin;
    }


    /**
     * Get the version of this configuration
     * @return - the version of this config
     */
    abstract double getVersion();

    /**
     * Load all information from file
     */
    abstract void load();

    /**
     * Perform all versioning here.
     * This must be called AFTER {@link #load} to ensure all user settings are maintained.
     */
    abstract void update();


    public void setNodeProtected(@NotNull String node) {
        if(node != null && !node.isEmpty()) {
            protectedNodes.add(node);
        }
    }

    public boolean isNodeProtected(@NotNull String node) {
        return protectedNodes.contains(node);
    }

    public void markNodeForDeletection(@NotNull String node) {
        if(node != null && !node.isEmpty()) {
            marked.add(node);
        }
    }

    public boolean isNodeMarkedForDeletion(@NotNull String node) {
        return marked.contains(node);
    }

    protected void deleteNode(@NotNull String node) {
        if(isNodeMarkedForDeletion(node) && !isNodeProtected(node) && getConfig().contains(node)) {
            getConfig().set(node, null);
        }
    }

    public void deleteMarkedNodes() {
        marked.forEach(this::deleteNode);
    }

    /** Initializes the configuration file.
     *
     * @param configFileName the config file's name, including file extensions
     */
    protected void init(String configFileName) {
        if(!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdirs();
        }

        file = new File(plugin.getDataFolder(), configFileName);

        if(!file.exists()) {
            plugin.saveResource(configFileName, true);
        }

        config = YamlConfiguration.loadConfiguration(file);
        load();
    }

    public File getFile() {
        return file;
    }

    public FileConfiguration getConfig() {
        return config;
    }

    public JavaPlugin getPlugin() {
        return plugin;
    }
}
