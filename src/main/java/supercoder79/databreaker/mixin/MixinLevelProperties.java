package supercoder79.databreaker.mixin;

import com.mojang.datafixers.DSL;
import com.mojang.datafixers.DataFixer;
import com.mojang.datafixers.Dynamic;
import net.minecraft.class_5285;
import net.minecraft.world.level.LevelProperties;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(class_5285.class)
public class MixinLevelProperties {
    @Redirect(method = "method_28023", at = @At(target = "Lcom/mojang/datafixers/DataFixer;update(Lcom/mojang/datafixers/DSL$TypeReference;Lcom/mojang/datafixers/Dynamic;II)Lcom/mojang/datafixers/Dynamic;", value = "INVOKE"))
    private static <T> Dynamic<T> no(DataFixer dataFixer, DSL.TypeReference type, Dynamic<T> input, int version, int newVersion) {
        return input;
    }
}
