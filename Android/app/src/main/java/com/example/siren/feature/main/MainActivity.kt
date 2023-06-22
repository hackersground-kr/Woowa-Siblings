package com.example.siren.feature.main

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.example.siren.R
import com.example.siren.databinding.ActivityMainBinding
import com.example.siren.feature.main.adapter.MainAdapter
import com.example.siren.feature.navigation.NavigationActivity
import com.example.siren.model.Emergency
import com.example.siren.network.response.EmergencyResponse
import com.example.siren.util.HorizontalMarginItemDecoration
import com.example.siren.util.SirenApplication
import com.kakaomobility.knsdk.KNLanguageType
import com.kakaomobility.knsdk.KNSDK
import com.kakaomobility.knsdk.common.gps.KN_DEFAULT_POS_X
import com.kakaomobility.knsdk.common.gps.KN_DEFAULT_POS_Y
import com.kakaomobility.knsdk.common.gps.WGS84ToKATEC
import com.kakaomobility.knsdk.common.util.FloatPoint
import com.kakaomobility.knsdk.map.knmaprenderer.objects.KNMapCameraUpdate
import com.kakaomobility.knsdk.map.uicustomsupport.renewal.KNMapMarker
import com.microsoft.appcenter.AppCenter
import com.microsoft.appcenter.analytics.Analytics
import com.microsoft.appcenter.crashes.Crashes
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraAnimation
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.overlay.OverlayImage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), OnMapReadyCallback {
    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private lateinit var naverMap: NaverMap
    private lateinit var mainAdapter: MainAdapter
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initMapView(savedInstanceState)
        initViewPager()

        AppCenter.start(
            application, "76cc6612-5ef5-4b0c-b751-8d8f3adcafdf",
            Analytics::class.java, Crashes::class.java
        )
    }

    private fun initViewPager() = with(binding.viewPager) {
//        lifecycleScope.launch {
//            viewModel.emergency.collect { data ->
//                adapter = MainAdapter(data) {
//                    startActivity(
//                        Intent(
//                            this@MainActivity,
//                            NavigationActivity::class.java
//                        ).apply {
//                        }
//                    )
//                }
//            }
//        }

        val sample = listOf(
            Emergency(128.60431466100363, 35.866235497221616, "https://i.namu.wiki/i/iDpaSp3m9aOqZ83UQat5MqMefX6j4gXVBiYLpeXS1DebFegakg1g1P-5sFkrRSLEjvWbbagu43iXppk7xrJ5FLG5ZL2xyRDwxxlr3yINXljaDIGT3f2MfIJaXkVNIGZQ7d0O8LSgF6FGymOmwdmRDg.webp", "4.3", "3분", "1", "경북대학교병원", "053-1234-1234", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0"),
            Emergency(128.56409603929066, 35.95681747386846, "https://i.namu.wiki/i/ckN8KvXPTsdhn3MoaQA6FIXnc_UtHASdRJKRCqZj7WgNiQyoNo7X0avYCiu2nJN4jPH1EvWweUeid5oh-ADPLU9TpPuMJsO7LDopA7ENlul0atTw8BDWHKvcRGOxqXiINjtsCDkaR-RIc-ZbajRFGw.webp", "7.9", "20분", "2", "칠곡경대병원", "053-4132-1243", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0")
        )

        mainAdapter = MainAdapter(sample) {
            startActivity(
                Intent(
                    this@MainActivity,
                    NavigationActivity::class.java
                ).apply {
                    putExtra("startName", sample[0].hospitalName)
                    putExtra("goalName", sample[1].hospitalName)
                    putExtra("startLongitude", sample[0].longitude)
                    putExtra("goalLongitude", sample[1].longitude)
                    putExtra("startLatitude", sample[0].latitude)
                    putExtra("goalLatitude", sample[1].latitude)
                }
            )
        }

        adapter = mainAdapter

//        setMarker(sample)

        offscreenPageLimit = 3
        setPadding(50, 0, 50, 100)

        val nextItemVisiblePx = resources.getDimension(R.dimen.viewpager_next_item_visible)
        val currentItemHorizontalMarginPx =
            resources.getDimension(R.dimen.viewpager_current_item_horizontal_margin)
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
        with(binding) {
            mapView.onCreate(savedInstanceState)
            mapView.getMapAsync(this@MainActivity)


        }
    }

    private fun setMarker() {
        val sample = listOf(
            Emergency(128.60431466100363, 35.866235497221616, "https://i.namu.wiki/i/iDpaSp3m9aOqZ83UQat5MqMefX6j4gXVBiYLpeXS1DebFegakg1g1P-5sFkrRSLEjvWbbagu43iXppk7xrJ5FLG5ZL2xyRDwxxlr3yINXljaDIGT3f2MfIJaXkVNIGZQ7d0O8LSgF6FGymOmwdmRDg.webp", "4.3", "3분", "1", "경북대학교병원", "053-1234-1234", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0"),
            Emergency(128.56409603929066, 35.95681747386846, "https://i.namu.wiki/i/ckN8KvXPTsdhn3MoaQA6FIXnc_UtHASdRJKRCqZj7WgNiQyoNo7X0avYCiu2nJN4jPH1EvWweUeid5oh-ADPLU9TpPuMJsO7LDopA7ENlul0atTw8BDWHKvcRGOxqXiINjtsCDkaR-RIc-ZbajRFGw.webp", "7.9", "20분", "2", "칠곡경대병원", "053-4132-1243", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0")
        ).forEach { emergency ->

            val marker = Marker()

            marker.position = LatLng(emergency.latitude, emergency.longitude)

            marker.map = naverMap
            marker.tag = emergency.hospitalCode
            marker.icon = OverlayImage.fromResource(R.drawable.marker)

            marker.setOnClickListener {
//                navigateToDetail(it.tag as Int)
                true
            }
        }
    }

    override fun onMapReady(map: NaverMap) {
        naverMap = map

        with(naverMap.uiSettings) {
            isLocationButtonEnabled = false
            logoGravity = Gravity.END.or(Gravity.TOP)
            setLogoMargin(0, 0, 16, 0)
            isCompassEnabled = false
            isZoomControlEnabled = false
        }

        setMarker()

        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                CoroutineScope(Dispatchers.Default).launch {
                    val selectedCompanyModel = mainAdapter.items[position]
                    val cameraUpdate = CameraUpdate
                        .scrollAndZoomTo(LatLng(selectedCompanyModel.latitude, selectedCompanyModel.longitude), 15.0)
                        .animate(CameraAnimation.Easing)
                    naverMap.moveCamera(cameraUpdate)
                }
            }
        })
    }

}