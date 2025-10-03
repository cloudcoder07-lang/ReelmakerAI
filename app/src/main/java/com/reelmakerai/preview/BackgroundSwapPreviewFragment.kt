package com.reelmakerai.preview

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.reelmakerai.databinding.FragmentPreviewBinding
import com.reelmakerai.filters.BackgroundSwapProcessor

class BackgroundSwapPreviewFragment : Fragment() {

    private var _binding: FragmentPreviewBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentPreviewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val originalBitmap =
            BitmapFactory.decodeResource(resources, android.R.drawable.sym_def_app_icon)
        val backgroundBitmap =
            BitmapFactory.decodeResource(resources, android.R.drawable.alert_dark_frame)

        val swapped = BackgroundSwapProcessor.compositeForeground(
            requireContext(),
            originalBitmap,
            backgroundBitmap
        )
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
