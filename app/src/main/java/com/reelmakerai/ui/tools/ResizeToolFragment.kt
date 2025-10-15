package com.reelmakerai.ui.tools

import android.net.Uri
import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.reelmakerai.R
import com.reelmakerai.ui.common.VideoPreviewFragment

class ResizeToolFragment : Fragment() {

    private var selectedAspectRatio: Float = 0f
    private var selectedLabel: String = "Fit"
    private var videoUri: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_resize_tool, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        videoUri = arguments?.getString("video_uri")

        loadPreview(selectedAspectRatio)

        wireAspectButton(view, R.id.btnFit, 0f, "Fit")
        wireAspectButton(view, R.id.btn1_1, 1f, "1:1")
        wireAspectButton(view, R.id.btn9_16, 9f / 16f, "9:16")
        wireAspectButton(view, R.id.btn16_9, 16f / 9f, "16:9")
        wireAspectButton(view, R.id.btn2_3, 2f / 3f, "2:3")
        wireAspectButton(view, R.id.btn2_35_1, 2.35f / 1f, "2.35:1")
        wireAspectButton(view, R.id.btn2_1, 2f / 1f, "2:1")
        wireAspectButton(view, R.id.btn1_2, 1f / 2f, "1:2")
        wireAspectButton(view, R.id.btn3_4, 3f / 4f, "3:4")

        view.findViewById<View>(R.id.btnConfirmResize).setOnClickListener {
            Toast.makeText(requireContext(), "Saved: $selectedLabel", Toast.LENGTH_SHORT).show()
            // TODO: Save state to ViewModel or local cache
        }
    }

    private fun wireAspectButton(view: View, buttonId: Int, ratio: Float, label: String) {
        view.findViewById<Button>(buttonId).setOnClickListener {
            selectedAspectRatio = ratio
            selectedLabel = label
            loadPreview(ratio)
        }
    }

    private fun loadPreview(ratio: Float) {
        if (videoUri == null) return

        val previewFragment = VideoPreviewFragment().apply {
            arguments = Bundle().apply {
                putString("video_uri", videoUri)
                putFloat("aspect_ratio", ratio)
            }
        }

        childFragmentManager.beginTransaction()
            .replace(R.id.videoPreviewSlot, previewFragment)
            .commit()
    }
}
