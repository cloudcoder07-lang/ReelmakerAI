package com.reelmakerai.ui.tools

import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import com.reelmakerai.R
import com.reelmakerai.model.CanvasTransform
import com.reelmakerai.ui.AIStudioActivity

class RotateToolFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.fragment_rotate_tool, container, false)
        val activity = activity as? AIStudioActivity ?: return view

        val rotateLeft = Button(requireContext()).apply {
            text = "Rotate Left"
            setOnClickListener {
                activity.applyCanvasTransform(CanvasTransform(rotationDegrees = -90))
            }
        }

        val rotateRight = Button(requireContext()).apply {
            text = "Rotate Right"
            setOnClickListener {
                activity.applyCanvasTransform(CanvasTransform(rotationDegrees = 90))
            }
        }

        val saveButton = Button(requireContext()).apply {
            text = "Save"
            setOnClickListener {
                activity.onToolSave("Rotate")
            }
        }

        val discardButton = Button(requireContext()).apply {
            text = "Discard"
            setOnClickListener {
                activity.onToolDiscard()
            }
        }

        val containerLayout = LinearLayout(requireContext()).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            addView(rotateLeft)
            addView(rotateRight)
            addView(saveButton)
            addView(discardButton)
        }

        return containerLayout
    }
}
