package com.reelmakerai.ui.tools

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.reelmakerai.R

class CutToolFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.view_tool_cut, container, false)

        view.findViewById<Button>(R.id.btnSetStart).setOnClickListener {
            Toast.makeText(requireContext(), "Start time set", Toast.LENGTH_SHORT).show()
        }

        view.findViewById<Button>(R.id.btnSetEnd).setOnClickListener {
            Toast.makeText(requireContext(), "End time set", Toast.LENGTH_SHORT).show()
        }

        view.findViewById<Button>(R.id.btnPreviewCut).setOnClickListener {
            Toast.makeText(requireContext(), "Preview cut triggered", Toast.LENGTH_SHORT).show()
        }

        return view
    }
}
