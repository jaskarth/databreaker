package supercoder79.databreaker.mixin;

import com.mojang.datafixers.DataFixer;
import com.mojang.serialization.Dynamic;
import net.minecraft.datafixer.DataFixTypes;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtHelper;
import net.minecraft.nbt.NbtOps;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(NbtHelper.class)
public class MixinNbtHelper {
    /**
     * @reason Avoid using the fixer
     * @author SuperCoder79
     */
    @Overwrite
    public static NbtCompound update(DataFixer fixer, DataFixTypes fixTypes, NbtCompound tag, int oldVersion, int currentVersion) {
        return (NbtCompound)fixer.update(fixTypes.getTypeReference(), new Dynamic(NbtOps.INSTANCE, tag), oldVersion, currentVersion).getValue();
    }

}
