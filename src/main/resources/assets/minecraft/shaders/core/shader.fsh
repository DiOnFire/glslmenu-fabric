#version 150

in vec4 vertexCoord;
in vec4 vertexColor;

uniform float time;
uniform vec2 mouse;
uniform vec2 resolution;

out vec4 fragColor;

void main() {
    fragColor = vertexColor;//vec4(1.0, 1.0, 1.0, 1.0);
}