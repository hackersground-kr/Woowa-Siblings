package com.example.siren.feature.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.siren.databinding.ItemDontcheckBinding
import com.example.siren.databinding.ItemEmergencyRoomBinding
import com.example.siren.network.response.EmergencyResponse
import com.example.siren.network.response.Response


class DetailNotTreatAdapter(
    private val items: List<String>
) : RecyclerView.Adapter<DetailNotTreatAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemDontcheckBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: String) {
            with(binding) {
//                .text = item.split(" ").last()
//                tvState.text = item.split(" ").first()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemDontcheckBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }
}