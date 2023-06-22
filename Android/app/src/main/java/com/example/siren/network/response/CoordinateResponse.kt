package com.example.siren.network.response

import com.google.gson.annotations.SerializedName

data class CoordinateResponse(
    @SerializedName("wgs84y")
    val wgs84y: Double,
    @SerializedName("wgs84x")
    val wgs84x: Double,
    @SerializedName("hospitalCode")
    val hospitalCode: String,
    @SerializedName("emergencyRoomTel")
    val emergencyRoomTel: String,
    @SerializedName("hospitalName")
    val hospitalName: String,
    @SerializedName("dutyEmclsName")
    val dutyEmclsName: String,
    @SerializedName("dutyEmcls")
    val dutyEmcls: String,
    @SerializedName("hospitalAddress")
    val hospitalAddress: String,
)
