package com.example.siren.feature.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.siren.databinding.ItemHospitalBinding
import com.example.siren.network.response.EmergencyResponse
import dagger.hilt.android.AndroidEntryPoint

class MainAdapter(
    private val items: List<EmergencyResponse>,
    val onClick: (EmergencyResponse) -> Unit
) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemHospitalBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: EmergencyResponse) {
            Glide.with(binding.root.context)
                .load(item)
                .into(binding.ivImage)

            binding.ibNavigation.setOnClickListener { onClick.invoke(item) }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemHospitalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }
}