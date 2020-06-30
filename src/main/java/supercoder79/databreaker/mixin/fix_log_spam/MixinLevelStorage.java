package supercoder79.databreaker.mixin.fix_log_spam;

import com.mojang.datafixers.DSL;
import com.mojang.datafixers.DataFixer;
import com.mojang.serialization.Dynamic;
import net.minecraft.world.level.storage.LevelStorage;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(LevelStorage.class)
public class MixinLevelStorage {

    @Redirect(method = "method_29583(Ljava/io/File;Lcom/mojang/datafixers/DataFixer;)Lnet/minecraft/resource/DataPackSettings;", at = @At(value = "INVOKE", target = "Lcom/mojang/datafixers/DataFixer;update(Lcom/mojang/datafixers/DSL$TypeReference;Lcom/mojang/serialization/Dynamic;II)Lcom/mojang/serialization/Dynamic;"))
    private static Dynamic<?> avoidDataFixing_method_29583(DataFixer dataFixer, DSL.TypeReference type, Dynamic<?> input, int version, int newVersion) {
        return input;
    }
}
