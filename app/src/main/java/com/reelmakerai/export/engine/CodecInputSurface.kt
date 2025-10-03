package com.reelmakerai.export.engine

import android.view.Surface
import android.opengl.EGLSurface

class CodecInputSurface(private val surface: Surface) {

    private val eglCore = EglCore()
    private var eglSurface: EGLSurface? = null

    fun setup() {
        eglCore.init()
        eglSurface = eglCore.createWindowSurface(surface)
        eglCore.makeCurrent(eglSurface!!)
    }

    fun swapBuffers() {
        eglCore.swapBuffers(eglSurface!!)
    }

    fun release() {
        eglSurface?.let { eglCore.releaseSurface(it) }
        eglCore.release()
        surface.release()
    }
}
