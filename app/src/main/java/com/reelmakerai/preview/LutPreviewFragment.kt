package com.reelmakerai.preview

import android.opengl.GLSurfaceView
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.reelmakerai.databinding.FragmentLutPreviewBinding
import com.reelmakerai.filters.LutShaderRenderer

class LutPreviewFragment : Fragment() {

    private var _binding: FragmentLutPreviewBinding? = null
    private val binding get() = _binding!!

    private lateinit var glSurfaceView: GLSurfaceView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLutPreviewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        glSurfaceView = binding.glPreview
        glSurfaceView.setEGLContextClientVersion(2)
        glSurfaceView.setRenderer(LutShaderRenderer(requireContext()))
        glSurfaceView.renderMode = GLSurfaceView.RENDERMODE_CONTINUOUSLY
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
