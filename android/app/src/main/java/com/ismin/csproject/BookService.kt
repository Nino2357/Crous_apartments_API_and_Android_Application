package com.ismin.csproject

import retrofit2.Call
import retrofit2.http.GET

interface BookService {

    @GET("books")
    fun getAllBooks(): Call<List<Book>>
}