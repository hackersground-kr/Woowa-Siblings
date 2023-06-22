package com.example.siren.feature.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.siren.network.api.SirenApi
import com.example.siren.network.response.EmergencyResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val sirenApi: SirenApi
) : ViewModel() {

    init {
        getEmergency()
    }

    private val _emergency = MutableStateFlow(emptyList<EmergencyResponse>())
    val emergency: StateFlow<List<EmergencyResponse>> = _emergency.asStateFlow()

    val error = MutableSharedFlow<String?>()

    private fun getEmergency() = viewModelScope.launch {
        kotlin.runCatching {
            sirenApi.getEmergency()
        }.onSuccess {
            if (it.status == 200) {
                _emergency.emit(it.data)
            }
        }.onFailure {
            error.emit(it.message)
        }
    }
}