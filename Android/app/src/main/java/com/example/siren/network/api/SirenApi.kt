package com.example.siren.network.api

import com.example.siren.network.response.CoordinateResponse
import com.example.siren.network.response.EmergencyResponse
import com.example.siren.network.response.Response
import retrofit2.http.GET

interface SirenApi {
    @GET("emergency")
    suspend fun getEmergency(
    ): Response<List<EmergencyResponse>>

    @GET("emergency/coordinate")
    suspend fun getCoordinate(
    ): Response<List<CoordinateResponse>>
}