package net.senmori.vanillatweaks.config.sections;

import net.senmori.senlib.configuration.option.BooleanOption;
import net.senmori.senlib.configuration.option.SectionOption;
import net.senmori.senlib.configuration.option.StringOption;

public class VillagerOption extends SectionOption {

    public static VillagerOption newOption(String key) {
        return new VillagerOption(key);
    }

    public final BooleanOption ENABLED = addOption("Villager Enabled Option", new BooleanOption("enabled", true));
    public final StringOption FOLLOW_BLOCK = addOption("Villager Follow Block", new StringOption("follow-block", "emerald_block"));

    public VillagerOption(String key) {
        super(key, key);
    }
}
