#version 150

in vec3 Position;
in vec4 Color;

uniform mat4 ModelViewMat;
uniform mat4 ProjMat;

out vec4 vertexColor;
out vec4 vertexCoord;

void main() {
    gl_Position = ProjMat * ModelViewMat * vec4(Position, 1.0);

    vertexColor = Color;
    vertexCoord = vec4((gl_Position.xy + 0.5 * 1.75) / 1.75, gl_Position.zw);
}