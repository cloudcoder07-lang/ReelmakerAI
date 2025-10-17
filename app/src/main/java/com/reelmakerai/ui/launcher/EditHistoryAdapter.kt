package com.reelmakerai.ui.launcher

import android.view.*
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.reelmakerai.R
import com.reelmakerai.model.VideoEditState

class EditHistoryAdapter(
    private val items: List<VideoEditState>,
    private val onClick: (VideoEditState) -> Unit
) : RecyclerView.Adapter<EditHistoryAdapter.Holder>() {

    inner class Holder(view: View) : RecyclerView.ViewHolder(view) {
        val thumbnail: ImageView = view.findViewById(R.id.thumbnail)
        val timestamp: TextView = view.findViewById(R.id.timestamp)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_edit_history, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val item = items[position]
        holder.thumbnail.setImageURI(item.videoUri)
        holder.timestamp.text = "Edited: ${item.timestamp}"
        holder.itemView.setOnClickListener { onClick(item) }
    }

    override fun getItemCount(): Int = items.size
}
