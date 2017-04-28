package net.senmori.vanillatweaks.config;

import com.google.common.collect.Sets;
import java.util.Set;

public class ConfigOptions {
    public static Set<ConfigOption> OPTIONS = Sets.newHashSet();


    private ConfigOptions() {}

    public static Set<ConfigOption> getOptions() {
        return OPTIONS;
    }

    public static boolean addOption(ConfigOption option) {
        return OPTIONS.add(option);
    }

    public static boolean removeOption(ConfigOption option) {
        return OPTIONS.remove(option);
    }

    public static ConfigOption get(String node) {
        return OPTIONS.stream().filter(o -> o.getNode().equals(node)).findFirst().orElse(ConfigOption.DEFAULT_OPTION);
    }
}
