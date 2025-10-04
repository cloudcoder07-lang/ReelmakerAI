package com.reelmakerai.ui

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import com.reelmakerai.R

class ExportStatusView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : LinearLayout(context, attrs) {

    private val progressBar: ProgressBar
    private val statusText: TextView

    init {
        inflate(context, R.layout.view_export_status, this)
        progressBar = findViewById(R.id.exportProgress)
        statusText = findViewById(R.id.exportStatusText)
    }

    fun showStatus(message: String) {
        statusText.text = message
        progressBar.visibility = VISIBLE
    }

    fun hideStatus() {
        progressBar.visibility = GONE
    }
}
