package supercoder79.databreaker.mixin.fix_log_spam;

import net.minecraft.client.option.GameOptions;
import net.minecraft.nbt.NbtCompound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(GameOptions.class)
public class MixinGameOptions {

    @Redirect(method = "load", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/option/GameOptions;update(Lnet/minecraft/nbt/NbtCompound;)Lnet/minecraft/nbt/NbtCompound;"))
    public NbtCompound avoidDataFixing_load(GameOptions gameOptions, NbtCompound tag) {
        return tag;
    }
}
