package supercoder79.databreaker.mixin;

import com.google.common.collect.ImmutableSet;
import com.mojang.datafixers.DataFixer;
import net.minecraft.server.Main;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.World;
import net.minecraft.world.level.storage.LevelStorage;
import net.minecraft.world.updater.WorldUpdater;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.BooleanSupplier;

@Mixin(Main.class)
public abstract class MixinMinecraftServer {
    @Inject(method = "forceUpgradeWorld", at = @At("HEAD"))
    private static void pickUpBrokenGlass_convertLevel(LevelStorage.Session session, DataFixer dataFixer, boolean eraseCache, BooleanSupplier booleanSupplier, ImmutableSet<RegistryKey<World>> worlds, CallbackInfo ci) {
            throw new RuntimeException("You cannot upgrade worlds with DataBreaker. Please remove DataBreaker and then upgrade your world.");
    }
}
