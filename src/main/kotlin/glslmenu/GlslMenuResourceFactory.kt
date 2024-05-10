package glslmenu

import net.minecraft.resource.Resource
import net.minecraft.resource.ResourceFactory
import net.minecraft.util.Identifier
import org.apache.commons.io.IOUtils
import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader
import java.nio.file.Files
import java.util.*

class GlslMenuResourceFactory(
    private val file : File
) : ResourceFactory {
    override fun getResource(
        id : Identifier
    ) = when {
        id.path.endsWith(".fsh") -> Optional.of(Resource(GlslMenu.RESOURCE_PACK) { IOUtils.toInputStream(Fixer.fixFsh(Files.readString(file.toPath()))) })
        else -> {
            var stream = try {
                javaClass.getResourceAsStream("/assets/${id.namespace}/${id.path}")
            } catch(e : Exception) {
                null
            }

            if(stream != null) {
                if(id.path.endsWith(".json")) {
                    stream = IOUtils.toInputStream(Fixer.fixJson(IOUtils.toString(stream), file.nameWithoutExtension.lowercase()))
                }

                Optional.of(Resource(GlslMenu.RESOURCE_PACK) { stream })
            } else {
                Optional.ofNullable<Resource>(null)
            }
        }
    }

    override fun openAsReader(
        id : Identifier
    ) : BufferedReader = when {
        id.path.endsWith(".fsh") -> BufferedReader(InputStreamReader(getResource(id).get().inputStream))
        id.path.endsWith(".json") && !id.path.endsWith("shader.json") -> openAsReader(Identifier("shaders/core/shader.json"))
        else -> getResourceOrThrow(id).reader
    }!!
}