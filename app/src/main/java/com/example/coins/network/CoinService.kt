package com.example.coins.network

import com.example.coins.data.latestListing.LatestCryptoListing
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface CoinService {

    @GET("cryptocurrency/listings/latest")
    suspend fun getLatest(@QueryMap queries : HashMap<String,Any?>) : LatestCryptoListing

}