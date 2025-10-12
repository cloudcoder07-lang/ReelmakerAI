package com.reelmakerai.native

object NativeBridge {
    init {
        System.loadLibrary("subtools") // Loads libsubtools.so
    }

    external fun cropVideoNative(videoPath: String, cropMode: String): Boolean
    external fun resizeVideoNative(videoPath: String, targetRatio: String): Boolean
}
