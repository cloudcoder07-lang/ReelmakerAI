package com.reelmakerai.export

import android.content.Context
import java.io.File
import com.reelmakerai.assets.AssetManager  // ✅ Add this import

object AssetLoader {

    suspend fun loadPresetAssets(context: Context, preset: ExportPreset): Pair<File, File> {
        val lutFile = AssetManager.fetchFromCDN(context, "${preset.name}_lut.png", preset.lutUrl)
        val watermarkFile = AssetManager.fetchFromCDN(context, "${preset.name}_wm.png", preset.watermarkUrl)
        return lutFile to watermarkFile
    }
}
