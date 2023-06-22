package com.example.siren.feature.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.siren.databinding.ItemDontcheckBinding
import com.example.siren.databinding.ItemEmergencyRoomBinding
import com.example.siren.network.response.EmergencyResponse
import com.example.siren.network.response.Response


class DetailNotTreatAdapter(
    private val items: List<Response<String>>
) : RecyclerView.Adapter<DetailNotTreatAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemDontcheckBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: String) {
            binding.apply {
                tvState.text = item
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemDontcheckBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position].message)
    }
//    val emergencyRoom: String,
//    val operatingRoom: String,
//    val geIntensiveCareUnit: String,
//    val thIntensiveCareUnit: String,
//    val intensiveCareUnit: String,
//    val neurologicalIntensiveCareUnit: String,
//    val neonatalIntensiveCareUnit: String,
//    val neurosurgeryIntensiveCareUnit: String,
//    val inpatientRoom: String,
//    val surgicalInpatientRoom: String,
//    val neurologyInpatientRoom: String

    override fun getItemCount(): Int {
        return items.size
    }
}