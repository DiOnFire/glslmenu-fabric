package glslmenu.mixins

import glslmenu.GlslMenu
import glslmenu.Shaders
import net.minecraft.client.render.GameRenderer
import net.minecraft.resource.ResourceFactory
import org.spongepowered.asm.mixin.Mixin
import org.spongepowered.asm.mixin.injection.At
import org.spongepowered.asm.mixin.injection.Inject
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo

@Mixin(GameRenderer::class)
class MixinGameRenderer {
    @Inject(
        method = ["preloadPrograms"],
        at = [At("HEAD")]
    )
    private fun preloadPrograms(
        factory : ResourceFactory,
        ci : CallbackInfo
    ) {
        val timestamp = System.currentTimeMillis()

        GlslMenu.LOGGER.info("Initializing GLSL menu...")
        GlslMenu.LOGGER.info("Looking for available shaders in .minecraft/glslmenu...")

        Shaders.createPrograms()
        Shaders.refreshRandomProgram()

        if(Shaders.PROGRAMS.isEmpty()) {
            GlslMenu.LOGGER.info("Did not found any shaders :<")
        } else {
            GlslMenu.LOGGER.info("Found ${Shaders.PROGRAMS.size} shaders!")
            GlslMenu.LOGGER.info("Selected ${Shaders.SELECTED_PROGRAM!!.name} as first shader")
        }

        GlslMenu.LOGGER.info("Initialized GLSL Menu! It took ${System.currentTimeMillis() - timestamp} ms!")
    }
}