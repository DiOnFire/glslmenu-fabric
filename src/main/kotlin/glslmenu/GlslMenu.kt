package glslmenu

import net.fabricmc.api.ModInitializer
import org.apache.logging.log4j.LogManager

object GlslMenu : ModInitializer {
    val LOGGER = LogManager.getLogger("GLSL Menu")!!
    val RESOURCE_PACK = GlslMenuResourcePack()

    var TIME_MULTIPLIER = 1
    //Caused by: org.spongepowered.asm.mixin.transformer.throwables.IllegalClassLoadError: glslmenu.mixins.MixinTitleScreen$initTailHook$1 is in a defined mixin package glslmenu.mixins.* owned by glslmenu.mixins.json and cannot be referenced directly
    val TIME_MULTIPLIER_SETTER : (Int) -> Unit = { TIME_MULTIPLIER = it }

    override fun onInitialize() { }
}