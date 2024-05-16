package glslmenu

import net.minecraft.client.gui.DrawContext
import net.minecraft.client.gui.screen.narration.NarrationMessageBuilder
import net.minecraft.client.gui.widget.PressableWidget
import net.minecraft.text.Text

class ShaderButtonWidget(
    x : Int,
    y : Int,
    width : Int,
    height : Int
) : PressableWidget(
    x,
    y,
    width,
    height,
    Text.empty()
) {
    override fun getMessage(): Text {
        return Text.literal(if (Shaders.PROGRAMS.isEmpty()) "No shaders found" else "Shader: ${Shaders.SELECTED_PROGRAM?.name}")
    }

    override fun onPress() {
        if (Shaders.PROGRAMS.isNotEmpty()) {
            Shaders.selectNextShader()
        }
    }

    override fun renderWidget(context: DrawContext?, mouseX: Int, mouseY: Int, delta: Float) {
        active = Shaders.PROGRAMS.isNotEmpty()
        super.renderWidget(context, mouseX, mouseY, delta)
    }

    override fun appendClickableNarrations(
        builder : NarrationMessageBuilder
    ) { }
}