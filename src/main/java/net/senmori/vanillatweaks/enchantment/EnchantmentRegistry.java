package net.senmori.vanillatweaks.enchantment;

import java.lang.reflect.Field;
import net.senmori.vanillatweaks.VanillaTweaks;
import net.senmori.vanillatweaks.util.ResourceIdentifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentWrapper;

public class EnchantmentRegistry {

    public static void registerEnchant(ResourceIdentifier identifier, AbstractEnchantment enchant, int id) {
        //Enchantment.enchantments.a(id, new MinecraftKey(identifier.toString()), enchant);

        // set Bukkit Enchantment to accepting registrations
        startAcceptingRegistrations();
        org.bukkit.enchantments.Enchantment.registerEnchantment(new EnchantmentWrapper(id));
        Enchantment.stopAcceptingRegistrations();
    }

    private static void startAcceptingRegistrations() {
        if(!org.bukkit.enchantments.Enchantment.isAcceptingRegistrations()) {
            try {
                Field registrationField = org.bukkit.enchantments.Enchantment.class.getDeclaredField("acceptingNew");
                registrationField.setAccessible(true);
                registrationField.set(null, Boolean.TRUE);
                VanillaTweaks.getInstance().getLogger().info("Enchantment Accepting Registrations: " + org.bukkit.enchantments.Enchantment.isAcceptingRegistrations());
            } catch(NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
