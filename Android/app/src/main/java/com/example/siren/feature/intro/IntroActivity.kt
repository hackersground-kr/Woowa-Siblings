package com.example.siren.feature.intro

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.lifecycle.lifecycleScope
import com.example.siren.R
import com.example.siren.databinding.ActivityIntroBinding
import com.example.siren.feature.main.MainActivity
import com.example.siren.util.SirenApplication
import com.kakaomobility.knsdk.KNLanguageType
import com.kakaomobility.knsdk.common.objects.KNError_Code_C302
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class IntroActivity : AppCompatActivity() {
    private val binding: ActivityIntroBinding by lazy { ActivityIntroBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        if (!isTaskRoot) {
            val intentAction = intent.action
            if (intent.hasCategory(Intent.CATEGORY_LAUNCHER) && intentAction != null && intentAction == Intent.ACTION_MAIN) {
                finish()
                return
            }
        }

        checkPermission()
    }

    private fun checkPermission() {
        when {
            checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED -> {
                makeRequestPermissionGPS()
            }
            checkSelfPermission(Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED -> {
                makeRequestPermissionPhoneState()
            }
            else -> {
                sdkInit()
            }
        }
    }

    private fun sdkInit() {
        lifecycleScope.launch {
            val language: KNLanguageType = if (SirenApplication.prefs.mapLanguage()) {
                KNLanguageType.KNLanguageType_KOREAN
            } else {
                KNLanguageType.KNLanguageType_ENGLISH
            }

            SirenApplication.knsdk.apply {
                initializeWithAppKey("cff402064d1f2cb51ebcff82004170fd", "1.0", SirenApplication.prefs.getUserId(), language, aCompletion = {
                    if (it != null) {
                        android.util.Log.e("ABASDBASDB", "failed ${it.code}")
                        when (it.code) {
                            KNError_Code_C302 -> {
                                makeRequestPermissionGPS()
                            }
                            else -> {

                            }
                        }
                    } else {
                        startActivity(Intent(applicationContext, MainActivity::class.java))
                        finish()
                    }
                })
            }
        }
    }

    private fun makeRequestPermissionPhoneState() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_PHONE_STATE)) {
            val builder = AlertDialog.Builder(this)
            builder.setMessage("Permission to access the microphone is required for this app to Phone State")
                .setTitle("Permission required")

            builder.setPositiveButton("OK") { _, _->
                makeRequestPermission(arrayOf(Manifest.permission.READ_PHONE_STATE), PERMISSION_PHONE_STATE_REQUEST_CODE)
            }

            val dialog = builder.create()
            dialog.show()
        } else {
            makeRequestPermission(arrayOf(Manifest.permission.READ_PHONE_STATE), PERMISSION_PHONE_STATE_REQUEST_CODE)
        }
    }

    private fun makeRequestPermissionGPS() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
            val builder = AlertDialog.Builder(this)
            builder.setMessage("Permission to access the microphone is required for this app to GPS")
                .setTitle("Permission required")

            builder.setPositiveButton("OK") { _, _->
                makeRequestPermission(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), PERMISSION_GPS_REQUEST_CODE)
            }

            val dialog = builder.create()
            dialog.show()
        } else {
            makeRequestPermission(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), PERMISSION_GPS_REQUEST_CODE)
        }
    }

    private fun makeRequestPermission(permissions: Array<String>, requestCode: Int) {
        ActivityCompat.requestPermissions(this, permissions, requestCode)
    }

    companion object {
        const val PERMISSION_PHONE_STATE_REQUEST_CODE = 1000
        const val PERMISSION_GPS_REQUEST_CODE = 1001
    }
}