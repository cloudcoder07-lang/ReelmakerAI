package com.reelmakerai.ui.tools

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.reelmakerai.R

class StickerToolFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.view_tool_sticker, container, false)

        view.findViewById<Button>(R.id.btnAddSticker).setOnClickListener {
            Toast.makeText(requireContext(), "Add sticker triggered", Toast.LENGTH_SHORT).show()
        }

        view.findViewById<Button>(R.id.btnRemoveSticker).setOnClickListener {
            Toast.makeText(requireContext(), "Remove sticker triggered", Toast.LENGTH_SHORT).show()
        }

        return view
    }
}
