package com.example.siren.model

import com.google.gson.annotations.SerializedName

data class Emergency(
    val longitude: Double,
    val latitude: Double,
    val image: String,
    val distance: String,
    val remainingTime: String,
    val hospitalCode: String,
    val hospitalName: String,
    val telephone: String,
    val emergencyRoom: String,
    val operatingRoom: String,
    val geIntensiveCareUnit: String,
    val thIntensiveCareUnit: String,
    val intensiveCareUnit: String,
    val neurologicalIntensiveCareUnit: String,
    val neonatalIntensiveCareUnit: String,
    val neurosurgeryIntensiveCareUnit: String,
    val inpatientRoom: String,
    val surgicalInpatientRoom: String,
    val neurologyInpatientRoom: String,
)
