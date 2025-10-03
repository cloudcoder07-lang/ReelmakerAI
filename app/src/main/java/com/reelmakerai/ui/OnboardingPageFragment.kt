package com.reelmakerai.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.reelmakerai.databinding.ItemOnboardingPageBinding

class OnboardingPageFragment : Fragment() {

    private var _binding: ItemOnboardingPageBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance(title: String, description: String): OnboardingPageFragment {
            val fragment = OnboardingPageFragment()
            val args = Bundle()
            args.putString("title", title)
            args.putString("description", description)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ItemOnboardingPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.titleText.text = arguments?.getString("title")
        binding.descriptionText.text = arguments?.getString("description")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
