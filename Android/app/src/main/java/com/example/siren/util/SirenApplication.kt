package com.example.siren.util

import android.app.Application
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ProcessLifecycleOwner
import com.kakaomobility.knsdk.KNRoutePriority
import com.kakaomobility.knsdk.KNSDK
import com.kakaomobility.knsdk.KNSDKDelegate
import com.kakaomobility.knsdk.trip.kntrip.KNTrip
import java.util.UUID
import java.util.concurrent.ConcurrentHashMap

class SirenApplication : Application(), LifecycleObserver {
    companion object {
        lateinit var instance: SirenApplication
            private set

        lateinit var knsdk: KNSDK

        lateinit var handler : Handler

        lateinit var prefs: PreferenceUtil

        lateinit var dataHolder: Map<String, Any>
    }

    override fun onCreate() {
        super.onCreate()

        ProcessLifecycleOwner.get().lifecycle.addObserver(this)

        instance = this

        prefs = PreferenceUtil(this)

        dataHolder = ConcurrentHashMap()

        knsdk = KNSDK.apply {
            install(instance, "$filesDir/Siren")
            delegate = object: KNSDKDelegate {
                override fun knsdkFoundUnfinishedTrip(aTrip: KNTrip, aPriority: KNRoutePriority, aAvoidOptions: Int) {
                }

                override fun knsdkNeedsLocationAuthorization() {
                }
            }
        }

        handler = Handler(Looper.getMainLooper())
    }

    fun putDataHolder(data: KNTrip): String? {
        (dataHolder as ConcurrentHashMap).clear()
        val dataHolderId = UUID.randomUUID().toString()
        (dataHolder as ConcurrentHashMap).put(dataHolderId, data)
        return dataHolderId
    }

    fun getDataHolder(key: String?): KNTrip? {
        val KNTrip = (dataHolder.get(key) as KNTrip)
        (dataHolder as ConcurrentHashMap).clear()
        return KNTrip
    }

    //  override Lifecycle
    //  ============================================================================================

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onAppBackgrounded() {
        knsdk.handleDidEnterBackground()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onAppForegrounded() {
        knsdk.handleWillEnterForeground()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onAppResumed() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onAppDestroyed() {
        knsdk.handleWillTerminate()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onAppPaused() {
    }

    //  --------------------------------------------------------------------------------------------

}