package com.example.exchange_rate_uzs.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Base {
    const val BASE_URL = "https://cbu.uz/oz/"

    val RETROFIT: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}