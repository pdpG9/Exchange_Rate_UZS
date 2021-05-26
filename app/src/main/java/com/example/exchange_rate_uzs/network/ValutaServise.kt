package com.example.exchange_rate_uzs.network

import com.example.exchange_rate_uzs.data.ValutaResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ValutaServise {

    @GET("arkhiv-kursov-valyut/json/")
    fun getCurrentData(): Call<ValutaResponse>

    @GET("arkhiv-kursov-valyut/json/all/{data}/")
    fun getByDate(
        @Path(
            "data"
        ) d: String
    ): Call<ValutaResponse>

    @GET("arkhiv-kursov-valyut/json/all/{type}/{data}/")
    fun getDataByType(@Path("type") type: String, @Path("data") data: String): Call<ValutaResponse>
}