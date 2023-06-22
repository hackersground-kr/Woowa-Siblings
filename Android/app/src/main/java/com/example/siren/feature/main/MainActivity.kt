package com.example.siren.feature.main

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.view.Gravity
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.example.siren.R
import com.example.siren.databinding.ActivityMainBinding
import com.example.siren.feature.detail.DetailActivity
import com.example.siren.feature.main.adapter.MainAdapter
import com.example.siren.feature.navigation.NavigationActivity
import com.example.siren.model.Distance
import com.example.siren.network.response.CoordinateResponse
import com.example.siren.util.HorizontalMarginItemDecoration
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
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
    private val fusedLocationClient: FusedLocationProviderClient by lazy { LocationServices.getFusedLocationProviderClient(this) }
    private val viewModel: MainViewModel by viewModels()
    private val distanceList =  mutableListOf<Distance>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initMapView(savedInstanceState)
        initViewPager()
        collectViewModel()

        AppCenter.start(
            application, "76cc6612-5ef5-4b0c-b751-8d8f3adcafdf",
            Analytics::class.java, Crashes::class.java
        )
    }

    private fun collectViewModel() {
        lifecycleScope.launch {
            launch {
                viewModel.emergency.collect { emergencyList ->
                    launch {
                        viewModel.coordinate.collect { coordinateList ->
                            if (emergencyList.isNotEmpty() && coordinateList.isNotEmpty()) {
                                mainAdapter = MainAdapter(
                                    emergencyList,
                                    coordinateList,
                                    { coordinate ->
                                        startActivity(
                                            Intent(
                                                this@MainActivity,
                                                DetailActivity::class.java
                                            ).apply {
                                                putExtra("hpName", coordinate.hospitalName)
                                            }
                                        )
                                    }, {
                                        startActivity(
                                            Intent(
                                                this@MainActivity,
                                                NavigationActivity::class.java
                                            ).apply {
//                                putExtra("")
                                            }
                                        )
                                    })
                                binding.viewPager.adapter = mainAdapter
                                setMarker(coordinateList)
                            }
                        }
                    }
                }
            }
            launch {
                viewModel.error.collect {
                    Toast.makeText(this@MainActivity, it, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun initViewPager() = with(binding.viewPager) {

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

    private fun setMarker(list: List<CoordinateResponse>) {
        list.forEach { coordinate ->

            val marker = Marker()

            marker.position = LatLng(coordinate.wgs84x, coordinate.wgs84y)

            marker.map = naverMap
            marker.tag = coordinate.hospitalCode
            marker.icon = OverlayImage.fromResource(R.drawable.marker)

            marker.setOnClickListener {
                startActivity(
                    Intent(
                        this@MainActivity,
                        DetailActivity::class.java
                    ).apply {
                        putExtra("hpName", coordinate.hospitalName)
                    }
                )
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

        var currentLocation: Location?
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                currentLocation = location

                naverMap.locationOverlay.run {
                    isVisible = true
                    position = LatLng(currentLocation!!.latitude, currentLocation!!.longitude)
                }

                val cameraUpdate = CameraUpdate.scrollTo(
                    LatLng(
                        currentLocation!!.latitude,
                        currentLocation!!.longitude
                    )
                )
                naverMap.moveCamera(cameraUpdate)

            }

        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                CoroutineScope(Dispatchers.Default).launch {
                    val selectedEmergency = mainAdapter.coordinateList[position]
                    val cameraUpdate = CameraUpdate
                        .scrollAndZoomTo(LatLng(selectedEmergency.wgs84x, selectedEmergency.wgs84y), 15.0)
                        .animate(CameraAnimation.Easing)
                    naverMap.moveCamera(cameraUpdate)
                }
            }
        })
    }

}