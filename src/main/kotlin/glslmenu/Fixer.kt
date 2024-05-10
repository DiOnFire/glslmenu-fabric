package glslmenu

object Fixer {
    private val CACHE = hashMapOf<String, String>()

    fun fixFsh(
        code : String
    ) : String {
        if(code.contains("#version 150")) {
            return code
        }

        if(CACHE.contains(code)) {
            return CACHE[code]!!
        }

        val fixedCode = "#version 150\n" +
                "in vec4 vertexColor;\n" +
                "in vec4 vertexCoord;\n" +
                "out vec4 fragColor;\n" +
                code.replace("gl_FragColor", "fragColor")
                    .replace("gl_FragCoord", "vertexCoord")

        CACHE[code] = fixedCode

        return fixedCode
    }

    fun fixJson(
        code : String,
        name : String
    ) = code.replace("FILE_SHADER", name)
}