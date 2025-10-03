package com.reelmakerai.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.reelmakerai.databinding.FragmentProUpgradeBinding

class ProUpgradeFragment : Fragment() {

    private var _binding: FragmentProUpgradeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentProUpgradeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.upgradeButton.setOnClickListener {
            // TODO: Trigger BillingClient purchase flow
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
