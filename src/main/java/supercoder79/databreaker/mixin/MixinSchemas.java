package supercoder79.databreaker.mixin;

import com.mojang.datafixers.DataFixerBuilder;
import com.mojang.datafixers.schemas.Schema;
import net.minecraft.datafixer.Schemas;
import net.minecraft.datafixer.schema.Schema100;
import net.minecraft.datafixer.schema.Schema99;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(Schemas.class)
public class MixinSchemas {
    /**
     * @author SuperCoder79
     *
     * @reason yes
     */
    @Overwrite
    private static void build(DataFixerBuilder builder) {
    }
}
