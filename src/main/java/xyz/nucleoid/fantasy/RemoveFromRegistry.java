package xyz.nucleoid.fantasy;

import net.minecraft.core.MappedRegistry;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.ApiStatus;

@ApiStatus.Internal
public interface RemoveFromRegistry<T> {
    @SuppressWarnings("unchecked")
    static <T> boolean remove(MappedRegistry<T> registry, Identifier key) {
        return ((RemoveFromRegistry<T>) registry).fantasy$remove(key);
    }

    @SuppressWarnings("unchecked")
    static <T> boolean remove(MappedRegistry<T> registry, T value) {
        return ((RemoveFromRegistry<T>) registry).fantasy$remove(value);
    }

    boolean fantasy$remove(T value);

    boolean fantasy$remove(Identifier key);

    void fantasy$setFrozen(boolean value);

    boolean fantasy$isFrozen();
}
