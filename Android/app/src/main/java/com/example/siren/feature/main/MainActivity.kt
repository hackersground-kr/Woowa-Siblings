package com.example.siren.feature.main

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.motion.widget.MotionLayout
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
//        binding.viewPager.setPageTransformer(pageTransformer)


        initMapView(savedInstanceState)

        pagerDummy.add(Emergency(1, "일병원병원"))
        pagerDummy.add(Emergency(2, "이병원병원"))
        pagerDummy.add(Emergency(3, "삼병원병원"))


    }

    private fun initViewPager() {
        // MyRecyclerViewAdapter is an standard RecyclerView.Adapter :)
        binding.viewPager.adapter = MainAdapter(pagerDummy)
        binding.viewPager.clipToPadding = false
        binding.viewPager.clipChildren = false
// You need to retain one page on each side so that the next and previous items are visible
        binding.viewPager.offscreenPageLimit = 1
        binding.viewPager.setPadding(80, 0, 80, 35)
// Add a PageTransformer that translates the next and previous items horizontally
// towards the center of the screen, which makes them visible
        val nextItemVisiblePx = resources.getDimension(R.dimen.viewpager_next_item_visible)
        val currentItemHorizontalMarginPx =
            resources.getDimension(R.dimen.viewpager_current_item_horizontal_margin)
        val pageTranslationX = nextItemVisiblePx + currentItemHorizontalMarginPx
        val pageTransformer = ViewPager2.PageTransformer { page: View, position: Float ->
            page.translationX = -pageTranslationX * position
            // Next line scales the item's height. You can remove it if you don't want this effect
            page.translationY = 2 - (0.5f * abs(position))
            // If you want a fading effect uncomment the next line:
            // page.alpha = 0.25f + (1 - abs(position))
        }
        binding.viewPager.setPageTransformer(pageTransformer)
        val itemDecoration = HorizontalMarginItemDecoration(
            this,
            R.dimen.viewpager_current_item_horizontal_margin
        )
        binding.viewPager.addItemDecoration(itemDecoration)
    }

    private fun initMapView(savedInstanceState: Bundle?) {
        binding.mapView.onCreate(savedInstanceState)
        binding.mapView.getMapAsync(this)
    }

    override fun onMapReady(map: NaverMap) {
        naverMap = map
    }


}