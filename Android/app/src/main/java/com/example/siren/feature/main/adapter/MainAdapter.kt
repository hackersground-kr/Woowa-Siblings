package com.example.siren.feature.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.siren.R
import com.example.siren.databinding.ActivityMainBinding
import com.example.siren.databinding.ViewPagerItemBinding
import com.example.siren.network.response.Emergency

class MainAdapter(val items: ArrayList<Emergency>): RecyclerView.Adapter<MainAdapter.ViewHolder>(){

    interface OnItemClickListener{
        fun OnItemClick(url:String)
    }

    var itemClickListener:OnItemClickListener?=null

    inner class ViewHolder(val binding: ViewPagerItemBinding): RecyclerView.ViewHolder(binding.root){
        init {
            binding.root.setOnClickListener {
                itemClickListener?.OnItemClick("euya")
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ViewPagerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.apply {
            tvHospitalName.text = items[position].str
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}