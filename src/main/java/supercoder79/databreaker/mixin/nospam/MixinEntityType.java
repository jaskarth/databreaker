package supercoder79.databreaker.mixin.nospam;

import net.minecraft.entity.EntityType;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(EntityType.Builder.class)
public class MixinEntityType {

    @Redirect(method = "build", at = @At(value = "INVOKE", target = "Lorg/apache/logging/log4j/Logger;warn(Ljava/lang/String;Ljava/lang/Object;)V"))
    public void shutup(Logger logger, String message, Object p0) {

    }
}
