package com.example.siren.feature.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.siren.databinding.ActivityMainBinding
import net.daum.mf.map.api.MapView

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initMapView()
    }

    private fun initMapView() {
        val mapView = MapView(this)
        binding.mapView.addView(mapView)
    }
}