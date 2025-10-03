package com.reelmakerai.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.reelmakerai.databinding.ItemPresetPreviewBinding
import com.reelmakerai.export.ExportPreset

class PresetPreviewAdapter(
    private val presets: List<ExportPreset>,
    private val onSelect: (ExportPreset) -> Unit
) : RecyclerView.Adapter<PresetPreviewAdapter.PresetViewHolder>() {

    inner class PresetViewHolder(val binding: ItemPresetPreviewBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PresetViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemPresetPreviewBinding.inflate(inflater, parent, false)
        return PresetViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PresetViewHolder, position: Int) {
        val preset = presets[position]
        holder.binding.presetName.text = preset.name
        Glide.with(holder.itemView.context)
            .load(preset.lutUrl)
            .into(holder.binding.presetThumbnail)

        holder.binding.root.setOnClickListener {
            onSelect(preset)
        }
    }

    override fun getItemCount(): Int = presets.size
}
