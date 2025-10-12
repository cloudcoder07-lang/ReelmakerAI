package com.reelmakerai.ui.tools

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.reelmakerai.R

class AudioToolFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.view_tool_audio, container, false)

        view.findViewById<Button>(R.id.btnAudioTrim).setOnClickListener {
            Toast.makeText(requireContext(), "Trim audio triggered", Toast.LENGTH_SHORT).show()
        }

        view.findViewById<Button>(R.id.btnAudioFade).setOnClickListener {
            Toast.makeText(requireContext(), "Fade In/Out triggered", Toast.LENGTH_SHORT).show()
        }

        view.findViewById<Button>(R.id.btnAudioVolume).setOnClickListener {
            Toast.makeText(requireContext(), "Volume adjustment triggered", Toast.LENGTH_SHORT).show()
        }

        view.findViewById<Button>(R.id.btnAudioVoiceover).setOnClickListener {
            Toast.makeText(requireContext(), "Voiceover triggered", Toast.LENGTH_SHORT).show()
        }

        return view
    }
}
