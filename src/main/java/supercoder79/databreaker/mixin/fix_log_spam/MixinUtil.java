package supercoder79.databreaker.mixin.fix_log_spam;

import com.mojang.datafixers.DSL;
import com.mojang.datafixers.types.Type;
import net.minecraft.util.Util;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Util.class)
public class MixinUtil {

    @Redirect(method = "method_29191", at = @At(value = "INVOKE", target = "Lorg/apache/logging/log4j/Logger;error(Ljava/lang/String;Ljava/lang/Object;)V"))
    private static void stopLogSpam_method_29191(Logger logger, String message, Object p0) {

    }

    //Setting this to null avoids a crash, allowing us to bypass the first 2 schemas
    @Inject(method = "method_29187", at = @At("HEAD"), cancellable = true)
    private static void avoidCrash_method_29187(DSL.TypeReference typeReference, String string, CallbackInfoReturnable<Type<?>> cir) {
        cir.setReturnValue(null);
    }
}
