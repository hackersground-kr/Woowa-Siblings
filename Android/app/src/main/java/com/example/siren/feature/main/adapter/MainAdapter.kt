package com.example.siren.feature.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.siren.databinding.ItemHospitalBinding
import com.example.siren.model.Distance
import com.example.siren.network.response.CoordinateResponse
import com.example.siren.network.response.EmergencyResponse

class MainAdapter(
//    val distanceList: List<Distance>,
    val emergencyList: List<EmergencyResponse>,
    val coordinateList: List<CoordinateResponse>,
    val onClick: (/*Distance, */EmergencyResponse, CoordinateResponse) -> Unit
) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemHospitalBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(/*distance: Distance, */emergency: EmergencyResponse, coordinate: CoordinateResponse) {
            with(binding) {
//                Glide.with(root.context)
//                    .load(item.image)
//                    .into(ivImage)

                tvAddress.text = coordinate.dutyAddr
//                tvDistance.text = distance.distance + "km"
//                tvRemainingTime.text = distance.remainingTime
                tvHospitalName.text = coordinate.dutyName

                ibNavigation.setOnClickListener { onClick.invoke(/*distance, */emergency, coordinate) }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemHospitalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(/*distanceList[position],*/ emergencyList[position], coordinateList[position])
    }

    override fun getItemCount(): Int {
        return emergencyList.size
    }
}