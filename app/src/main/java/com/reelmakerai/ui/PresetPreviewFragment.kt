package com.reelmakerai.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.reelmakerai.databinding.FragmentPresetPreviewBinding
import com.reelmakerai.export.ExportPresets

class PresetPreviewFragment : Fragment() {

    private var _binding: FragmentPresetPreviewBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPresetPreviewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val adapter = PresetPreviewAdapter(ExportPresets.presets) { selected ->
            // TODO: Apply selected preset to export engine
        }
        binding.presetRecycler.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
