package com.example.siren.feature.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.siren.databinding.ItemDontcheckBinding
import com.example.siren.network.response.MessageResponse


class DetailNotTreatAdapter(
    private val items: List<MessageResponse>
) : RecyclerView.Adapter<DetailNotTreatAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemDontcheckBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MessageResponse) {
            with(binding) {
                tvState.text = item.itemType
                tvContent.text = item.messageText
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