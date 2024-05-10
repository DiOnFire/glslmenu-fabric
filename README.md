<div align="center">

# GLSL Menu

Mod that renders GLSL shader as background in main menu

Made for fabric 1.20.4

Port of [old glslmenu](https://github.com/toiletgaming/GLSLMenu)

Requires [fabric language kotlin](https://modrinth.com/mod/fabric-language-kotlin)

# How to get shader?

Open [glslsandbox](https://glslsandbox.com), find and open shader you need, create `XXX.fsh` file in `.minecraft/glslmenu` and just copy paste code from website to file

# Minecraft crashes?

If minecraft crashes before main menu is shown, please create an issue and attach `.minecraft/logs/latest.log` and zip archive of your shaders

# Introduction into API

Shader renderer is based on minecraft's `ShaderProgram` class

### Using gl_FragColor

I do not recommend using `gl_FragColor`/`gl_FragCoord`, cuz they do not exist

Mod replaces them with their analogues, so shaders from [glslsandbox](https://glslsandbox.com) are supported

### Using our API

If you want to make shader directly for **this** mod, please use this code as template

</div>

```glsl
#version 150

in vec4 vertexCoord;//Position of pixel(ranges from vec2(0.0) to vec2(1.0)
in vec4 vertexColor;//Original color of pixel(almost useless)

uniform float time;//Current time for animations
uniform vec2 mouse;//Cursor position
uniform vec2 resolution;//Resolution of screen, vec2(1.0)

out vec4 fragColor;//New color of pixel
```