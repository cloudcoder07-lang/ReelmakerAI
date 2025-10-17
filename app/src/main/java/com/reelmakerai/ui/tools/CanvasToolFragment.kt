package com.reelmakerai.ui.tools

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.VideoView
import androidx.fragment.app.Fragment
import com.reelmakerai.R
import com.reelmakerai.model.CanvasTransform
import com.reelmakerai.model.ToolProfile
import com.reelmakerai.session.EditSession
import com.reelmakerai.ui.AIStudioActivity
import com.reelmakerai.util.EditHistoryUtils
import com.reelmakerai.util.SnapshotManager
import com.reelmakerai.util.VideoPreviewUtils
import java.io.File

class CanvasToolFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.view_tool_canvas, container, false)
        val activity = activity as? AIStudioActivity ?: return view

        // Apply tool profile for Canvas
        activity.applyToolProfile(ToolProfile.CANVAS_ROTATE)

        // Load snapshot preview
        val videoView = view.findViewById<VideoView>(R.id.videoPreview)
        VideoPreviewUtils.loadSnapshotIntoPreview(videoView)

        // Manual tools
        view.findViewById<Button>(R.id.btnCanvasCrop).setOnClickListener {
            activity.applyToolProfile(ToolProfile.CANVAS_CROP)
            activity.launchToolEditor("Crop")
        }

        view.findViewById<Button>(R.id.btnCanvasRotate).setOnClickListener {
            activity.applyCanvasTransform(CanvasTransform(rotationDegrees = 90))
        }

        view.findViewById<Button>(R.id.btnCanvasFlip).setOnClickListener {
            activity.applyCanvasTransform(CanvasTransform(scaleX = -1f))
        }

        // AI tools
        view.findViewById<Button>(R.id.btnSmartCrop).setOnClickListener {
            activity.applyToolProfile(ToolProfile.AI_SMARTCROP)
            // TODO: Run SmartCropProcessor and update videoView
        }

        view.findViewById<Button>(R.id.btnBgReplace).setOnClickListener {
            // TODO: Run BGReplaceProcessor and update videoView
        }

        view.findViewById<Button>(R.id.btnAiZoom).setOnClickListener {
            // TODO: Run AI Zoom logic and apply zoom transform
        }

        view.findViewById<Button>(R.id.btnStyleTransfer).setOnClickListener {
            // TODO: Run StyleTransferEngine and apply filter preview
        }

        // Tick-to-commit logic
        val tick = activity.findViewById<View>(R.id.btnTick)
        tick.setOnClickListener {
            val snapshotUri = EditSession.snapshotPath?.let { Uri.fromFile(File(it)) } ?: return@setOnClickListener
            val aspectRatio = activity.intent.getFloatExtra("aspect_ratio", 1f)

            EditHistoryUtils.smartSave(snapshotUri, aspectRatio, toolUsed = "Canvas")
            SnapshotManager.commitTempEdit()
            activity.onBackPressed()
        }

        val cancel = activity.findViewById<View>(R.id.btnCancel)
        cancel.setOnClickListener {
            SnapshotManager.discardTempEdit()
            activity.onBackPressed()
        }

        return view
    }
}
