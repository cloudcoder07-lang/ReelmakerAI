package com.reelmakerai.ui.tools

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.reelmakerai.R
import com.reelmakerai.model.CanvasTransform
import com.reelmakerai.model.ToolProfile
import com.reelmakerai.ui.AIStudioActivity

class CanvasToolFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.view_tool_canvas, container, false)
        val activity = activity as? AIStudioActivity

        // Apply tool profile for Canvas
        activity?.applyToolProfile(ToolProfile.CANVAS_ROTATE)

        // Manual tools
        view.findViewById<Button>(R.id.btnCanvasCrop).setOnClickListener {
            activity?.applyToolProfile(ToolProfile.CANVAS_CROP)
            activity?.launchToolEditor("Crop")
        }

        view.findViewById<Button>(R.id.btnCanvasRotate).setOnClickListener {
            activity?.applyCanvasTransform(CanvasTransform(rotationDegrees = 90))
        }

        view.findViewById<Button>(R.id.btnCanvasFlip).setOnClickListener {
            activity?.applyCanvasTransform(CanvasTransform(scaleX = -1f))
        }

        // AI tools
        view.findViewById<Button>(R.id.btnSmartCrop).setOnClickListener {
            activity?.applyToolProfile(ToolProfile.AI_SMARTCROP)
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

        return view
    }
}
