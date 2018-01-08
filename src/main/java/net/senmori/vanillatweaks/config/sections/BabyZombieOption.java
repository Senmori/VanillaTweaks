package net.senmori.vanillatweaks.config.sections;

import net.senmori.senlib.configuration.option.BooleanOption;
import net.senmori.senlib.configuration.option.NumberOption;
import net.senmori.senlib.configuration.option.SectionOption;

public class BabyZombieOption extends SectionOption {

    public static BabyZombieOption newOption(String key) {
        return new BabyZombieOption(key);
    }

    public final BooleanOption ENABLED = addOption("Baby Zombies Enabled", BooleanOption.newOption("enabled", true));
    public final NumberOption FIRE_TICKS = addOption("Baby Zombie Fire Ticks", NumberOption.newOption("length", 1));

    public BabyZombieOption(String key) {
        super(key, key);
    }
}
