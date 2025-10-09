package com.reelmakerai.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.reelmakerai.R
import com.reelmakerai.model.FrameItem

class FrameStripAdapter(private val frames: List<FrameItem>) :
    RecyclerView.Adapter<FrameStripAdapter.FrameViewHolder>() {

    inner class FrameViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.frameImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FrameViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_frame_strip, parent, false)
        return FrameViewHolder(view)
    }

    override fun onBindViewHolder(holder: FrameViewHolder, position: Int) {
        holder.imageView.setImageDrawable(frames[position].thumbnail)
    }

    override fun getItemCount(): Int = frames.size
}
