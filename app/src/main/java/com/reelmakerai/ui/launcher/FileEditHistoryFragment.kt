package com.reelmakerai.ui.launcher

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.reelmakerai.R
import com.reelmakerai.data.EditHistoryRepository
import com.reelmakerai.model.VideoEditState
import com.reelmakerai.ui.AIStudioActivity

class FileEditHistoryFragment : Fragment() {

    companion object {
        private const val VIDEO_PICK_REQUEST = 101
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_file_edit_history, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val btnNew = view.findViewById<Button>(R.id.btnNewVideo)
        val recycler = view.findViewById<RecyclerView>(R.id.historyRecyclerView)

        val history = EditHistoryRepository.loadHistory()

        if (history.isNotEmpty()) {
            recycler.layoutManager = LinearLayoutManager(requireContext())
            recycler.adapter = EditHistoryAdapter(history) { state ->
                val intent = Intent(requireContext(), AIStudioActivity::class.java).apply {
                    putExtra("video_uri", state.videoUri.toString())
                    putExtra("aspect_ratio", state.aspectRatio)
                }
                startActivity(intent)
            }
            recycler.visibility = View.VISIBLE
        } else {
            recycler.visibility = View.GONE
        }

        btnNew.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK).apply {
                type = "video/*"
            }
            startActivityForResult(intent, VIDEO_PICK_REQUEST)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == VIDEO_PICK_REQUEST && resultCode == Activity.RESULT_OK) {
            val videoUri: Uri? = data?.data
            if (videoUri != null) {
                val intent = Intent(requireContext(), AIStudioActivity::class.java).apply {
                    putExtra("video_uri", videoUri.toString())
                    putExtra("aspect_ratio", "16:9") // Default or placeholder
                }
                startActivity(intent)
            } else {
                Toast.makeText(requireContext(), "No video selected", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
