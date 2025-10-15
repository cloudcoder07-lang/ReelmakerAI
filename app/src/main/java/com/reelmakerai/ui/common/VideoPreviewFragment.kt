package com.reelmakerai.ui.common

import android.net.Uri
import android.os.Bundle
import android.util.TypedValue
import android.view.*
import android.widget.*
import androidx.fragment.app.Fragment
import com.reelmakerai.R

class VideoPreviewFragment : Fragment() {

    private lateinit var videoView: VideoView
    private var aspectRatio: Float = 0f // 0 = Fit

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_video_preview, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        videoView = view.findViewById(R.id.videoPreview)

        val videoUri = arguments?.getString("video_uri")?.let { Uri.parse(it) }
        aspectRatio = arguments?.getFloat("aspect_ratio") ?: 0f

        if (videoUri != null) {
            videoView.setVideoURI(videoUri)
            videoView.setOnPreparedListener {
                videoView.seekTo(1)
            }
        }

        applyAspectRatio(aspectRatio)
    }

    private fun applyAspectRatio(ratio: Float) {
        val layoutParams = videoView.layoutParams
        val screenWidth = resources.displayMetrics.widthPixels

        if (ratio > 0f) {
            layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT
            layoutParams.height = (screenWidth / ratio).toInt()
        } else {
            layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT
            layoutParams.height = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 360f, resources.displayMetrics
            ).toInt()
        }

        videoView.layoutParams = layoutParams
    }
}
