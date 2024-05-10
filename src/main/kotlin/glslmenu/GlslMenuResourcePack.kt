package glslmenu

import net.minecraft.resource.InputSupplier
import net.minecraft.resource.ResourcePack
import net.minecraft.resource.ResourceType
import net.minecraft.resource.metadata.ResourceMetadataReader
import net.minecraft.util.Identifier
import java.io.InputStream

class GlslMenuResourcePack : ResourcePack {
    override fun close() { }

    override fun openRoot(
        vararg segments : String
    ) : InputSupplier<InputStream>? = null

    override fun open(
        type : ResourceType,
        id : Identifier
    ) : InputSupplier<InputStream>? = null

    override fun findResources(
        type : ResourceType?,
        namespace : String,
        prefix : String,
        consumer : ResourcePack.ResultConsumer
    ) { }

    override fun getNamespaces(
        type : ResourceType
    ) = mutableSetOf<String>()

    override fun <T : Any?> parseMetadata(
        metaReader : ResourceMetadataReader<T>
    ) : T? = null

    override fun getName() = "GLSL Menu"
}