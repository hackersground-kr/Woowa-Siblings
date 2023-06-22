package com.example.siren.network.api

import com.example.siren.network.response.NaverResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface NaverApi {
    @GET("map-direction-15/v1/driving")
    suspend fun getDirection(
        @Header("X-NCP-APIGW-API-KEY-ID") X_NCP_APIGW_API_KEY_ID: String,
        @Header("X-NCP-APIGW-API-KEY") X_NCP_APIGW_API_KEY: String,
        @Query("start") start: String,
        @Query("goal") goal: String,
    ): NaverResponse
}