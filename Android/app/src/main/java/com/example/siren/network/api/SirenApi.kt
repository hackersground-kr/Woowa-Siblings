package com.example.siren.network.api

import com.example.siren.network.response.EmergencyResponse
import com.example.siren.network.response.Response
import retrofit2.http.GET

interface SirenApi {
    @GET("emergency")
    suspend fun getEmergency(
    ): Response<List<EmergencyResponse>>
}