package com.example.siren.network.api

import com.example.siren.network.response.CoordinateResponse
import com.example.siren.network.response.EmergencyResponse
import com.example.siren.network.response.MessageResponse
import com.example.siren.network.response.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SirenApi {
    @GET("emergency")
    suspend fun getEmergency(
    ): Response<List<EmergencyResponse>>

    @GET("emergency/coordinate")
    suspend fun getCoordinate(
    ): Response<List<CoordinateResponse>>

    @GET("emergency/message")
    suspend fun getMessage(
        @Query("hospitalCode") hospitalCode: String
    ): Response<List<MessageResponse>>
}