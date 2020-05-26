package supercoder79.databreaker.mixin.nospam;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;
import com.mojang.datafixers.DSL;
import com.mojang.datafixers.DataFixer;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.Dynamic;
import com.mojang.serialization.Lifecycle;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.SharedConstants;
import net.minecraft.class_5315;
import net.minecraft.datafixer.DataFixTypes;
import net.minecraft.datafixer.NbtOps;
import net.minecraft.datafixer.TypeReferences;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtIo;
import net.minecraft.nbt.Tag;
import net.minecraft.util.Util;
import net.minecraft.world.gen.GeneratorOptions;
import net.minecraft.world.level.LevelInfo;
import net.minecraft.world.level.storage.LevelStorage;
import net.minecraft.world.level.storage.LevelSummary;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.io.File;
import java.io.FileInputStream;
import java.util.Optional;
import java.util.function.BiFunction;

@Mixin(LevelStorage.class)
public abstract class MixinLevelStorageClient {
    @Shadow protected abstract int getCurrentVersion();

    @Shadow @Final private static Logger LOGGER;

    @Shadow @Final private static ImmutableList<String> field_25020;

    /**
     * @author
     */
    @Environment(EnvType.CLIENT)
    @Overwrite
    private BiFunction<File, DataFixer, LevelSummary> method_29014(File file, boolean bl) {
        return (file2, dataFixer) -> {
            try {
                CompoundTag compoundTag = NbtIo.readCompressed(new FileInputStream(file2));
                CompoundTag compoundTag2 = compoundTag.getCompound("Data");
                compoundTag2.remove("Player");
                int i = compoundTag2.contains("DataVersion", 99) ? compoundTag2.getInt("DataVersion") : -1;
                Dynamic<Tag> dynamic = new Dynamic<>(NbtOps.INSTANCE, compoundTag2);
                class_5315 lv = class_5315.method_29023(dynamic);
                int j = lv.method_29022();
                if (j != 19132 && j != 19133) {
                    return null;
                } else {
                    boolean bl2 = j != this.getCurrentVersion();
                    File file3 = new File(file, "icon.png");
                    Pair<GeneratorOptions, Lifecycle> pair = method_29010(dynamic, dataFixer, i);
                    LevelInfo levelInfo = LevelInfo.method_28383(dynamic, pair.getFirst());
                    return new LevelSummary(levelInfo, lv, file.getName(), bl2, bl, file3, pair.getSecond());
                }
            } catch (Exception var15) {
                return null;
            }
        };
    }

    /**
     * @author
     */
    @Overwrite
    private static Pair<GeneratorOptions, Lifecycle> method_29010(Dynamic<?> dynamic, DataFixer dataFixer, int i) {
        Dynamic<?> dynamic2 = dynamic.get("WorldGenSettings").orElseEmptyMap();
        UnmodifiableIterator var4 = field_25020.iterator();

        while(var4.hasNext()) {
            String string = (String)var4.next();
            Optional<? extends Dynamic<?>> optional = dynamic.get(string).result();
            if (optional.isPresent()) {
                dynamic2 = dynamic2.set(string, (Dynamic)optional.get());
            }
        }

        Dynamic<?> dynamic3 = dynamic2;
        DataResult<GeneratorOptions> dataResult = GeneratorOptions.CODEC.parse(dynamic3);
        Logger var10002 = LOGGER;
        var10002.getClass();
        return Pair.of(dataResult.resultOrPartial(Util.method_29188("WorldGenSettings: ", var10002::error)).orElseGet(GeneratorOptions::getDefaultOptions), dataResult.lifecycle());
    }
}
