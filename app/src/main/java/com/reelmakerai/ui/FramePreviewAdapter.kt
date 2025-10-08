package com.reelmakerai.ui

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.reelmakerai.R

class FramePreviewAdapter(private val videoUri: Uri) :
    RecyclerView.Adapter<FramePreviewAdapter.FrameViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FrameViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_frame_preview, parent, false)
        return FrameViewHolder(view)
    }

    override fun onBindViewHolder(holder: FrameViewHolder, position: Int) {
        // Stub: Replace with actual frame extraction logic
        holder.frameThumb.setImageResource(R.drawable.ic_frame_placeholder)
    }

    override fun getItemCount(): Int = 10 // Stub: Replace with actual frame count

    class FrameViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val frameThumb: ImageView = view.findViewById(R.id.frameThumb)
    }
}
