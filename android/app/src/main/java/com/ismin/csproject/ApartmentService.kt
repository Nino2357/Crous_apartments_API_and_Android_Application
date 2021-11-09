package com.ismin.csproject

import retrofit2.Call
import retrofit2.http.GET

interface ApartmentService {

    @GET("apartments")
    fun getAllApartments(): Call<List<Apartment>>
}