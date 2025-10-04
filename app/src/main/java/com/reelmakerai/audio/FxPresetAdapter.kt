package com.reelmakerai.audio

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.reelmakerai.R

class FxPresetAdapter(
    private val presets: List<FxPreset>,
    private val onClick: (FxPreset) -> Unit
) : RecyclerView.Adapter<FxPresetAdapter.PresetViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PresetViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_fx_preset, parent, false)
        return PresetViewHolder(view)
    }

    override fun onBindViewHolder(holder: PresetViewHolder, position: Int) {
        val preset = presets[position]
        holder.name.text = preset.name
        holder.icon.setImageResource(preset.iconResId)
        holder.itemView.setOnClickListener { onClick(preset) }
    }

    override fun getItemCount(): Int = presets.size

    class PresetViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.presetName)
        val icon: ImageView = view.findViewById(R.id.presetIcon)
    }
}
