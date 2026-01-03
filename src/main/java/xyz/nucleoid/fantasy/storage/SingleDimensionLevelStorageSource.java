package xyz.nucleoid.fantasy.storage;

import com.mojang.datafixers.DataFixer;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.path.SymlinkEntry;
import net.minecraft.util.path.SymlinkFinder;
import net.minecraft.util.path.SymlinkValidationException;
import net.minecraft.world.World;
import net.minecraft.world.level.storage.LevelStorage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class SingleDimensionLevelStorageSource extends LevelStorage {
    private final SymlinkFinder symlinkFinder;
    private final Path worldPath;

    public SingleDimensionLevelStorageSource(Path path, SymlinkFinder symlinkFinder, DataFixer dataFixer) {
        super(path.getParent(), null, symlinkFinder, dataFixer);
        this.symlinkFinder = symlinkFinder;
        this.worldPath = path;
    }

    @Override
    public LevelStorage.Session createSession(String name) throws IOException, SymlinkValidationException {
        List<SymlinkEntry> list = this.symlinkFinder.collect(this.worldPath, true);
        if (!list.isEmpty()) {
            throw new SymlinkValidationException(this.worldPath, list);
        } else {
            return new Session(name, this.worldPath);
        }
    }

    @Override
    public LevelStorage.Session createSessionWithoutSymlinkCheck(String name) throws IOException {
        return new Session(name, this.worldPath);
    }

    class Session extends LevelStorage.Session {
        public Session(String name, Path path) throws IOException {
            super(name, path);
        }

        @Override
        public Path getWorldDirectory(RegistryKey<World> resourceKey) {
            return SingleDimensionLevelStorageSource.this.worldPath;
        }

        @Override
        public LevelStorage getLevelStorage() {
            return SingleDimensionLevelStorageSource.this;
        }

        @Override
        public long createBackup() {
            return 0;
        }
    }
}
