package supercoder79.databreaker.mixin.fix_log_spam;

import com.mojang.datafixers.DSL;
import com.mojang.datafixers.types.Type;
import net.minecraft.util.Util;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Util.class)
public class MixinUtil {

    @Redirect(method = "getChoiceTypeInternal", at = @At(value = "INVOKE", target = "Lorg/slf4j/Logger;error(Ljava/lang/String;Ljava/lang/Object;)V"))
    private static void stopLogSpam_getChoiceTypeInternal(Logger instance, String s, Object o) {

    }

    //Setting this to null avoids a crash, allowing us to bypass the first 2 schemas
    @Inject(method = "getChoiceType", at = @At("HEAD"), cancellable = true)
    private static void avoidCrash_getChoiceType(DSL.TypeReference typeReference, String string, CallbackInfoReturnable<Type<?>> cir) {
        cir.setReturnValue(null);
    }
}
