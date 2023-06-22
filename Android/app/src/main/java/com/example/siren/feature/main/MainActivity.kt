package com.example.siren.feature.main

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.siren.R
import com.example.siren.databinding.ActivityMainBinding
import com.example.siren.feature.main.adapter.MainAdapter
import com.example.siren.feature.navigation.NavigationActivity
import com.example.siren.network.response.Emergency
import com.example.siren.util.HorizontalMarginItemDecoration
import com.example.siren.util.SirenApplication
import com.kakaomobility.knsdk.KNLanguageType
import com.kakaomobility.knsdk.KNSDK
import com.kakaomobility.knsdk.common.gps.KN_DEFAULT_POS_X
import com.kakaomobility.knsdk.common.gps.KN_DEFAULT_POS_Y
import com.kakaomobility.knsdk.common.gps.WGS84ToKATEC
import com.kakaomobility.knsdk.common.util.FloatPoint
import com.kakaomobility.knsdk.map.knmaprenderer.objects.KNMapCameraUpdate

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    var pagerDummy = ArrayList<Emergency>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        applyMapSettings()
        initViewPager()
        initMapView()

        pagerDummy.add(Emergency(1, "일병원병원"))
        pagerDummy.add(Emergency(2, "이병원병원"))
        pagerDummy.add(Emergency(3, "삼병원병원"))


    }

    private fun initViewPager() = with(binding.viewPager) {
        adapter = MainAdapter(pagerDummy) {
            startActivity(Intent(this@MainActivity, NavigationActivity::class.java))
        }

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


    private fun initMapView() {
        KNSDK.bindingMapView(binding.mapView, binding.mapView.mapTheme) { error ->
            if (error != null) {
                Toast.makeText(
                    this, "맵 초기화 작업이 실패하였습니다. \n[${error.code}] : ${error.msg}",
                    Toast.LENGTH_LONG
                ).show()
                return@bindingMapView
            }

            val lastPos = KNSDK.sharedGpsManager()?.lastValidGpsData?.pos ?: FloatPoint(
                KN_DEFAULT_POS_X.toFloat(), KN_DEFAULT_POS_Y.toFloat()
            )
            val center = WGS84ToKATEC(127.11019081347423, 37.3941851228957)

            binding.mapView.moveCamera(KNMapCameraUpdate.targetTo(center).zoomTo(2.5f), false)
            binding.mapView.userLocation?.apply {
                isVisible = true
                isVisibleGuideLine = true
                coordinate = lastPos
            }
        }
    }

    private fun applyMapSettings() {
        var isTraffic: Boolean
        var showPoi: Boolean
        var showBuilding: Boolean
        var isLangType: Boolean

        SirenApplication.prefs.apply {
            isTraffic = mapTrafficMode()
            isLangType = mapLanguage()
            showPoi = showPoi()
            showBuilding = showBuilding()
        }

        if (binding.mapView.isInitialize()) {
            binding.mapView.apply {
                KNSDK.setLanguageType(if (isLangType) KNLanguageType.KNLanguageType_KOREAN else KNLanguageType.KNLanguageType_ENGLISH)
                isVisibleTraffic = isTraffic
                isVisiblePOI = showPoi
                isVisibleBuilding = showBuilding
            }
        }
    }

}