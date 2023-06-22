package com.example.siren.feature.main

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.core.view.marginBottom
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.siren.R
import com.example.siren.databinding.ActivityMainBinding
import com.example.siren.feature.main.adapter.MainAdapter
import com.example.siren.network.response.Emergency
import com.example.siren.util.HorizontalMarginItemDecoration
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import java.lang.Math.abs

class MainActivity : AppCompatActivity(), OnMapReadyCallback {
    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private lateinit var naverMap: NaverMap
    var pagerDummy = ArrayList<Emergency>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initViewPager()


        initMapView(savedInstanceState)

        pagerDummy.add(Emergency(1, "일병원병원"))
        pagerDummy.add(Emergency(2, "이병원병원"))
        pagerDummy.add(Emergency(3, "삼병원병원"))


    }

    private fun initViewPager() = with(binding.viewPager) {
        adapter = MainAdapter(pagerDummy)

        offscreenPageLimit = 3
        setPadding(50, 0, 50, 100)

        val nextItemVisiblePx = resources.getDimension(R.dimen.viewpager_next_item_visible)
        val currentItemHorizontalMarginPx = resources.getDimension(R.dimen.viewpager_current_item_horizontal_margin)
        val pageTranslationX = nextItemVisiblePx + currentItemHorizontalMarginPx

        setPageTransformer { page, position ->
            page.translationX = -pageTranslationX * position
            page.scaleY = 1 - (0.25f * kotlin.math.abs(position))

        }

        val itemDecoration = HorizontalMarginItemDecoration(
            context,
            R.dimen.viewpager_current_item_horizontal_margin
        )
        addItemDecoration(itemDecoration)
    }


    private fun initMapView(savedInstanceState: Bundle?) {
        binding.mapView.onCreate(savedInstanceState)
        binding.mapView.getMapAsync(this)
    }

    override fun onMapReady(map: NaverMap) {
        naverMap = map
    }


}