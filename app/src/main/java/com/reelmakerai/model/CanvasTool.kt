package com.reelmakerai.model

import android.content.Context
import com.reelmakerai.R

object CanvasTool {
    fun getSubTools(context: Context): List<SubToolItem> = listOf(
        SubToolItem(
            name = "Resize",
            iconRes = R.drawable.ic_resize,
            label = context.getString(R.string.canvas_resize)
        ),
        SubToolItem(
            name = "Crop",
            iconRes = R.drawable.ic_crop,
            label = context.getString(R.string.canvas_crop)
        ),
        SubToolItem(
            name = "Rotate",
            iconRes = R.drawable.ic_rotate,
            label = context.getString(R.string.canvas_rotate)
        ),
        SubToolItem(
            name = "Flip",
            iconRes = R.drawable.ic_flip,
            label = context.getString(R.string.canvas_flip)
        ),
        SubToolItem(
            name = "Zoom",
            iconRes = R.drawable.ic_zoom,
            label = context.getString(R.string.canvas_zoom)
        ),
        SubToolItem(
            name = "BGFill",
            iconRes = R.drawable.ic_background,
            label = context.getString(R.string.canvas_bg_fill)
        )
    )
}
