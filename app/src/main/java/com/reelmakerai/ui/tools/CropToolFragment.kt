package com.reelmakerai.ui.tools

import android.net.Uri
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.reelmakerai.R
import com.reelmakerai.model.AspectRatio
import com.reelmakerai.session.CropSession
import com.reelmakerai.ui.adapter.AspectRatioAdapter
import com.reelmakerai.ui.custom.CropOverlayView

class CropToolFragment : Fragment() {

    private lateinit var videoView: VideoView
    private lateinit var cropOverlay: CropOverlayView
    private lateinit var ratioStrip: RecyclerView
    private lateinit var btnConfirm: ImageButton
    private lateinit var btnPlay: ImageButton

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.fragment_crop_tool, container, false)

        videoView = view.findViewById(R.id.cropVideoView)
        cropOverlay = view.findViewById(R.id.cropOverlay)
        ratioStrip = view.findViewById(R.id.ratioStrip)
        btnConfirm = view.findViewById(R.id.btnConfirm)
        btnPlay = view.findViewById(R.id.btnPlay)

        val videoUri = Uri.parse(requireActivity().intent.getStringExtra("video_uri"))
        videoView.setVideoURI(videoUri)
        videoView.setOnPreparedListener { mp ->
            mp.isLooping = true
            videoView.start()
        }

        setupAspectRatioStrip()
        setupControls()

        return view
    }

    private fun setupAspectRatioStrip() {
        val ratios = listOf(
            AspectRatio("Free", 0f),
            AspectRatio("Original", -1f),
            AspectRatio("1:1", 1f),
            AspectRatio("4:5", 0.8f),
            AspectRatio("9:16", 0.5625f)
        )

        val adapter = AspectRatioAdapter(ratios) { ratio ->
            cropOverlay.setAspectRatio(ratio)
        }

        ratioStrip.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        ratioStrip.adapter = adapter
    }

    private fun setupControls() {
        btnPlay.setOnClickListener {
            if (videoView.isPlaying) {
                videoView.pause()
            } else {
                videoView.start()
            }
        }

        btnConfirm.setOnClickListener {
            val cropRect = cropOverlay.getCropRect()
            val resolution = cropOverlay.getResolutionLabel()
            CropSession.commit(cropRect, resolution)
            Toast.makeText(requireContext(), "Crop saved", Toast.LENGTH_SHORT).show()
            requireActivity().onBackPressed()
        }
    }
}
