package com.reelmakerai.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.reelmakerai.databinding.ViewExportStatusBinding

class ExportStatusView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val binding: ViewExportStatusBinding =
        ViewExportStatusBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        binding.progressBar.progress = 0
        binding.statusMessage.text = "Ready to export"
    }

    fun showProgress() {
        binding.progressBar.visibility = VISIBLE
        binding.statusMessage.text = "Exporting..."
    }

    fun updateProgress(percent: Int) {
        binding.progressBar.progress = percent
        binding.statusMessage.text = "Exporting... $percent%"
    }

    fun showCompleted() {
        binding.progressBar.progress = 100
        binding.statusMessage.text = "Export complete!"
    }

    fun showError(message: String) {
        binding.statusMessage.text = "Export failed: $message"
    }
}
