package supercoder79.databreaker.mixin.fix_log_spam;

import net.minecraft.client.options.GameOptions;
import net.minecraft.nbt.CompoundTag;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(GameOptions.class)
public class MixinGameOptions {

    @Redirect(method = "load", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/options/GameOptions;update(Lnet/minecraft/nbt/CompoundTag;)Lnet/minecraft/nbt/CompoundTag;"))
    public CompoundTag avoidDataFixing_load(GameOptions gameOptions, CompoundTag tag) {
        return tag;
    }
}
