package glslmenu

import net.minecraft.client.gl.ShaderProgram
import net.minecraft.client.render.VertexFormats
import java.io.File

class FileShaderProgram(
    file : File,
    index : Int
) : ShaderProgram(
    GlslMenuResourceFactory(file),
    "shader$index",
    VertexFormats.POSITION_COLOR
) {
    @get:JvmName("getName0")
    val name = file.nameWithoutExtension
    private val timeUniform = getUniformOrDefault("time")
    private val mouseUniform = getUniformOrDefault("mouse")
    private val resolutionUniform = getUniformOrDefault("resolution")

    fun updateUniforms() {
        timeUniform.set(time)
        mouseUniform.set(mouseX.toFloat() * mc.window.scaledWidth / mc.window.width, mouseY.toFloat() * mc.window.scaledHeight / mc.window.height)
        resolutionUniform.set(1f, 1f)

        time += 0.01f * GlslMenu.TIME_MULTIPLIER.toFloat()
    }
}