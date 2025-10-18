package com.reelmakerai.ui.adapter

import android.view.*
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.reelmakerai.R
import com.reelmakerai.model.AspectRatio

class AspectRatioAdapter(
    private val ratios: List<AspectRatio>,
    private val onSelect: (AspectRatio) -> Unit
) : RecyclerView.Adapter<AspectRatioAdapter.RatioViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RatioViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_aspect_ratio, parent, false)
        return RatioViewHolder(view)
    }

    override fun onBindViewHolder(holder: RatioViewHolder, position: Int) {
        val ratio = ratios[position]
        holder.label.text = ratio.label
        holder.itemView.setOnClickListener { onSelect(ratio) }
    }

    override fun getItemCount(): Int = ratios.size

    class RatioViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val label: TextView = view.findViewById(R.id.ratioLabel)
    }
}
