package glslmenu.mixins

import com.mojang.blaze3d.systems.RenderSystem
import glslmenu.*
import net.minecraft.client.gui.DrawContext
import net.minecraft.client.gui.screen.Screen
import net.minecraft.client.gui.screen.TitleScreen
import net.minecraft.client.gui.widget.ButtonWidget
import net.minecraft.client.render.Tessellator
import net.minecraft.client.render.VertexFormat
import net.minecraft.client.render.VertexFormats
import net.minecraft.text.Text
import org.spongepowered.asm.mixin.Mixin
import org.spongepowered.asm.mixin.injection.At
import org.spongepowered.asm.mixin.injection.Inject
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo

@Mixin(TitleScreen::class)
class MixinTitleScreen : Screen(
    Text.literal("meow!")
) {
    @Inject(
        method = ["init"],
        at = [At("TAIL")]
    )
    private fun initTailHook(
        ci : CallbackInfo
    ) {
        addDrawableChild(ShaderButtonWidget(1, 1, 80, 18))
        addDrawableChild(UpdateShadersListWidget(1, 2 + 18, 80, 18))
        addDrawableChild(IntSliderWidget(1, 2 + 18 * 2, 80, 18, "Time Multiplier", 1, 1..5, GlslMenu.TIME_MULTIPLIER_SETTER)/* { GlslMenu.TIME_MULTIPLIER = it }*/)
    }

    @Inject(
        method = ["render"],
        at = [At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/gui/RotatingCubeMapRenderer;render(FF)V",
            shift = At.Shift.AFTER
        )]
    )
    private fun renderInvokeRotatingCubeMapRendererRenderHook(
        context : DrawContext,
        mouseX : Int,
        mouseY : Int,
        delta : Float,
        ci : CallbackInfo
    ) {
        if(Shaders.SELECTED_PROGRAM != null) {
            val tessellator = Tessellator.getInstance()
            val buffer = tessellator.buffer
            val shader = RenderSystem.getShader()
            val matrix = context.matrices.peek().positionMatrix

            Shaders.SELECTED_PROGRAM!!.updateUniforms()
            RenderSystem.setShader { Shaders.SELECTED_PROGRAM!! }

            buffer.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_COLOR)
            buffer.vertex(matrix, 0f, 0f, 0f).color(1f, 1f, 1f, 1f).next()
            buffer.vertex(matrix, 0f, height.toFloat(), 0f).color(1f, 1f, 1f, 1f).next()
            buffer.vertex(matrix, width.toFloat(), height.toFloat(), 0f).color(1f, 1f, 1f, 1f).next()
            buffer.vertex(matrix, width.toFloat(), 0f, 0f).color(1f, 1f, 1f, 1f).next()
            tessellator.draw()

            RenderSystem.setShader { shader }
        }
    }
}