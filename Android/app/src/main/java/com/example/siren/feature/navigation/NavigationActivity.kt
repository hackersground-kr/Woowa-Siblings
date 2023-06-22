package com.example.siren.feature.navigation

import android.os.Bundle
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import com.example.siren.databinding.ActivityNavigationBinding
import com.example.siren.util.SirenApplication
import com.example.siren.util.carTypeWithIdx
import com.example.siren.util.fuelTypeWithIdx
import com.kakaomobility.knsdk.KNRouteAvoidOption
import com.kakaomobility.knsdk.KNRoutePriority
import com.kakaomobility.knsdk.common.objects.KNError
import com.kakaomobility.knsdk.guidance.knguidance.KNGuidance
import com.kakaomobility.knsdk.guidance.knguidance.KNGuidance_CitsGuideDelegate
import com.kakaomobility.knsdk.guidance.knguidance.KNGuidance_GuideStateDelegate
import com.kakaomobility.knsdk.guidance.knguidance.KNGuidance_LocationGuideDelegate
import com.kakaomobility.knsdk.guidance.knguidance.KNGuidance_RouteGuideDelegate
import com.kakaomobility.knsdk.guidance.knguidance.KNGuidance_SafetyGuideDelegate
import com.kakaomobility.knsdk.guidance.knguidance.KNGuidance_VoiceGuideDelegate
import com.kakaomobility.knsdk.guidance.knguidance.KNGuideState
import com.kakaomobility.knsdk.guidance.knguidance.citsguide.KNGuide_Cits
import com.kakaomobility.knsdk.guidance.knguidance.locationguide.KNGuide_Location
import com.kakaomobility.knsdk.guidance.knguidance.routeguide.KNGuide_Route
import com.kakaomobility.knsdk.guidance.knguidance.routeguide.objects.KNMultiRouteInfo
import com.kakaomobility.knsdk.guidance.knguidance.safetyguide.KNGuide_Safety
import com.kakaomobility.knsdk.guidance.knguidance.safetyguide.objects.KNSafety
import com.kakaomobility.knsdk.guidance.knguidance.voiceguide.KNGuide_Voice
import com.kakaomobility.knsdk.trip.kntrip.KNTrip
import com.kakaomobility.knsdk.trip.kntrip.knroute.KNRoute
import com.kakaomobility.knsdk.ui.component.MapViewCameraMode
import com.kakaomobility.knsdk.ui.view.KNNaviView_GuideStateDelegate
import com.kakaomobility.knsdk.ui.view.KNNaviView_StateDelegate

class NavigationActivity : AppCompatActivity(), KNNaviView_StateDelegate,
    KNNaviView_GuideStateDelegate,
    KNGuidance_GuideStateDelegate, KNGuidance_LocationGuideDelegate, KNGuidance_RouteGuideDelegate,
    KNGuidance_SafetyGuideDelegate, KNGuidance_VoiceGuideDelegate, KNGuidance_CitsGuideDelegate {

    private val binding: ActivityNavigationBinding by lazy { ActivityNavigationBinding.inflate(layoutInflater) }
    private var guidestate = KNGuideState.KNGuideState_Init

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        with(binding.naviView) {
            stateDelegate = this@NavigationActivity
            guideStateDelegate = this@NavigationActivity
            mapViewMode = MapViewCameraMode.Bird
            sndVolume = SirenApplication.prefs.getSndVolume()
            fuelType = fuelTypeWithIdx(SirenApplication.prefs.getFuelType())
            carType = carTypeWithIdx(SirenApplication.prefs.getCarType())
        }

        val avoidOption = intent?.getIntExtra("avoidOption", 0)
        val routeOption = intent?.getSerializableExtra("routeOption") as KNRoutePriority?
        val priRoute = intent?.getBooleanExtra("priRoute", true)
        val key = intent?.getStringExtra("tripKey")

        var trip: KNTrip? = null
        if (!TextUtils.isEmpty(key)) {
            trip = SirenApplication.instance.getDataHolder(key)
        }

        if (trip != null) {
            SirenApplication.knsdk.sharedGuidance()?.apply {
                //  guidance delegate 등록
                guideStateDelegate = this@NavigationActivity
                locationGuideDelegate = this@NavigationActivity
                routeGuideDelegate = this@NavigationActivity
                safetyGuideDelegate = this@NavigationActivity
                voiceGuideDelegate = this@NavigationActivity
                citsGuideDelegate = this@NavigationActivity

                binding.naviView.initWithGuidance(
                    this,
                    trip,
                    routeOption!!,
                    avoidOption!!
                )

                binding.naviView.post {
                    run {
                        if (priRoute == false) {
                            changeRoute()
                        }
                    }
                }
            }
        } else {
            SirenApplication.knsdk.sharedGuidance()?.apply {
                //  guidance delegate 등록
                guideStateDelegate = this@NavigationActivity
                locationGuideDelegate = this@NavigationActivity
                routeGuideDelegate = this@NavigationActivity
                safetyGuideDelegate = this@NavigationActivity
                voiceGuideDelegate = this@NavigationActivity
                citsGuideDelegate = this@NavigationActivity

                binding.naviView.initWithGuidance(this, null, KNRoutePriority.KNRoutePriority_Recommand, KNRouteAvoidOption.KNRouteAvoidOption_None.value)
            }
        }

//        startForegroundService(this, "길안내 주행중", "빠르고 즐거운 운전, 카카오내비")
    }

    override fun didUpdateCitsGuide(aGuidance: KNGuidance, aCitsGuide: KNGuide_Cits) {
        binding.naviView.didUpdateCitsGuide(aGuidance, aCitsGuide)
    }

    override fun guidanceCheckingRouteChange(aGuidance: KNGuidance) {
        binding.naviView.guidanceCheckingRouteChange(aGuidance)
    }

    override fun guidanceDidUpdateRoutes(
        aGuidance: KNGuidance,
        aRoutes: List<KNRoute>,
        aMultiRouteInfo: KNMultiRouteInfo?
    ) {
        binding.naviView.guidanceDidUpdateRoutes(aGuidance, aRoutes, aMultiRouteInfo)
    }

    override fun guidanceGuideEnded(aGuidance: KNGuidance) {
        binding.naviView.guidanceGuideEnded(aGuidance)
    }

    override fun guidanceGuideStarted(aGuidance: KNGuidance) {
        binding.naviView.guidanceGuideStarted(aGuidance)
    }

    override fun guidanceOutOfRoute(aGuidance: KNGuidance) {
        binding.naviView.guidanceOutOfRoute(aGuidance)
    }

    override fun guidanceRouteChanged(aGuidance: KNGuidance) {
        binding.naviView.guidanceRouteChanged(aGuidance)
    }

    override fun guidanceRouteUnchanged(aGuidance: KNGuidance) {
        binding.naviView.guidanceRouteUnchanged(aGuidance)
    }

    override fun guidanceRouteUnchangedWithError(aGuidnace: KNGuidance, aError: KNError) {
        binding.naviView.guidanceRouteUnchangedWithError(aGuidnace, aError)
    }

    override fun guidanceDidUpdateLocation(
        aGuidance: KNGuidance,
        aLocationGuide: KNGuide_Location
    ) {
        binding.naviView.guidanceDidUpdateLocation(aGuidance, aLocationGuide)
    }

    override fun guidanceDidUpdateRouteGuide(aGuidance: KNGuidance, aRouteGuide: KNGuide_Route) {
        binding.naviView.guidanceDidUpdateRouteGuide(aGuidance, aRouteGuide)
    }

    override fun guidanceDidUpdateAroundSafeties(
        aGuidance: KNGuidance,
        aSafeties: List<KNSafety>?
    ) {
        binding.naviView.guidanceDidUpdateAroundSafeties(aGuidance, aSafeties)
    }

    override fun guidanceDidUpdateSafetyGuide(
        aGuidance: KNGuidance,
        aSafetyGuide: KNGuide_Safety?
    ) {
        binding.naviView.guidanceDidUpdateSafetyGuide(aGuidance, aSafetyGuide)
    }

    override fun didFinishPlayVoiceGuide(aGuidance: KNGuidance, aVoiceGuide: KNGuide_Voice) {
        binding.naviView.didFinishPlayVoiceGuide(aGuidance, aVoiceGuide)
    }

    override fun shouldPlayVoiceGuide(
        aGuidance: KNGuidance,
        aVoiceGuide: KNGuide_Voice,
        aNewData: MutableList<ByteArray>
    ): Boolean {
        return binding.naviView.shouldPlayVoiceGuide(aGuidance, aVoiceGuide, aNewData)
    }

    override fun willPlayVoiceGuide(aGuidance: KNGuidance, aVoiceGuide: KNGuide_Voice) {
        binding.naviView.willPlayVoiceGuide(aGuidance, aVoiceGuide)
    }

    override fun naviViewGuideEnded() {
        binding.naviView.mapComponent.mapView.onPause()
        finish()
    }

    override fun naviViewGuideState(state: KNGuideState) {
    }

    override fun naviViewDidUpdateMapCameraMode(aCameraMode: MapViewCameraMode) {
    }

    override fun naviViewDidUpdateSndVolume(aVolume: Float) {
        SirenApplication.prefs.setSndVolume(aVolume)
    }

    override fun naviViewDidUpdateStatusBarColor(aColor: Int) {
    }

    override fun naviViewDidUpdateUseDarkMode(aMode: Boolean) {
    }

//    private fun startForegroundService(context : Context, title : String, content : String) {
//        val bundle = Bundle().apply {
//            putString("title", title)
//            putString("content", content)
//        }
//
//        val intent = Intent(context, KNSampleService::class.java).apply {
//            putExtra(KNSampleService.EXTRA_BUNDLE, bundle)
//        }
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            context.startForegroundService(intent)
//        } else {
//            context.startService(intent)
//        }
//    }
//
//    private fun stopForegroundService(context : Context) {
//        context.stopService(Intent(context, KNSampleService::class.java))
//    }
}