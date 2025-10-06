package com.reelmakerai.ui

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.reelmakerai.databinding.FragmentExportBinding
import com.reelmakerai.export.*
import com.reelmakerai.export.engine.VideoExportEngine
import com.reelmakerai.overlay.OverlayItem
import com.reelmakerai.pro.ProFeatureManager
import jp.co.cyberagent.android.gpuimage.GPUImageView
import jp.co.cyberagent.android.gpuimage.filter.GPUImageLookupFilter
import java.io.File
import java.net.URL

class ExportFragment : Fragment() {

    private var _binding: FragmentExportBinding? = null
    private val binding get() = _binding!!

    private var selectedMood: String? = null
    private var lutUrl: String? = null

    companion object {
        private const val ARG_MOOD = "arg_mood"

        fun newInstance(mood: String): ExportFragment {
            val fragment = ExportFragment()
            val args = Bundle()
            args.putString(ARG_MOOD, mood)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        selectedMood = arguments?.getString(ARG_MOOD)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentExportBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.moodLabel.text = "Mood: ${selectedMood ?: "Unknown"}"
        showProgress("Preparing preview...")

        val moodToLutUrl = mapOf(
            "Happy" to "https://cdn.jsdelivr.net/gh/cloudcoder07/reelmaker-assets/luts/lut_happy.png",
            "Melancholy" to "https://cdn.jsdelivr.net/gh/cloudcoder07/reelmaker-assets/luts/lut_melancholy.png",
            "Epic" to "https://cdn.jsdelivr.net/gh/cloudcoder07/reelmaker-assets/luts/lut_epic.png",
            "Romantic" to "https://cdn.jsdelivr.net/gh/cloudcoder07/reelmaker-assets/luts/lut_romantic.png",
            "Dark" to "https://cdn.jsdelivr.net/gh/cloudcoder07/reelmaker-assets/luts/lut_dark.png"
        )

        lutUrl = moodToLutUrl[selectedMood]
        if (lutUrl != null) {
            applyLutToPreview(binding.gpuPreview, lutUrl!!)
        } else {
            showError("No LUT found for mood: $selectedMood")
        }

        binding.exportButton.setOnClickListener {
            if (lutUrl != null) {
                startExportFlow(lutUrl!!)
            } else {
                showError("Cannot export: LUT missing")
            }
        }
    }

    private fun applyLutToPreview(gpuImageView: GPUImageView, lutUrl: String) {
        val fallbackBitmap = Bitmap.createBitmap(300, 300, Bitmap.Config.ARGB_8888).apply {
            eraseColor(Color.DKGRAY)
        }
        gpuImageView.setImage(fallbackBitmap)

        Thread {
            try {
                val input = URL(lutUrl).openStream()
                val lutBitmap = BitmapFactory.decodeStream(input)

                val filter = GPUImageLookupFilter()
                filter.bitmap = lutBitmap

                requireActivity().runOnUiThread {
                    gpuImageView.filter = filter
                    showProgress("Preview ready")
                }
            } catch (e: Exception) {
                requireActivity().runOnUiThread {
                    showError("Failed to load LUT: ${e.message}")
                }
            }
        }.start()
    }

    private fun startExportFlow(lutUrl: String) {
        showProgress("Exporting video...")

        Thread {
            try {
                val inputPath = "/sdcard/input.mp4" // Replace with actual input path
                val outputPath = ExportUtils.getTempExportPath(requireContext())
                val lutBitmap = BitmapFactory.decodeStream(URL(lutUrl).openStream())

                val watermark = try {
                    BitmapFactory.decodeStream(requireContext().assets.open("watermark/logo_white.png"))
                } catch (e: Exception) {
                    null
                }

                val overlays = listOf<OverlayItem>()
                val exportHD = !ProFeatureManager.isFeatureLocked("export_hd")

                val task = ExportTask(
                    inputPath = inputPath,
                    outputPath = outputPath,
                    lutBitmap = lutBitmap,
                    mood = selectedMood ?: "Unknown",
                    overlays = overlays,
                    watermarkBitmap = watermark,
                    exportHD = exportHD,
                    voiceFXEnabled = false
                )

                VideoExportEngine.export(requireContext(), task, object : ExportCallback {
                    override fun onProgress(percent: Int) {
                        requireActivity().runOnUiThread {
                            updateProgress(percent)
                        }
                    }

                    override fun onComplete(outputFile: File) {
                        requireActivity().runOnUiThread {
                            showCompleted()

                            val shareIntent = Intent(Intent.ACTION_SEND).apply {
                                type = "video/mp4"
                                putExtra(Intent.EXTRA_STREAM, Uri.fromFile(outputFile))
                                addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                            }

                            val chooser = Intent.createChooser(shareIntent, "Share your cinematic reel")
                            startActivity(chooser)
                        }
                    }

                    override fun onError(message: String) {
                        requireActivity().runOnUiThread {
                            showError(message)
                        }
                    }
                })

            } catch (e: Exception) {
                requireActivity().runOnUiThread {
                    showError("Export failed: ${e.message}")
                }
            }
        }.start()
    }

    private fun showProgress(message: String) {
        binding.exportStatusView?.visibility = View.VISIBLE
        binding.exportStatusView?.setMessage(message)
    }

    private fun showError(message: String) {
        binding.exportStatusView?.visibility = View.VISIBLE
        binding.exportStatusView?.setMessage("Error: $message")
    }

    private fun updateProgress(percent: Int) {
        binding.exportStatusView?.setMessage("Exporting... $percent%")
    }

    private fun showCompleted() {
        binding.exportStatusView?.setMessage("Export complete!")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
