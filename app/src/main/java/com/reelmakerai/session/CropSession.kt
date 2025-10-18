package com.reelmakerai.session

import android.graphics.RectF

object CropSession {
    var lastCropRect: RectF? = null
    var lastResolution: String? = null

    fun commit(rect: RectF, resolution: String) {
        lastCropRect = rect
        lastResolution = resolution
        // TODO: Persist crop state or apply to video
    }
}
