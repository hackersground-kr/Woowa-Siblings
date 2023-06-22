package com.example.siren.network.response

import com.google.gson.annotations.SerializedName

data class MessageResponse(
    @SerializedName("hospitalName")
    val hospitalName: String,
    @SerializedName("itemType")
    val itemType: String,
    @SerializedName("messageText")
    val messageText: String,
    @SerializedName("estimatedTime")
    val estimatedTime: String
)
