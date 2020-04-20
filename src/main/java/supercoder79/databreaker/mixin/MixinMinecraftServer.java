package supercoder79.databreaker.mixin;

import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.storage.LevelStorage;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(MinecraftServer.class)
public abstract class MixinMinecraftServer {
    @Redirect(method = "method_27052", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/storage/LevelStorage$Session;needsConversion()Z"))
    public boolean concern(LevelStorage.Session session) {
        boolean shouldExplode = session.needsConversion();
        if (shouldExplode) {
            throw new RuntimeException("You cannot upgrade worlds with DataBreaker! Please remove DataBreaker and then upgrade your world.");
        }

        return false;
    }
}
