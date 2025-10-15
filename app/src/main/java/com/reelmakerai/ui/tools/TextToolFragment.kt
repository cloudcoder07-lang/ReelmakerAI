package com.reelmakerai.ui.tools

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.reelmakerai.R

class TextToolFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.view_tool_text, container, false)

        view.findViewById<Button>(R.id.btnAddText).setOnClickListener {
            Toast.makeText(requireContext(), "Add text triggered", Toast.LENGTH_SHORT).show()
        }

        view.findViewById<Button>(R.id.btnFontPicker).setOnClickListener {
            Toast.makeText(requireContext(), "Font picker triggered", Toast.LENGTH_SHORT).show()
        }

        view.findViewById<Button>(R.id.btnTextColor).setOnClickListener {
            Toast.makeText(requireContext(), "Text color triggered", Toast.LENGTH_SHORT).show()
        }

        return view
    }
}
