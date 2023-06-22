package com.example.siren.feature.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.siren.databinding.ActivityDetailBinding
import com.example.siren.feature.detail.adapter.DetailAboveAdapter
import com.example.siren.feature.detail.adapter.DetailNotTreatAdapter
import com.example.siren.feature.detail.adapter.DetailUnderneathAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {
    private val binding: ActivityDetailBinding by lazy { ActivityDetailBinding.inflate(layoutInflater) }
    private val viewModel: DetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        lifecycleScope.launch {
            launch {
                viewModel.emergency.collect { emergency ->
                    if (emergency.isNotEmpty()) {
                        val emergencyList = emergency.find { it.hospitalName == intent.getStringExtra("hpName") }!!

                        Glide.with(binding.root)
                            .load(emergencyList.imageUrl)
                            .into(binding.imageView)

                        binding.recyclerView1.adapter = DetailAboveAdapter(
                            listOf(
                                "응급실 ${emergencyList.emergencyRoom}",
                                "수술실 ${emergencyList.operatingRoom}",
                                "입원실 ${emergencyList.inpatientRoom}",
                                "외과입원실 ${emergencyList.surgicalInpatientRoom}",
                                "신경과입원실 ${emergencyList.neurologyInpatientRoom}"
                            )
                        )
                        binding.recyclerView2.adapter = DetailUnderneathAdapter(
                            listOf(
                                "신경중환자실 ${emergencyList.neurologicalIntensiveCareUnit}",
                                "신생아중환자실 ${emergencyList.neonatalIntensiveCareUnit}",
                                "흉부중환자실 ${emergencyList.thIntensiveCareUnit}",
                                "신경외과중환자실 ${emergencyList.neurosurgeryIntensiveCareUnit}",
                                "내과중환자실 ${emergencyList.intensiveCareUnit}",

                                )
                        )
                    }
                }
            }
            launch {
                viewModel.coordinate.collect { coordinate ->
                    if (coordinate.isNotEmpty()) {
                        val coordinateList = coordinate.find { it.hospitalName == intent.getStringExtra("hpName") }!!

                        viewModel.getMessage(coordinateList.hospitalCode)

                        binding.tvHospitalNameDetail.text = coordinateList.hospitalName
                        binding.tvAddressDetail.text = coordinateList.hospitalAddress
                        binding.tvTelephone.text = coordinateList.emergencyRoomTel
                    }
                }
            }
            launch {
                viewModel.message.collect { message ->
                    if (message.isNotEmpty()) {
                        binding.tv2.visibility = View.VISIBLE
                        binding.recyclerView3.adapter = DetailNotTreatAdapter(message)
                    } else {
                        binding.tv2.visibility = View.INVISIBLE
                    }
                }
            }
        }
    }
}