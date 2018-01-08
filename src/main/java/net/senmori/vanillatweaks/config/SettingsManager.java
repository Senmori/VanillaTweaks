package net.senmori.vanillatweaks.config;

import net.senmori.senlib.configuration.ConfigManager;
import net.senmori.senlib.configuration.option.BooleanOption;
import net.senmori.vanillatweaks.VanillaTweaks;
import net.senmori.vanillatweaks.config.sections.ArmorStandOption;
import net.senmori.vanillatweaks.config.sections.BabyZombieOption;
import net.senmori.vanillatweaks.config.sections.MinecartOption;
import net.senmori.vanillatweaks.config.sections.SpongeOption;
import net.senmori.vanillatweaks.config.sections.VillagerOption;

import java.io.File;

public class SettingsManager extends ConfigManager {
    public SettingsManager(VanillaTweaks plugin, File configFile) {
        super(plugin, configFile);
    }

    // Non Configuration Section settings
    public final BooleanOption VERBOSE = registerOption("Verbose", new BooleanOption("verbose", false));

    public final BooleanOption CONVERT_DIRT = registerOption("Convert Dirt To Path", new BooleanOption("dirt-to-pth", true));

    public final BooleanOption CONVERT_CLAY = registerOption("Clay Conversion", new BooleanOption("clay-conversion", true));

    public final BooleanOption STONE_TOOL_RECIPES = registerOption("Stone Tool Pickaxes", new BooleanOption("stone-tool-variant", true));

    public final BooleanOption EDITABLE_SIGNS = registerOption("Editable Signs", new BooleanOption("editable-signs", true));

    public final BooleanOption DRAGON_BREATH_FIX = registerOption("Fix Dragon Breath", new BooleanOption("fix-dragon-breath", true));

    public final BooleanOption SHAVE_SNOW = registerOption("Shave Snow Layers", new BooleanOption("shave-snow", true));

    // Sections
    public final ArmorStandOption ARMOR_STAND = registerOption("Armor Stand Section", new ArmorStandOption("armor-stand"));

    public final MinecartOption MINECART = registerOption("Minecart Section", new MinecartOption("minecart"));

    public final BabyZombieOption BABY_ZOMBIE = registerOption("Baby Zombie Section", new BabyZombieOption("burn-baby-zombies"));

    public final SpongeOption SPONGE = registerOption("Sponge Section", new SpongeOption("sponge"));

    public final VillagerOption VILLAGER = registerOption("Villager Section", new VillagerOption("villagers"));
}
