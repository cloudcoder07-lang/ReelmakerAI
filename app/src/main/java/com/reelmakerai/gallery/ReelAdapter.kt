package com.reelmakerai.gallery

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.reelmakerai.databinding.ReelItemBinding

class ReelAdapter : RecyclerView.Adapter<ReelAdapter.ReelViewHolder>() {

    private val reelList = listOf<String>() // TODO: Replace with actual reel paths

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReelViewHolder {
        val binding = ReelItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ReelViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReelViewHolder, position: Int) {
        val reelPath = reelList[position]
        // TODO: Load thumbnail from reelPath
        holder.binding.reelTitle.text = "Reel #$position"
    }

    override fun getItemCount(): Int = reelList.size

    inner class ReelViewHolder(val binding: ReelItemBinding) : RecyclerView.ViewHolder(binding.root)
}
