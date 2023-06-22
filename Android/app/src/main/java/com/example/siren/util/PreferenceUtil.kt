package com.example.siren.util

import android.app.Application
import android.preference.PreferenceManager
import kotlin.random.Random

class PreferenceUtil(application: Application) {
    private val context = application.baseContext
    private val prefs = PreferenceManager.getDefaultSharedPreferences(application)

    init {
        getUserId()
    }

    companion object {
        const val USER_ID = "user_id"
        const val CAR_TYPE = "car_type"
        const val USE_HIPASS = "use_hipass"
        private const val CAR_WIDTH = "car_width"
        const val CAR_HEIGHT = "car_height"
        const val CAR_LENGTH = "car_length" 
        const val CAR_WEIGTH = "car_weight"
        const val CAR_FUEL_TYPE = "car_fuel_type"
        
        const val MAP_DISPLAY_TYPE = "map_display_type"
        const val MAP_TRAFFIC_MODE = "map_traffic_mode"
        const val MAP_LANGUAGE = "map_language"
        const val SHOW_POI = "show_poi"
        const val SHOW_BUILDING = "show_building"
        
        const val SOUND_VOLUME = "sound_volume"
    }

    //  userId
    fun getUserId() = getString(USER_ID, "user_${Random.nextInt(Int.MAX_VALUE)}")
    fun setUserId(value: String) = putString(USER_ID, value)

    fun getCarType() = getString(CAR_TYPE, "0")
    fun useHipass() = getBoolean(USE_HIPASS, true)
    fun getCarWidth() = getString(CAR_WIDTH, "-1").toInt()
    fun getCarHeight() = getString(CAR_HEIGHT, "-1").toInt()
    fun getCarLength() = getString(CAR_LENGTH, "-1").toInt()
    fun getCarWeight() = getString(CAR_WEIGTH, "-1").toInt()
    fun getFuelType() = getString(CAR_FUEL_TYPE, "0")

    fun mapDisplayType() = getBoolean(MAP_DISPLAY_TYPE, true)
    fun mapTrafficMode() = getBoolean(MAP_TRAFFIC_MODE, false)
    fun mapLanguage() = getBoolean(MAP_LANGUAGE, true)
    fun showPoi() = getBoolean(SHOW_POI, true)
    fun showBuilding() = getBoolean(SHOW_BUILDING, true)

    fun getSndVolume() = getFloat(SOUND_VOLUME, 1f)
    fun setSndVolume(value: Float) = putFloat(SOUND_VOLUME, value)

    private fun getString(aKey: String, defaultValue: String): String {
        var result = defaultValue

        if (prefs.contains(aKey)) {
            result = prefs.getString(aKey, defaultValue)!!
        } else {
            putString(aKey, defaultValue)
        }

        return result
    }

    private fun putString(aKey: String, aValue: String) {
        prefs.edit().apply {
            putString(aKey, aValue)
            apply()
        }
    }

    private fun getBoolean(aKey: String, defaultValue: Boolean): Boolean {
        var result = defaultValue

        if(prefs.contains(aKey)) {
            result = prefs.getBoolean(aKey, defaultValue)
        } else {
            putBoolean(aKey, defaultValue)
        }

        return result
    }

    private fun putBoolean(aKey: String, aValue: Boolean) {
        prefs.edit().apply {
            putBoolean(aKey, aValue)
            apply()
        }
    }

    private fun getInt(aKey: String, defaultValue: Int): Int {
        var result = defaultValue

        if (prefs.contains(aKey)) {
            result = prefs.getInt(aKey, defaultValue)
        } else {
            putInt(aKey, defaultValue)
        }

        return result
    }

    private fun putInt(aKey: String, aValue: Int) {
        prefs.edit().apply {
            putInt(aKey, aValue)
            apply()
        }
    }

    private fun getFloat(aKey: String, defaultValue: Float): Float {
        var result = defaultValue

        if (prefs.contains(aKey)) {
            result = prefs.getFloat(aKey, defaultValue)
        } else {
            putFloat(aKey, defaultValue)
        }

        return result
    }

    private fun putFloat(aKey: String, aValue: Float) {
        prefs.edit().apply {
            putFloat(aKey, aValue)
            apply()
        }
    }
}
