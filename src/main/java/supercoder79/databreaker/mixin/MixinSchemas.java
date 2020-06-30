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
     * @reason The core of DataBreaker. Stops the game from loading schemas.
     * @author SuperCoder79
     */
    @Overwrite
    private static void build(DataFixerBuilder builder) {
    }
}
