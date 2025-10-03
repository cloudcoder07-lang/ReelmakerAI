package com.reelmakerai.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class OnboardingPagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> OnboardingPageFragment.newInstance("Cinematic LUTs", "Apply mood filters with one tap")
            1 -> OnboardingPageFragment.newInstance("Voice FX", "Trigger effects with your voice")
            else -> OnboardingPageFragment.newInstance("Export & Monetize", "Add watermark, export, and unlock Pro")
        }
    }
}
