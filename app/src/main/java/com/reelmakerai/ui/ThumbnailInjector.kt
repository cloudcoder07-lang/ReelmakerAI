package com.reelmakerai.ui

import android.app.Activity
import android.widget.ImageView
import com.reelmakerai.R

object ThumbnailInjector {
    fun inject(activity: Activity) {
        activity.findViewById<ImageView>(R.id.videoThumb)?.setImageResource(R.drawable.ic_video)
        activity.findViewById<ImageView>(R.id.photoThumb)?.setImageResource(R.drawable.ic_photo)
        activity.findViewById<ImageView>(R.id.collageThumb)?.setImageResource(R.drawable.ic_collage)
    }
}
