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
import net.minecraft.SharedConstants;
import net.minecraft.datafixer.TypeReferences;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtIo;
import net.minecraft.nbt.NbtOps;
import net.minecraft.resource.DataPackSettings;
import net.minecraft.util.Util;
import net.minecraft.util.registry.DynamicRegistryManager;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.GeneratorOptions;
import net.minecraft.world.gen.WorldPresets;
import net.minecraft.world.gen.chunk.ChunkGeneratorSettings;
import net.minecraft.world.level.LevelInfo;
import net.minecraft.world.level.storage.LevelStorage;
import net.minecraft.world.level.storage.LevelSummary;
import net.minecraft.world.level.storage.SaveVersionInfo;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.io.File;
import java.io.FileInputStream;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BiFunction;

@Mixin(LevelStorage.class)
public abstract class MixinLevelStorageClient {
    @Shadow protected abstract int getCurrentVersion();

    @Shadow @Final private static Logger LOGGER;

    @Shadow @Final private static ImmutableList<String> GENERATOR_OPTION_KEYS;

    /**
     * @reason Avoid datafixing and return the provided dynamic
     * @author SuperCoder79
     */
//    @Environment(EnvType.CLIENT)
//    @Overwrite
//    public BiFunction<File, DataFixer, LevelSummary> createLevelDataParser(File file, boolean bl) {
//        return (file2, dataFixer) -> {
//            try {
//                NbtCompound compoundTag = NbtIo.readCompressed(new FileInputStream(file2));
//                NbtCompound compoundTag2 = compoundTag.getCompound("Data");
//                compoundTag2.remove("Player");
//                int i = compoundTag2.contains("DataVersion", 99) ? compoundTag2.getInt("DataVersion") : -1;
//                Dynamic<NbtElement> dynamic = new Dynamic<>(NbtOps.INSTANCE, compoundTag2);
//                SaveVersionInfo saveVersionInfo = SaveVersionInfo.fromDynamic(dynamic);
//                int j = saveVersionInfo.getLevelFormatVersion();
//                if (j != 19132 && j != 19133) {
//                    return null;
//                } else {
//                    boolean bl2 = j != this.getCurrentVersion();
//                    File file3 = new File(file, "icon.png");
//                    DataPackSettings dataPackSettings = dynamic.get("DataPacks").result().map(d -> method_29580(d)).orElse(DataPackSettings.SAFE_MODE);
//                    LevelInfo levelInfo = LevelInfo.fromDynamic(dynamic, dataPackSettings);
//                    return new LevelSummary(levelInfo, saveVersionInfo, file.getName(), bl2, bl, file3);
//                }
//            } catch (Exception var15) {
//                return null;
//            }
//        };
//    }

    /**
     * @reason Avoid datafixing and return the provided dynamic
     * @author SuperCoder79
     */
    @Overwrite
    private static <T> Pair<GeneratorOptions, Lifecycle> readGeneratorProperties(Dynamic<T> levelData, DataFixer dataFixer, int version) {
        Dynamic<T> dynamic = levelData.get("WorldGenSettings").orElseEmptyMap();
        UnmodifiableIterator var4 = GENERATOR_OPTION_KEYS.iterator();

        while(var4.hasNext()) {
            String string = (String)var4.next();
            Optional<? extends Dynamic<?>> optional = levelData.get(string).result();
            if (optional.isPresent()) {
                dynamic = dynamic.set(string, (Dynamic)optional.get());
            }
        }

        Dynamic<T> dynamic2 = dynamic; //dataFixer.update(TypeReferences.WORLD_GEN_SETTINGS, dynamic, version, SharedConstants.getGameVersion().getWorldVersion());
        DataResult<GeneratorOptions> string = GeneratorOptions.CODEC.parse(dynamic2);
        return Pair.of((GeneratorOptions)string.resultOrPartial(Util.addPrefix("WorldGenSettings: ", LOGGER::error)).orElseGet(() -> {
            DynamicRegistryManager dynamicRegistryManager = DynamicRegistryManager.createDynamicRegistryManager(dynamic2);
            return WorldPresets.createDefaultOptions(dynamicRegistryManager);
        }), string.lifecycle());
    }

    private static DataPackSettings method_29580(Dynamic<?> dynamic) {
        DataResult<DataPackSettings> var10000 = DataPackSettings.CODEC.parse(dynamic);
        return var10000.resultOrPartial((String s) -> {
            LOGGER.error(s);
        }).orElse(DataPackSettings.SAFE_MODE);
    }
}
