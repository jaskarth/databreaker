package supercoder79.databreaker.mixin.nospam;

import net.minecraft.entity.EntityType;
import net.minecraft.util.Util;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Util.class)
public class MixinUtil {

    @Redirect(method = "method_29191", at = @At(value = "INVOKE", target = "Lorg/apache/logging/log4j/Logger;error(Ljava/lang/String;Ljava/lang/Object;)V"))
    private static void shutup(Logger logger, String message, Object p0) {

    }
}
