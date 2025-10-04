package com.reelmakerai.export

import com.reelmakerai.audio.AudioMuxer
import java.io.File

class ExportManager {

    fun exportFinalVideo(videoInput: File, audioInput: File, output: File): Boolean {
        val muxer = AudioMuxer()
        return muxer.muxAudioVideo(videoInput, audioInput, output)
    }
}
