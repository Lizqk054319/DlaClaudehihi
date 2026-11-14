package pl.holovis.mixin.extra;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import pl.holovis.KosaTag;

/** Kosa Nametag nad głową gracza: neonowe K po lewej stronie nicku. */
@Mixin(PlayerEntity.class)
public abstract class PlayerDisplayNameMixin {

    @Inject(method = "getDisplayName", at = @At("RETURN"), cancellable = true, require = 0)
    private void holovis$tag(CallbackInfoReturnable<Text> cir) {
        if (KosaTag.isUser((PlayerEntity) (Object) this))
            if (!KosaTag.has(cir.getReturnValue())) cir.setReturnValue(KosaTag.tag(cir.getReturnValue()));
    }
}
