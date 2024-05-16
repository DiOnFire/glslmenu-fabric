package glslmenu

import java.io.File
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

object Shaders {
    private var INDEX = 0
    val PROGRAMS = mutableListOf<FileShaderProgram>()
    var SELECTED_PROGRAM : FileShaderProgram? = null

    fun createPrograms() {
        val directory = File(mc.runDirectory, "glslmenu")
        val files = directory.listFiles()

        if (files == null) {
            Files.createDirectory(Paths.get(mc.runDirectory.path, "glslmenu"))
            return
        }

        for((index, file) in files.withIndex()) {
            val name = file.name

            if(name.endsWith(".fsh")) {
                val program = FileShaderProgram(file, index)

                PROGRAMS.add(program)
            }
        }
    }

    fun selectNextShader() {
        INDEX++

        if(INDEX == PROGRAMS.size) {
            INDEX = -1
            SELECTED_PROGRAM = null
        } else {
            SELECTED_PROGRAM = PROGRAMS[INDEX]
        }
    }

    fun refreshRandomProgram() {
        SELECTED_PROGRAM = PROGRAMS.randomOrNull()
    }

    fun refreshPrograms() {
        PROGRAMS.clear()

        createPrograms()
    }
}