package com.ismin.csproject

import java.io.Serializable

data class Apartment(
    val id: Int,
    val nom: String,
    val description: String,
    val zone: String,
    val coordX: Double,
    val coordY: Double,
    val phone: String,
    val address: String,
    val photo: String,
): Serializable {}
