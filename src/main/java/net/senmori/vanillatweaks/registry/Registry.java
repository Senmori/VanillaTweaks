package net.senmori.vanillatweaks.registry;

public interface Registry<K,V> {

    boolean isRegistered(K key);

    void register(K key, V value);

    V get(K key);
}
