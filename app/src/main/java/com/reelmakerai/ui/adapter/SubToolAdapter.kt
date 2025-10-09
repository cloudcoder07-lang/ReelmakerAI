package com.reelmakerai.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.reelmakerai.R
import com.reelmakerai.model.SubToolItem

class SubToolAdapter(
    private val items: List<SubToolItem>,
    private val onClick: (SubToolItem) -> Unit
) : RecyclerView.Adapter<SubToolAdapter.SubToolViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubToolViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_sub_tool, parent, false) as TextView
        return SubToolViewHolder(view)
    }

    override fun onBindViewHolder(holder: SubToolViewHolder, position: Int) {
        val item = items[position]
        holder.textView.text = item.label
        holder.textView.setOnClickListener { onClick(item) }
    }

    override fun getItemCount(): Int = items.size

    class SubToolViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)
}
