package com.reelmakerai.ui.common

import android.net.Uri
import android.os.Bundle
import android.util.TypedValue
import android.view.*
import android.widget.*
import androidx.fragment.app.Fragment
import com.reelmakerai.R
import com.reelmakerai.ui.AIStudioActivity
import java.io.File

class VideoPreviewFragment : Fragment() {

    private lateinit var videoView: VideoView
    private var aspectRatio: Float = 0f
    private var videoUri: Uri? = null
    private var isPrepared = false

    companion object {
        fun newInstance(videoPath: String?, aspectRatio: Float): VideoPreviewFragment {
            val fragment = VideoPreviewFragment()
            val args = Bundle()
            args.putString("video_uri", videoPath)
            args.putFloat("aspect_ratio", aspectRatio)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_video_preview, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        videoView = view.findViewById(R.id.videoPreview)
        videoUri = arguments?.getString("video_uri")?.let { Uri.parse(it) }
        aspectRatio = arguments?.getFloat("aspect_ratio") ?: 0f

        if (videoUri != null && File(videoUri!!.path ?: "").exists()) {
            videoView.setVideoURI(videoUri)
            videoView.setOnPreparedListener {
                it.isLooping = false
                videoView.seekTo(0)
                isPrepared = true

                // Notify activity with duration
                (activity as? AIStudioActivity)?.updateDuration(
                    videoView.currentPosition,
                    videoView.duration
                )
            }


            videoView.setOnCompletionListener {
                (activity as? AIStudioActivity)?.onPlaybackCompleted()
            }
        }

        applyAspectRatio(aspectRatio)
    }

    private fun applyAspectRatio(ratio: Float) {
        val layoutParams = videoView.layoutParams
        val screenWidth = resources.displayMetrics.widthPixels

        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT
        layoutParams.height = if (ratio > 0f) {
            (screenWidth / ratio).toInt()
        } else {
            TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 360f, resources.displayMetrics
            ).toInt()
        }

        videoView.layoutParams = layoutParams
    }

    // ✅ Public control hooks
    fun play() {
        if (isPrepared) {
            videoView.start()
            (activity as? AIStudioActivity)?.onPlaybackStarted()
        }
    }

    fun pause() {
        if (isPrepared) videoView.pause()
    }

    fun isPlaying(): Boolean {
        return isPrepared && videoView.isPlaying
    }

    fun maximize() {
        val intent = android.content.Intent(requireContext(), com.reelmakerai.ui.FullscreenVideoActivity::class.java)
        intent.putExtra("video_uri", videoUri?.toString())
        intent.putExtra("video_position", videoView.currentPosition)
        startActivity(intent)
    }

    fun getCurrentPosition(): Int {
        return videoView.currentPosition
    }

    fun getDuration(): Int {
        return videoView.duration
    }
}
