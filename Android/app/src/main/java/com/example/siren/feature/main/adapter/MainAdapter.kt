package com.example.siren.feature.main.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.siren.databinding.ItemHospitalBinding
import com.example.siren.network.response.CoordinateResponse
import com.example.siren.network.response.EmergencyResponse

class MainAdapter(
//    val distanceList: List<Distance>,
    val emergencyList: List<EmergencyResponse>,
    val coordinateList: List<CoordinateResponse>,
    val onClick: (CoordinateResponse) -> Unit,
    val onClickNavigation: (CoordinateResponse) -> Unit
) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemHospitalBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(/*distance: Distance, */emergency: EmergencyResponse, coordinate: CoordinateResponse) {
            with(binding) {
                Glide.with(root.context)
                    .load(emergency.imageUrl)
                    .into(ivImage)

                tvAddress.text = coordinate.hospitalAddress
//                tvDistance.text = distance.distance + "km"
//                tvRemainingTime.text = distance.remainingTime
                tvHospitalName.text = coordinate.hospitalName

                binding.root.setOnClickListener { onClick.invoke(coordinate) }
                ibNavigation.setOnClickListener { onClickNavigation.invoke(coordinate) }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemHospitalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(emergencyList[position], coordinateList[position])
        Log.d("버그 찾기", emergencyList[position].imageUrl)
    }

    override fun getItemCount(): Int {
        return emergencyList.size
    }
}