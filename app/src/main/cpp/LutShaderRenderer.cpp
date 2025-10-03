#include <GLES2/gl2.h>
#include <GLES2/gl2ext.h>

extern "C" {

GLuint createLutShaderProgram() {
    const char* vertexShaderSrc = R"(
        attribute vec4 aPosition;
        attribute vec2 aTexCoord;
        varying vec2 vTexCoord;
        void main() {
            gl_Position = aPosition;
            vTexCoord = aTexCoord;
        }
    )";

    const char* fragmentShaderSrc = R"(
        precision mediump float;
        varying vec2 vTexCoord;
        uniform sampler2D uInputTexture;
        uniform sampler2D uLutTexture;

        void main() {
            vec4 color = texture2D(uInputTexture, vTexCoord);
            float blueColor = color.b * 63.0;
            vec2 lutPos = vec2(
                mod(blueColor, 8.0) / 8.0 + color.r / 8.0,
                floor(blueColor / 8.0) / 8.0 + color.g / 8.0
            );
            gl_FragColor = texture2D(uLutTexture, lutPos);
        }
    )";

    GLuint vertexShader = glCreateShader(GL_VERTEX_SHADER);
    glShaderSource(vertexShader, 1, &vertexShaderSrc, nullptr);
    glCompileShader(vertexShader);

    GLuint fragmentShader = glCreateShader(GL_FRAGMENT_SHADER);
    glShaderSource(fragmentShader, 1, &fragmentShaderSrc, nullptr);
    glCompileShader(fragmentShader);

    GLuint program = glCreateProgram();
    glAttachShader(program, vertexShader);
    glAttachShader(program, fragmentShader);
    glLinkProgram(program);

    return program;
}

}
