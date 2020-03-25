package supercoder79.databreaker.mixin;

import com.mojang.datafixers.DataFixerUpper;
import net.minecraft.block.entity.BlockEntityType;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(BlockEntityType.class)
public class MixinBlockEntityType {
    @Redirect(method = "create", at = @At(target = "Lorg/apache/logging/log4j/Logger;error(Ljava/lang/String;Ljava/lang/Object;)V", value = "INVOKE"))
    private static void no(Logger logger, String message, Object o){
    }

}
