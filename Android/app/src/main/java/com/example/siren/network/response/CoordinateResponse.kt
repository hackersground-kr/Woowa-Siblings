package com.example.siren.network.response

import com.google.gson.annotations.SerializedName

data class CoordinateResponse(
    @SerializedName("wgs84Lon")
    val wgs84Lon: Double,
    @SerializedName("wgs84Lat")
    val wgs84Lat: Double,
    @SerializedName("rnum")
    val rnum: String,
    @SerializedName("phpid")
    val phpid: String,
    @SerializedName("hpid")
    val hpid: String,
    @SerializedName("dutyTel3")
    val dutyTel3: String,
    @SerializedName("dutyTel1")
    val dutyTel1: String,
    @SerializedName("dutyName")
    val dutyName: String,
    @SerializedName("dutyEmclsName")
    val dutyEmclsName: String,
    @SerializedName("dutyEmcls")
    val dutyEmcls: String,
    @SerializedName("dutyAddr")
    val dutyAddr: String,
)
