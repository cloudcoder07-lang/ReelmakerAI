package com.reelmakerai.ui.tools

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.reelmakerai.R

class VoiceFxToolFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.view_tool_voicefx, container, false)

        view.findViewById<Button>(R.id.btnVoiceRobot).setOnClickListener {
            Toast.makeText(requireContext(), "Robot voice triggered", Toast.LENGTH_SHORT).show()
        }

        view.findViewById<Button>(R.id.btnVoiceEcho).setOnClickListener {
            Toast.makeText(requireContext(), "Echo effect triggered", Toast.LENGTH_SHORT).show()
        }

        view.findViewById<Button>(R.id.btnVoicePitch).setOnClickListener {
            Toast.makeText(requireContext(), "Pitch shift triggered", Toast.LENGTH_SHORT).show()
        }

        return view
    }
}
