package com.sibelyildiz.currencyexchange.service


import com.sibelyildiz.currencyexchange.model.CyrptoModel
import retrofit2.Call
import retrofit2.http.GET


interface CryptoApi {

    @GET("prices?key=7d2f35aef2ff3d0de70d4a6667a76972")
    fun getData(): Call<List<CyrptoModel>>
}