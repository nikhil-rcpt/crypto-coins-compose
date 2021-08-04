package com.example.coins.network

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CoinInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        var request = chain.request()
        request = request.newBuilder().header("X-CMC_PRO_API_KEY", NetworkConstants.CMC_KEY)
            .build()
        return chain.proceed(request)
    }


}