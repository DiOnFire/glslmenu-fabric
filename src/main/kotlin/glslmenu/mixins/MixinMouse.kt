package glslmenu.mixins

import glslmenu.mouseX
import glslmenu.mouseY
import net.minecraft.client.Mouse
import org.spongepowered.asm.mixin.Mixin
import org.spongepowered.asm.mixin.injection.At
import org.spongepowered.asm.mixin.injection.Inject
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo

@Mixin(Mouse::class)
class MixinMouse {
    @Inject(
        method = ["onCursorPos"],
        at = [At("HEAD")]
    )
    private fun onCursorPosHeadHook(
        window : Long,
        x : Double,
        y : Double,
        ci : CallbackInfo
    ) {
        mouseX = x
        mouseY = y
    }
}