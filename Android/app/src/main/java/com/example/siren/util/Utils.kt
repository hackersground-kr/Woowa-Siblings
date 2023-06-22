package com.example.siren.util

import com.kakaomobility.knsdk.KNCarFuel
import com.kakaomobility.knsdk.KNCarType

fun carTypeWithIdx(idx: String) = KNCarType.values().find { carType -> carType.value == idx.toInt() } ?: KNCarType.KNCarType_1

fun fuelTypeWithIdx(idx: String) = KNCarFuel.values().find { fuleType -> fuleType.value == idx.toInt()} ?: KNCarFuel.KNCarFuel_Gasoline