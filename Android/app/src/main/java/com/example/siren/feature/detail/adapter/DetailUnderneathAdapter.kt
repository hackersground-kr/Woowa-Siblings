package com.example.siren.feature.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.siren.databinding.ItemEmergencyRoomBinding
import com.example.siren.network.response.EmergencyResponse


class DetailUnderneathAdapter(
    private val items: List<String>
) : RecyclerView.Adapter<DetailUnderneathAdapter.ViewHolder>() {


    inner class ViewHolder(private val binding: ItemEmergencyRoomBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: String) {
            binding.apply {
                tvState.text = item.split(" ").first()
                tvRemainState.text = item.split(" ").last()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemEmergencyRoomBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }
}