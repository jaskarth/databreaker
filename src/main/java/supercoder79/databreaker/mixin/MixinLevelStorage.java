package supercoder79.databreaker.mixin;

import net.minecraft.world.level.LevelProperties;
import net.minecraft.world.level.storage.LevelStorage;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(LevelStorage.class)
public abstract class MixinLevelStorage {

    @Shadow public abstract LevelProperties getLevelProperties(String string);

    @Shadow protected abstract int getCurrentVersion();

    /**
     * @author
     */
    @Overwrite
    public boolean requiresConversion(String name) {
        LevelProperties levelProperties = this.getLevelProperties(name);
        boolean needsConversion = levelProperties != null && levelProperties.getVersion() != this.getCurrentVersion();
        if (needsConversion) {
            throw new RuntimeException("You cannot upgrade worlds with DataBreaker! Please remove DataBreaker and then upgrade your world.");
        }
        return false;
    }
}
