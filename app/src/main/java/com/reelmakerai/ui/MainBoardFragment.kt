package com.reelmakerai.ui

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.reelmakerai.databinding.FragmentMainBoardBinding
import com.reelmakerai.export.AIStudioActivity

class MainBoardFragment : Fragment() {

    private var _binding: FragmentMainBoardBinding? = null
    private val binding get() = _binding!!

    companion object {
        private const val REQUEST_VIDEO_CAPTURE = 1003
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBoardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnCreateVideo.setOnClickListener {
            val intent = Intent(MediaStore.ACTION_VIDEO_CAPTURE)
            startActivityForResult(intent, REQUEST_VIDEO_CAPTURE)
        }

        binding.btnCollage.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
            startActivityForResult(Intent.createChooser(intent, "Select Images"), 1002)
        }

        binding.btnSettings.setOnClickListener {
            // Placeholder for settings logic
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_VIDEO_CAPTURE && resultCode == Activity.RESULT_OK) {
            val videoUri = data?.data
            if (videoUri != null) {
                val intent = Intent(requireContext(), AIStudioActivity::class.java)
                intent.putExtra("video_uri", videoUri.toString())
                startActivity(intent)

            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
