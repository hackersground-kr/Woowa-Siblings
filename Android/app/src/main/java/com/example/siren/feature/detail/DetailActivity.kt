package com.example.siren.feature.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.siren.databinding.ActivityDetailBinding
import com.example.siren.feature.detail.adapter.DetailAboveAdapter
import com.example.siren.feature.detail.adapter.DetailNotTreatAdapter
import com.example.siren.feature.detail.adapter.DetailUnderneathAdapter
import com.example.siren.feature.main.SirenViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {
    private val binding: ActivityDetailBinding by lazy { ActivityDetailBinding.inflate(layoutInflater) }
    private val viewModel: SirenViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
//        binding.recyclerView3.adapter = DetailNotTreatAdapter()

        lifecycleScope.launch {
            launch {
                viewModel.emergency.collect { emergency ->
                    if (emergency.isNotEmpty()) {
                        val emergencyList = emergency.find { it.hospitalName == intent.getStringExtra("hpName") }!!

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
                        val coordinateList = coordinate.find { it.dutyName == intent.getStringExtra("hpName") }!!
                        binding.tvHospitalNameDetail.text = coordinateList.dutyName
                        binding.tvAddressDetail.text = coordinateList.dutyAddr
                        binding.tvTelephone.text = coordinateList.dutyTel1
                    }
                }
            }
        }
    }
}