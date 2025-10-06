package com.reelmakerai.ui.components

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.reelmakerai.R

class ExportStatusView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : ConstraintLayout(context, attrs) {

    private val statusText: TextView

    init {
        View.inflate(context, R.layout.view_export_status, this)
        statusText = findViewById(R.id.statusText)
    }

    fun setMessage(message: String) {
        statusText.text = message
        visibility = View.VISIBLE
    }
}
