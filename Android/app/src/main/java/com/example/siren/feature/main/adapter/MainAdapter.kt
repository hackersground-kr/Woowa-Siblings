package com.example.siren.feature.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.siren.databinding.ItemHospitalBinding
import com.example.siren.model.Emergency
import com.example.siren.network.response.EmergencyResponse
import dagger.hilt.android.AndroidEntryPoint

class MainAdapter(
    val items: List<Emergency>,
    val onClick: (Emergency) -> Unit
) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemHospitalBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Emergency) {
            with(binding) {
                Glide.with(root.context)
                    .load(item.image)
                    .into(ivImage)

                tvAddress.text = "구지면 창리로 11길 93"
                tvDistance.text = "${item.distance}km"
                tvRemainingTime.text = item.remainingTime
                tvHospitalName.text = item.hospitalName

                ibNavigation.setOnClickListener { onClick.invoke(item) }
            }
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