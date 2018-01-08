package net.senmori.vanillatweaks.config.sections;

import net.senmori.senlib.configuration.option.BooleanOption;
import net.senmori.senlib.configuration.option.SectionOption;
import org.bukkit.configuration.file.FileConfiguration;

public class ArmorStandOption extends SectionOption {

    public static ArmorStandOption newOption(String key) {
        return new ArmorStandOption(key);
    }

    // Child nodes of this configuration section
    public final BooleanOption ADD_ARMS = addOption("Add Arms", BooleanOption.newOption("add-arms", true));
    public final BooleanOption ADD_PLATE = addOption("Add Base Plate", BooleanOption.newOption("add-base-plate", true));
    public final BooleanOption REMOVE_PLATE = addOption("Remove Base Plate", BooleanOption.newOption("hide-base-plate", true));
    public final BooleanOption QUICK_SWAP = addOption("Quick Swap", BooleanOption.newOption("quick-swap", true));
    public final BooleanOption OFFHAND_SWAP = addOption("OffHand Swap", BooleanOption.newOption("offhand-swap", true));

    public ArmorStandOption(String key) {
        super(key, key);
    }

    @Override
    public void save(FileConfiguration config) {

    }
}

