package net.senmori.vanillatweaks.config.sections;

import net.senmori.senlib.configuration.option.BooleanOption;
import net.senmori.senlib.configuration.option.NumberOption;
import net.senmori.senlib.configuration.option.SectionOption;

public class MinecartOption extends SectionOption {

    public static MinecartOption newOption(String key) {
        return new MinecartOption(key);
    }

    public final BooleanOption MODIFICATIONS = addOption("Minecart Modifications", BooleanOption.newOption("modifications", true));
    public final NumberOption STACK_SIZE = addOption("Minecart Max Stack Size", NumberOption.newOption("stack-size", 16));

    public MinecartOption(String key) {
        super(key, key);
    }
}
