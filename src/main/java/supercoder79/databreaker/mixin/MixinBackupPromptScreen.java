package supercoder79.databreaker.mixin;

import net.minecraft.client.font.MultilineText;
import net.minecraft.client.gui.screen.BackupPromptScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.CheckboxWidget;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(BackupPromptScreen.class)
public class MixinBackupPromptScreen extends Screen {

    @Mutable
    @Shadow @Final private Text subtitle;

    @Shadow @Final private Screen parent;

    @Shadow @Final protected BackupPromptScreen.Callback callback;

    @Shadow private CheckboxWidget eraseCacheCheckbox;

    @Shadow @Final private boolean showEraseCacheCheckbox;

    @Shadow private MultilineText wrappedText;

    protected MixinBackupPromptScreen(Text title) {
        super(title);
    }

    @Unique boolean dontStop;

    @Inject(method = "<init>", at = @At("RETURN"))
    private void hookConstructor(Screen parent, BackupPromptScreen.Callback callback, Text title, Text subtitle, boolean showEraseCacheCheckBox, CallbackInfo ci) {
        if (title.toString().contains("selectWorld.backupQuestion.experimental")) {
            dontStop = true;
        } else {
            this.subtitle = Text.literal("You cannot load older worlds with DataBreaker installed. Please remove DataBreaker to load the world.");
        }
    }

    /**
     * @reason Stop users from loading old worlds. They're better off this way.
     * @author SuperCoder79
     */
    @Overwrite
    public void init() {
        if (!dontStop) {
            super.init();
            this.wrappedText = MultilineText.create(this.textRenderer, this.subtitle, this.width - 50);
            int var10000 = this.wrappedText.count() + 1;
            this.textRenderer.getClass();
            int i = var10000 * 9;
            this.addDrawableChild(new ButtonWidget(this.width / 2 - 155 + 80, 124 + i, 150, 20, ScreenTexts.CANCEL, (buttonWidget) -> {
                this.client.setScreen(this.parent);
            }));
        } else {
            this.wrappedText = MultilineText.create(this.textRenderer, this.subtitle, this.width - 50);
            int var10000 = this.wrappedText.count() + 1;
            this.textRenderer.getClass();
            int i = var10000 * 9;
            this.addDrawableChild(new ButtonWidget(this.width / 2 - 155, 100 + i, 150, 20, Text.translatable("selectWorld.backupJoinConfirmButton"), (buttonWidget) -> {
                this.callback.proceed(true, this.eraseCacheCheckbox.isChecked());
            }));
            this.addDrawableChild(new ButtonWidget(this.width / 2 - 155 + 160, 100 + i, 150, 20, Text.translatable("selectWorld.backupJoinSkipButton"), (buttonWidget) -> {
                this.callback.proceed(false, this.eraseCacheCheckbox.isChecked());
            }));
            this.addDrawableChild(new ButtonWidget(this.width / 2 - 155 + 80, 124 + i, 150, 20, ScreenTexts.CANCEL, (buttonWidget) -> {
                this.client.setScreen(this.parent);
            }));
            this.eraseCacheCheckbox = new CheckboxWidget(this.width / 2 - 155 + 80, 76 + i, 150, 20, Text.translatable("selectWorld.backupEraseCache"), false);
            if (this.showEraseCacheCheckbox) {
                this.addDrawableChild(this.eraseCacheCheckbox);
            }

        }
    }
}
