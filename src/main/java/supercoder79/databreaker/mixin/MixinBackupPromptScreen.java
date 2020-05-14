package supercoder79.databreaker.mixin;

import net.minecraft.client.gui.screen.BackupPromptScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ScreenTexts;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(BackupPromptScreen.class)
public class MixinBackupPromptScreen extends Screen {

    @Shadow @Final private List<Text> wrappedText;

    @Mutable
    @Shadow @Final private Text subtitle;

    @Shadow @Final private Screen parent;

    protected MixinBackupPromptScreen(Text title) {
        super(title);
    }

    @Inject(method = "<init>", at = @At("RETURN"))
    private void hookConstructor(Screen parent, BackupPromptScreen.Callback callback, Text title, Text subtitle, boolean showEraseCacheCheckBox, CallbackInfo ci) {
        this.subtitle = new LiteralText("You cannot load older worlds with DataBreaker installed. Please remove DataBreaker to load the world.");
    }

    /**
     * @author
     */
    @Overwrite
    public void init() {
        this.wrappedText.clear();
        this.wrappedText.addAll(this.textRenderer.wrapLines(this.subtitle, this.width - 50));
        int var10000 = this.wrappedText.size() + 1;
        this.textRenderer.getClass();
        int i = var10000 * 9;
        this.addButton(new ButtonWidget(this.width / 2 - 155 + 80, 124 + i, 150, 20, ScreenTexts.CANCEL, (buttonWidget) -> {
            this.client.openScreen(this.parent);
        }));

    }
}
