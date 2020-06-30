package supercoder79.databreaker.mixin;

import net.minecraft.client.gui.screen.BackupPromptScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ScreenTexts;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.CheckboxWidget;
import net.minecraft.text.LiteralText;
import net.minecraft.text.StringRenderable;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(BackupPromptScreen.class)
public class MixinBackupPromptScreen extends Screen {

    @Shadow @Final private List<StringRenderable> wrappedText;

    @Mutable
    @Shadow @Final private Text subtitle;

    @Shadow @Final private Screen parent;

    @Shadow @Final protected BackupPromptScreen.Callback callback;

    @Shadow private CheckboxWidget eraseCacheCheckbox;

    @Shadow @Final private boolean showEraseCacheCheckbox;

    protected MixinBackupPromptScreen(Text title) {
        super(title);
    }

    @Unique boolean dontStop;

    @Inject(method = "<init>", at = @At("RETURN"))
    private void hookConstructor(Screen parent, BackupPromptScreen.Callback callback, Text title, Text subtitle, boolean showEraseCacheCheckBox, CallbackInfo ci) {
        if (title.toString().contains("selectWorld.backupQuestion.experimental")) {
            dontStop = true;
        } else {
            this.subtitle = new LiteralText("You cannot load older worlds with DataBreaker installed. Please remove DataBreaker to load the world.");
        }
    }

    /**
     * @reason Stop users from loading old worlds. They're better off this way.
     * @author SuperCoder79
     */
    @Overwrite
    public void init() {
        if (!dontStop) {
            this.wrappedText.clear();
            this.wrappedText.addAll(this.textRenderer.wrapLines(this.subtitle, this.width - 50));
            int var10000 = this.wrappedText.size() + 1;
            this.textRenderer.getClass();
            int i = var10000 * 9;
            this.addButton(new ButtonWidget(this.width / 2 - 155 + 80, 124 + i, 150, 20, ScreenTexts.CANCEL, (buttonWidget) -> {
                this.client.openScreen(this.parent);
            }));
        } else {
            this.wrappedText.clear();
            this.wrappedText.addAll(this.textRenderer.wrapLines(this.subtitle, this.width - 50));
            int var10000 = this.wrappedText.size() + 1;
            this.textRenderer.getClass();
            int i = var10000 * 9;
            this.addButton(new ButtonWidget(this.width / 2 - 155, 100 + i, 150, 20, new TranslatableText("selectWorld.backupJoinConfirmButton"), (buttonWidget) -> {
                this.callback.proceed(true, this.eraseCacheCheckbox.isChecked());
            }));
            this.addButton(new ButtonWidget(this.width / 2 - 155 + 160, 100 + i, 150, 20, new TranslatableText("selectWorld.backupJoinSkipButton"), (buttonWidget) -> {
                this.callback.proceed(false, this.eraseCacheCheckbox.isChecked());
            }));
            this.addButton(new ButtonWidget(this.width / 2 - 155 + 80, 124 + i, 150, 20, ScreenTexts.CANCEL, (buttonWidget) -> {
                this.client.openScreen(this.parent);
            }));
            this.eraseCacheCheckbox = new CheckboxWidget(this.width / 2 - 155 + 80, 76 + i, 150, 20, new TranslatableText("selectWorld.backupEraseCache"), false);
            if (this.showEraseCacheCheckbox) {
                this.addButton(this.eraseCacheCheckbox);
            }
        }
    }
}
