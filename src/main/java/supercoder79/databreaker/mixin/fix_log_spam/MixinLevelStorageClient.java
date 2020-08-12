package supercoder79.databreaker.mixin.fix_log_spam;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;
import com.mojang.datafixers.DataFixer;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.Dynamic;
import com.mojang.serialization.Lifecycle;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtIo;
import net.minecraft.nbt.NbtOps;
import net.minecraft.nbt.Tag;
import net.minecraft.resource.DataPackSettings;
import net.minecraft.util.Util;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryLookupCodec;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.GeneratorOptions;
import net.minecraft.world.gen.chunk.ChunkGeneratorSettings;
import net.minecraft.world.level.LevelInfo;
import net.minecraft.world.level.storage.LevelStorage;
import net.minecraft.world.level.storage.LevelSummary;
import net.minecraft.world.level.storage.SaveVersionInfo;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

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
     * @reason Avoid datafixing and return the provided dynamic
     * @author SuperCoder79
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
                SaveVersionInfo saveVersionInfo = SaveVersionInfo.fromDynamic(dynamic);
                int j = saveVersionInfo.getLevelFormatVersion();
                if (j != 19132 && j != 19133) {
                    return null;
                } else {
                    boolean bl2 = j != this.getCurrentVersion();
                    File file3 = new File(file, "icon.png");
                    DataPackSettings dataPackSettings = dynamic.get("DataPacks").result().map(d -> method_29580(d)).orElse(DataPackSettings.SAFE_MODE);
                    LevelInfo levelInfo = LevelInfo.fromDynamic(dynamic, dataPackSettings);
                    return new LevelSummary(levelInfo, saveVersionInfo, file.getName(), bl2, bl, file3);
                }
            } catch (Exception var15) {
                return null;
            }
        };
    }

    /**
     * @reason Avoid datafixing and return the provided dynamic
     * @author SuperCoder79
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
        final Logger var10002 = LOGGER;
        return Pair.of(dataResult.resultOrPartial(Util.method_29188("WorldGenSettings: ", var10002::error)).orElseGet(() -> {
            DataResult var10000 = RegistryLookupCodec.of(Registry.DIMENSION_TYPE_KEY).codec().parse(dynamic3);
            Registry<DimensionType> registry = null;
            try {
                registry = (Registry)var10000.resultOrPartial(Util.method_29188("Dimension type registry: ", var10002::error)).orElseThrow(() -> new IllegalStateException("Failed to get dimension registry"));
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
            var10000 = RegistryLookupCodec.of(Registry.BIOME_KEY).codec().parse(dynamic3);
            Registry<Biome> registry2 = null;
            try {
                registry2 = (Registry)var10000.resultOrPartial(Util.method_29188("Biome registry: ", var10002::error)).orElseThrow(() -> new IllegalStateException("Failed to get biome registry"));
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
            var10000 = RegistryLookupCodec.of(Registry.NOISE_SETTINGS_WORLDGEN).codec().parse(dynamic3);
            Registry<ChunkGeneratorSettings> registry3 = null;
            try {
                registry3 = (Registry)var10000.resultOrPartial(Util.method_29188("Noise settings registry: ", var10002::error)).orElseThrow(() -> new IllegalStateException("Failed to get noise settings registry"));
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
            return GeneratorOptions.getDefaultOptions(registry, registry2, registry3);
        }), dataResult.lifecycle());
    }

    private static DataPackSettings method_29580(Dynamic<?> dynamic) {
        DataResult var10000 = DataPackSettings.CODEC.parse(dynamic);
        Logger var10001 = LOGGER;
        var10001.getClass();
        return (DataPackSettings)var10000.resultOrPartial(var10001::error).orElse(DataPackSettings.SAFE_MODE);
    }
}
