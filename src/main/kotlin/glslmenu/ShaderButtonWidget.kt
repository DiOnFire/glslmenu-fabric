package glslmenu

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
    override fun getMessage() = Text.literal("Shader: ${Shaders.SELECTED_PROGRAM?.name}")!!

    override fun onPress() {
        Shaders.selectNextShader()
    }

    override fun appendClickableNarrations(
        builder : NarrationMessageBuilder
    ) { }
}