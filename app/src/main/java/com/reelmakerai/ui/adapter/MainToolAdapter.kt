package com.reelmakerai.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.reelmakerai.R
import com.reelmakerai.model.ToolType

class MainToolAdapter(
    private val tools: List<ToolType>,
    private val onClick: (ToolType) -> Unit
) : RecyclerView.Adapter<MainToolAdapter.ToolViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToolViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_main_tool, parent, false)
        return ToolViewHolder(view)
    }

    override fun onBindViewHolder(holder: ToolViewHolder, position: Int) {
        val tool = tools[position]
        holder.label.text = tool.name
        holder.icon.setImageResource(tool.iconRes)
        holder.itemView.setOnClickListener { onClick(tool) }
    }

    override fun getItemCount(): Int = tools.size

    class ToolViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val label: TextView = view.findViewById(R.id.toolLabel)
        val icon: ImageView = view.findViewById(R.id.toolIcon)
    }
}
