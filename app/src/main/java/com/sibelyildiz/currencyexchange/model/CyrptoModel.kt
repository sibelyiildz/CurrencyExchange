package com.sibelyildiz.currencyexchange.model

import com.google.gson.annotations.SerializedName


data class CyrptoModel(
    @SerializedName("currency")
    val currency: String,
    @SerializedName("price")
    val price: String
)