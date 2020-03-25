package supercoder79.databreaker.mixin;

import com.mojang.datafixers.DataFixerBuilder;
import com.mojang.datafixers.DataFixerUpper;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

//TODO: this doesn't work, use i5's cused prelaunchhacks
@Mixin(DataFixerUpper.class)
public class MixinDFU {
    @Redirect(method = "update", at = @At(target = "Lorg/apache/logging/log4j/Logger;error(Ljava/lang/String;Ljava/lang/Throwable;)V", value = "INVOKE"), remap = false)
    public void no(Logger logger, String message, Throwable t){

    }

}
