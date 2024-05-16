package glslmenu

import net.minecraft.client.gui.screen.narration.NarrationMessageBuilder
import net.minecraft.client.gui.widget.PressableWidget
import net.minecraft.text.Text

class UpdateShadersListWidget(
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
        return Text.literal("Reload list")
    }

    override fun onPress() {
        Shaders.createPrograms()
    }

    override fun appendClickableNarrations(
        builder : NarrationMessageBuilder
    ) { }
}