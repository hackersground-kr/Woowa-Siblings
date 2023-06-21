package com.example.siren.feature.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.MarginPageTransformer
import com.example.siren.R
import com.example.siren.databinding.ActivityMainBinding
import com.example.siren.feature.main.adapter.MainAdapter
import com.example.siren.network.response.Emergency
import net.daum.mf.map.api.MapView

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    var pagerDummy = ArrayList<Emergency>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initMapView()

        pagerDummy.add(Emergency(1,"일병원병원"))
        pagerDummy.add(Emergency(2,"이병원병원"))
        pagerDummy.add(Emergency(3,"삼병원병원"))

        binding.viewPager.setClipToPadding(false)
        var dpValue = 50
        var d = getResources().getDisplayMetrics().density
        var margin = (dpValue * d).toInt()
        binding.viewPager.setPageTransformer(MarginPageTransformer(10))
        binding.viewPager.setPadding(margin, 0, margin, 0)

        mainInitViewPager2()

    }

    private fun initMapView() {
        val mapView = MapView(this)
        binding.mapView.addView(mapView)
    }
    private fun mainInitViewPager2(){
        binding.viewPager.apply {
            clipToPadding= false
            clipChildren= false
            offscreenPageLimit = 1
            adapter = MainAdapter(pagerDummy)
        }
        binding.viewPager.setPageTransformer(MarginPageTransformer(40))
        binding.viewPager.setPadding(80,0,80,0)
    }


}