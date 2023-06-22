package com.example.siren.network.response

import com.google.gson.annotations.SerializedName

data class Response<T>(
    @SerializedName("status")
    val status: Int,
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val data: T
)
