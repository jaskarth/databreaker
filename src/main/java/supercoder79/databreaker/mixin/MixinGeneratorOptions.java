package supercoder79.databreaker.mixin;

import com.mojang.datafixers.DSL;
import com.mojang.datafixers.DataFixer;
import net.minecraft.world.gen.GeneratorOptions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(GeneratorOptions.class)
public class MixinGeneratorOptions {
//    @Redirect(method = "method_28023", at = @At(target = "Lcom/mojang/datafixers/DataFixer;update(Lcom/mojang/datafixers/DSL$TypeReference;Lcom/mojang/datafixers/Dynamic;II)Lcom/mojang/datafixers/Dynamic;", value = "INVOKE"))
//    private static <T> Dynamic<T> no(DataFixer dataFixer, DSL.TypeReference type, Dynamic<T> input, int version, int newVersion) {
//        return input;
//    }
}
