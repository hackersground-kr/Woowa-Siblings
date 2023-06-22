package com.example.siren.network.response

data class NaverResponse(
    val code: Int,
    val message: String,
    val currentDateTime: String,
    val route: Route
)
