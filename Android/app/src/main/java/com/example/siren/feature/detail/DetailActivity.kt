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
    private val binding: ActivityDetailBinding by lazy {
        ActivityDetailBinding.inflate(
            layoutInflater
        )
    }
    private val viewModel: SirenViewModel by viewModels()
    private val list1 = mutableListOf<String>()
    private val list2 = mutableListOf<String>()
    private val list3 = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.recyclerView1.adapter = DetailAboveAdapter(list1.toList())
        binding.recyclerView2.adapter = DetailUnderneathAdapter(list2.toList())
//        binding.recyclerView3.adapter = DetailNotTreatAdapter()

        lifecycleScope.launch {
            viewModel.emergency.collect {
                it.forEach { emergency ->
                    list1.apply {
                        add("응급실 ${emergency.emergencyRoom}")
                        add("수술실 ${emergency.operatingRoom}")
                        add("입원실 ${emergency.geIntensiveCareUnit}")
                        add("외과입원실 ${emergency.thIntensiveCareUnit}")
                        add("집중치료실 ${emergency.intensiveCareUnit}")
                        add("중환자치료실 ${emergency.neurologicalIntensiveCareUnit}")
                    }
                    list2.apply {
                        add("신생아집중치료실 ${emergency.neonatalIntensiveCareUnit}")
                        add("신경외과집중치료실 ${emergency.neurosurgeryIntensiveCareUnit}")
                        add("입원실 ${emergency.inpatientRoom}")
                        add("수술입원실 ${emergency.surgicalInpatientRoom}")
                        add("신경과입원실 ${emergency.neurologyInpatientRoom}")
                    }
                }
            }
        }
    }
}