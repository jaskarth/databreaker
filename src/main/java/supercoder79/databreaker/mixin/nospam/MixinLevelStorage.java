package supercoder79.databreaker.mixin.nospam;

import com.mojang.datafixers.DSL;
import com.mojang.datafixers.DataFixer;
import com.mojang.serialization.Dynamic;
import net.minecraft.datafixer.DataFixTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.storage.LevelStorage;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(LevelStorage.class)
public class MixinLevelStorage {

    @Redirect(method = "readLevelProperties(Ljava/io/File;Lcom/mojang/datafixers/DataFixer;)Lnet/minecraft/world/SaveProperties;", at = @At(value = "INVOKE", target = "Lcom/mojang/datafixers/DataFixer;update(Lcom/mojang/datafixers/DSL$TypeReference;Lcom/mojang/serialization/Dynamic;II)Lcom/mojang/serialization/Dynamic;"))
    private static Dynamic<?> hmmm(DataFixer dataFixer, DSL.TypeReference type, Dynamic<?> input, int version, int newVersion) {
        return input;
    }

//    @Redirect(method = "method_29014(Ljava/io/File;Z)Ljava/util/function/BiFunction;", at = @At(value = "INVOKE", target = "Lcom/mojang/datafixers/DataFixer;update(Lcom/mojang/datafixers/DSL$TypeReference;Lcom/mojang/serialization/Dynamic;II)Lcom/mojang/serialization/Dynamic;"))
//    private static Dynamic<?> hmmm2(DataFixer dataFixer, DSL.TypeReference type, Dynamic<?> input, int version, int newVersion) {
//        return input;
//    }
}
