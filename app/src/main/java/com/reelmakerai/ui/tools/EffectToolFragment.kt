package com.reelmakerai.ui.tools

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.reelmakerai.R

class EffectToolFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.view_tool_effect, container, false)

        view.findViewById<Button>(R.id.btnApplyFilter).setOnClickListener {
            Toast.makeText(requireContext(), "Filter applied", Toast.LENGTH_SHORT).show()
        }

        view.findViewById<Button>(R.id.btnAdjustIntensity).setOnClickListener {
            Toast.makeText(requireContext(), "Intensity adjusted", Toast.LENGTH_SHORT).show()
        }

        return view
    }
}
