package com.example.siren.network.response

import com.google.gson.annotations.SerializedName

data class EmergencyResponse(
    @SerializedName("hospitalName")
    val hospitalName: String,
    @SerializedName("telephone")
    val telephone: String,
    @SerializedName("emergencyRoom")
    val emergencyRoom: String,
    @SerializedName("operatingRoom")
    val operatingRoom: String,
    @SerializedName("geIntensiveCareUnit")
    val geIntensiveCareUnit: String,
    @SerializedName("thIntensiveCareUnit")
    val thIntensiveCareUnit: String,
    @SerializedName("intensiveCareUnit")
    val intensiveCareUnit: String,
    @SerializedName("neurologicalIntensiveCareUnit")
    val neurologicalIntensiveCareUnit: String,
    @SerializedName("neonatalIntensiveCareUnit")
    val neonatalIntensiveCareUnit: String,
    @SerializedName("neurosurgeryIntensiveCareUnit")
    val neurosurgeryIntensiveCareUnit: String,
    @SerializedName("inpatientRoom")
    val inpatientRoom: String,
    @SerializedName("surgicalInpatientRoom")
    val surgicalInpatientRoom: String,
    @SerializedName("neurologyInpatientRoom")
    val neurologyInpatientRoom: String,
    @SerializedName("imageUrl")
    val imageUrl: String,
)
