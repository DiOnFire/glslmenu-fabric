package glslmenu

import net.minecraft.client.gui.DrawContext
import net.minecraft.client.gui.widget.SliderWidget
import net.minecraft.text.Text

class IntSliderWidget(
    x : Int,
    y : Int,
    width : Int,
    height : Int,
    private val name : String,
    default : Int,
    private val range : ClosedRange<Int>,
    private val setter : (Int) -> Unit
) : SliderWidget(
    x,
    y,
    width,
    height,
    Text.literal("$name: $default"),
    default.toDouble() / (range.endInclusive.toDouble() - range.start.toDouble())
) {
    private var intValue = 0

    override fun updateMessage() {
        message = Text.literal("$name: $intValue")
    }

    override fun renderWidget(context: DrawContext?, mouseX: Int, mouseY: Int, delta: Float) {
        active = Shaders.PROGRAMS.isNotEmpty()
        super.renderWidget(context, mouseX, mouseY, delta)
    }

    override fun applyValue() {
        intValue = (value * (range.endInclusive - range.start)).toInt()

        setter(intValue)
    }
}