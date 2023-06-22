package com.example.siren.feature.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.siren.databinding.ItemHospitalBinding
import com.example.siren.network.response.Emergency

class MainAdapter(val items: List<Emergency>, val onClick: () -> Unit): RecyclerView.Adapter<MainAdapter.ViewHolder>(){

    inner class ViewHolder(val binding: ItemHospitalBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: Emergency?) {
//            Glide.with(binding.root.context)
//                .load(item)
//                .into(binding.imgUrl)

            binding.ibNavigation.setOnClickListener { onClick.invoke() }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemHospitalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items.get(position))
    }

    override fun getItemCount(): Int {
        return items.size
    }
}