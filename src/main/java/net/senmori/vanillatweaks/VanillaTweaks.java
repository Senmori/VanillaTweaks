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
import net.senmori.vanillatweaks.config.TweakConfig;
import net.senmori.vanillatweaks.controllers.ArmorStandController;
import net.senmori.vanillatweaks.controllers.ConvertClayController;
import net.senmori.vanillatweaks.controllers.DebugController;
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
import org.bukkit.plugin.java.JavaPlugin;

public class VanillaTweaks extends JavaPlugin {
    public static Logger logger;
    private static VanillaTweaks instance;
    public TweakConfig config;

    private Set<TweakController> controllers = new HashSet<>();

    @Override
    public void onDisable() {
        getServer().getScheduler().cancelTasks(this);
        controllers.clear();

        this.config = null;
        instance = null;
    }

    @Override
    public void onEnable() {
        logger = getLogger();
        instance = this;

        config = new TweakConfig(this);

        // commands - use Aikar's ACF

        instance = this;

        // init tweak controllers
        initControllers();
        initEnchantments();
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
        new DebugController(instance);
    }

    private void initEnchantments() {
        //Enchantments.init();
    }

    public TweakConfig getTweakConfig() {
        return config;
    }

    public void addController(TweakController controller) {
        controllers.add(controller);
    }

    public static VanillaTweaks getInstance() {
        return instance;
    }
}
