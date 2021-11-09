package com.ismin.csproject

import retrofit2.Call
import retrofit2.http.GET

interface BookService {

    @GET("apartments")
    fun getAllBooks(): Call<List<Apartment>>
}