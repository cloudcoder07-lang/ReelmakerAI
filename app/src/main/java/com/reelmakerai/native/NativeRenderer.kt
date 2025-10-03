package com.reelmakerai.native

object NativeRenderer {
    init {
        System.loadLibrary("reelmaker_native")
    }

    external fun createLutShaderProgram(): Int
    external fun drawWatermark(watermarkTexId: Int, x: Float, y: Float, scale: Float)
}
