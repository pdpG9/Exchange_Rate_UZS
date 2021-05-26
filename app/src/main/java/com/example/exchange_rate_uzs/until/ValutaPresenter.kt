package com.example.exchange_rate_uzs.until

import android.util.Log
import com.example.exchange_rate_uzs.data.ValutaResponse
import com.example.exchange_rate_uzs.network.Base
import com.example.exchange_rate_uzs.network.ValutaServise
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ValutaPresenter(val view: ValutaContract.ValutaView) : ValutaContract.ValutaPresenter {
    private var valutaServise: ValutaServise? = null
    private var data: ValutaResponse? = null
    private val v = view

    init {
        valutaServise = Base.RETROFIT.create(ValutaServise::class.java)
    }

    override fun getCurrentData() {
        valutaServise?.getCurrentData()?.enqueue(object : Callback<ValutaResponse> {
            override fun onResponse(
                call: Call<ValutaResponse>,
                response: Response<ValutaResponse>
            ) {
                if (response.isSuccessful) {
                    Log.d("TAG", "onResponse:${response.code()} ")
                    data = response.body()
                    data?.let { view.getValuteResponse(it) }

                } else {
                    Log.d("TAG", "onResponse: ${response.isSuccessful}")

                }
            }

            override fun onFailure(call: Call<ValutaResponse>, t: Throwable) {
                Log.d("TAG", "onFailure: ${t.message}")


            }

        })
    }
}