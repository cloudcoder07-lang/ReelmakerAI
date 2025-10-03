package com.reelmakerai.export.engine

import android.graphics.Bitmap
import android.opengl.GLES20
import android.opengl.GLES11Ext
import android.opengl.GLUtils
import com.reelmakerai.native.NativeRenderer

class LutVideoRenderer(private val lutBitmap: Bitmap) {

    private val shaderProgram: Int = NativeRenderer.createLutShaderProgram()
    private var lutTextureId: Int = 0

    init {
        lutTextureId = createLutTexture(lutBitmap)
    }

    private fun createLutTexture(bitmap: Bitmap): Int {
        val textures = IntArray(1)
        GLES20.glGenTextures(1, textures, 0)
        val textureId = textures[0]
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, textureId)
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_LINEAR)
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_LINEAR)
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_S, GLES20.GL_CLAMP_TO_EDGE)
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_T, GLES20.GL_CLAMP_TO_EDGE)
        GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, 0, bitmap, 0)
        return textureId
    }

    fun applyLut(inputTextureId: Int) {
        GLES20.glUseProgram(shaderProgram)

        // Bind input texture
        GLES20.glActiveTexture(GLES20.GL_TEXTURE0)
        GLES20.glBindTexture(GLES11Ext.GL_TEXTURE_EXTERNAL_OES, inputTextureId)
        GLES20.glUniform1i(GLES20.glGetUniformLocation(shaderProgram, "uInputTexture"), 0)

        // Bind LUT texture
        GLES20.glActiveTexture(GLES20.GL_TEXTURE1)
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, lutTextureId)
        GLES20.glUniform1i(GLES20.glGetUniformLocation(shaderProgram, "uLutTexture"), 1)

        // TODO: Add vertex buffer binding and draw call
    }

    fun release() {
        GLES20.glDeleteTextures(1, intArrayOf(lutTextureId), 0)
        GLES20.glDeleteProgram(shaderProgram)
    }
}
