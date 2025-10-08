package com.reelmakerai.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.reelmakerai.R

class ToolbarAdapter(private val items: List<ToolbarItem>) :
    RecyclerView.Adapter<ToolbarAdapter.ToolbarViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToolbarViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_toolbar_icon, parent, false)
        return ToolbarViewHolder(view)
    }

    override fun onBindViewHolder(holder: ToolbarViewHolder, position: Int) {
        val item = items[position]
        holder.icon.setImageResource(item.iconResId)
        holder.label.text = item.label
    }

    override fun getItemCount(): Int = items.size

    class ToolbarViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val icon: ImageView = view.findViewById(R.id.toolbarIcon)
        val label: TextView = view.findViewById(R.id.toolbarLabel)
    }
}
