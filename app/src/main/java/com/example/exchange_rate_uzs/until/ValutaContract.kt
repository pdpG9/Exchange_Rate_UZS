package com.example.exchange_rate_uzs.until

import com.example.exchange_rate_uzs.data.ValutaResponse

interface ValutaContract {
    interface ValutaPresenter {
        fun getCurrentData()

    }

    interface ValutaView {
        fun getValuteResponse(valutaResponse: ValutaResponse)
        fun showProgress()
        fun hideProgress()
        fun showMessage(mess: String)
    }
}