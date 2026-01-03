package xyz.nucleoid.fantasy;

import net.fabricmc.api.ModInitializer;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import xyz.nucleoid.fantasy.util.TransientChunkGenerator;
import xyz.nucleoid.fantasy.util.VoidChunkGenerator;

public final class FantasyInitializer implements ModInitializer {
    @Override
    public void onInitialize() {
        Registry.register(Registries.CHUNK_GENERATOR, Fantasy.VOID_CHUNK_GENERATOR, VoidChunkGenerator.CODEC);
        Registry.register(Registries.CHUNK_GENERATOR, Fantasy.TRANSIENT_CHUNK_GENERATOR, TransientChunkGenerator.CODEC);
    }
}
