package net.senmori.vanillatweaks.config.sections;

import net.senmori.senlib.configuration.option.BooleanOption;
import net.senmori.senlib.configuration.option.NumberOption;
import net.senmori.senlib.configuration.option.SectionOption;

public class SpongeOption extends SectionOption {

    public static SpongeOption newOption(String key) {
        return new SpongeOption(key);
    }

    public final BooleanOption ENABLED = addOption("Enabled", new BooleanOption("enabled", true));
    public final NumberOption DRY_CHANCE = addOption("Dry Chance", new NumberOption("dry-chance", 7));

    public SpongeOption(String key) {
        super(key, key);
    }
}
