package supercoder79.databreaker.mixin.nospam;

import com.mojang.datafixers.DataFixer;
import net.minecraft.datafixer.DataFixTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.storage.LevelStorage;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(LevelStorage.class)
public class MixinLevelStorage {

    @Redirect(method = "readLevelProperties(Ljava/io/File;Lcom/mojang/datafixers/DataFixer;)Lnet/minecraft/class_5219;", at = @At(value = "INVOKE", target = "Lnet/minecraft/nbt/NbtHelper;update(Lcom/mojang/datafixers/DataFixer;Lnet/minecraft/datafixer/DataFixTypes;Lnet/minecraft/nbt/CompoundTag;I)Lnet/minecraft/nbt/CompoundTag;"))
    private static CompoundTag hmmm(DataFixer fixer, DataFixTypes fixTypes, CompoundTag tag, int oldVersion) {
        return tag;
    }

}
