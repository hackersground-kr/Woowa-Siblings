package com.example.siren.feature.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.siren.databinding.ItemEmergencyRoomBinding
import com.example.siren.network.response.EmergencyResponse


class DetailAboveAdapter(
    private val items: List<String>
) : RecyclerView.Adapter<DetailAboveAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemEmergencyRoomBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: String) {
            with(binding) {
                tvRemainState.text = item.split(" ").last()
                tvState.text = item.split(" ").first()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailAboveAdapter.ViewHolder {
        val view = ItemEmergencyRoomBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: DetailAboveAdapter.ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }
}