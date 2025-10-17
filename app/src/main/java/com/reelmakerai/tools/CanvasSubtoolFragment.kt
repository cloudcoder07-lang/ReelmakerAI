package com.reelmakerai.ui.tools

import android.os.Bundle
import android.view.View
import android.widget.VideoView
import androidx.fragment.app.Fragment
import com.reelmakerai.R
import com.reelmakerai.util.SnapshotManager
import com.reelmakerai.util.VideoPreviewUtils
import com.reelmakerai.util.EditHistoryUtils
import com.reelmakerai.session.EditSession
import android.net.Uri
import java.io.File




class CanvasSubtoolFragment : Fragment(R.layout.fragment_canvas_subtool) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val tick = requireActivity().findViewById<View>(R.id.btnTick)
        val cancel = requireActivity().findViewById<View>(R.id.btnCancel)
        val videoView = view.findViewById<VideoView>(R.id.videoPreview)

        // ✅ Load snapshot preview
        VideoPreviewUtils.loadSnapshotIntoPreview(videoView)

        tick.setOnClickListener {
            val snapshotUri = EditSession.snapshotPath?.let { Uri.fromFile(File(it)) } ?: return@setOnClickListener
            val aspectRatio = requireActivity().intent.getFloatExtra("aspect_ratio", 1f)

            // ✅ Save edit history
            EditHistoryUtils.smartSave(snapshotUri, aspectRatio, toolUsed = "Canvas")

            SnapshotManager.commitTempEdit()
            requireActivity().onBackPressed()
        }

        cancel.setOnClickListener {
            SnapshotManager.discardTempEdit()
            requireActivity().onBackPressed()
        }
    }

}
