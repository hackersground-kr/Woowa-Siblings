package com.example.siren.feature.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.siren.di.module.NetworkModule
import com.example.siren.network.api.NaverApi
import com.example.siren.network.api.SirenApi
import com.example.siren.network.response.CoordinateResponse
import com.example.siren.network.response.EmergencyResponse
import com.example.siren.network.response.Summary
import com.example.siren.network.response.Trafast
import com.kakaomobility.knsdk.KNRoutePriority
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val sirenApi: SirenApi,
    private val naverApi: NaverApi
) : ViewModel() {

    init {
        getEmergency()
        getCoordinate()
    }

    private val _emergency = MutableStateFlow(emptyList<EmergencyResponse>())
    val emergency: StateFlow<List<EmergencyResponse>> = _emergency.asStateFlow()

    private val _coordinate = MutableStateFlow(emptyList<CoordinateResponse>())
    val coordinate: StateFlow<List<CoordinateResponse>> = _coordinate.asStateFlow()

    val error = MutableSharedFlow<String?>()

    private fun getEmergency() = viewModelScope.launch {
        kotlin.runCatching {
            sirenApi.getEmergency()
        }.onSuccess {
            _emergency.emit(it.data)
        }.onFailure {
            error.emit(it.message)
        }
    }

    private fun getCoordinate() = viewModelScope.launch {
        kotlin.runCatching {
            sirenApi.getCoordinate()
        }.onSuccess {
            _coordinate.emit(it.data)
        }.onFailure {
            error.emit(it.message)
        }
    }

    fun getDirection(start: String, goal: String) = viewModelScope.launch {
        kotlin.runCatching {
            naverApi.getDirection(
                "fssy95c1nb",
                "Kj0CoGwxJA7UqQY7gxj84ygqEnbDsJZwxuzpe5dr",
                start, goal
            )
        }.onSuccess {
        }.onFailure {
            error.emit(it.message)
        }
    }
}