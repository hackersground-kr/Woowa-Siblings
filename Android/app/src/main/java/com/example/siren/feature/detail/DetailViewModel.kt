package com.example.siren.feature.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.siren.di.module.Local
import com.example.siren.network.api.SirenApi
import com.example.siren.network.response.CoordinateResponse
import com.example.siren.network.response.EmergencyResponse
import com.example.siren.network.response.MessageResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val sirenApi: SirenApi
) : ViewModel() {

    init {
        getEmergency()
        getCoordinate()
    }

    private val _emergency = MutableStateFlow(emptyList<EmergencyResponse>())
    val emergency: StateFlow<List<EmergencyResponse>> = _emergency.asStateFlow()

    private val _coordinate = MutableStateFlow(emptyList<CoordinateResponse>())
    val coordinate: StateFlow<List<CoordinateResponse>> = _coordinate.asStateFlow()

    private val _message = MutableStateFlow(emptyList<MessageResponse>())
    val message: StateFlow<List<MessageResponse>> = _message.asStateFlow()

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

    fun getMessage(hospitalCode: String) = viewModelScope.launch {
        kotlin.runCatching {
            sirenApi.getMessage(hospitalCode)
        }.onSuccess {
            _message.emit(it.data)
        }.onFailure {
            error.emit(it.message)
        }
    }
}