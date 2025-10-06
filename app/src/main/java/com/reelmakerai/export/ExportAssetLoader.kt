package com.reelmakerai.export

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.net.URL

data class AssetPack(
    val name: String,
    val lutUrl: String,
    val watermarkUrl: String
)

data class AssetResult(
    val name: String,
    val lutBitmap: Bitmap?,
    val watermarkBitmap: Bitmap?
)

object AssetLoader {

    suspend fun loadAssets(context: Context, packs: List<AssetPack>): List<AssetResult> {
        return withContext(Dispatchers.IO) {
            packs.map { pack ->
                val lutBitmap = try {
                    val lutStream = URL(pack.lutUrl).openStream()
                    val lutBytes = lutStream.readBytes()
                    val lutFile = File(context.cacheDir, "${pack.name}_lut.png")
                    lutFile.writeBytes(lutBytes)
                    BitmapFactory.decodeFile(lutFile.absolutePath)
                } catch (e: Exception) {
                    Log.e("AssetLoader", "Failed to load LUT for ${pack.name}: ${e.message}")
                    null
                }

                val watermarkBitmap = try {
                    val wmStream = URL(pack.watermarkUrl).openStream()
                    val wmBytes = wmStream.readBytes()
                    val wmFile = File(context.cacheDir, "${pack.name}_wm.png")
                    wmFile.writeBytes(wmBytes)
                    BitmapFactory.decodeFile(wmFile.absolutePath)
                } catch (e: Exception) {
                    Log.e("AssetLoader", "Failed to load watermark for ${pack.name}: ${e.message}")
                    null
                }

                AssetResult(pack.name, lutBitmap, watermarkBitmap)
            }
        }
    }
}
