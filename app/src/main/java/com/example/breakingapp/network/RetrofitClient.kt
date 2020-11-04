package com.example.breakingapp.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {

    companion object {
        private const val URL_BASE = "http://breakingbadapi.com/api/"
        fun getRetrofitCliente(): BreakingApi {
            val mRetrofit = Retrofit.Builder()
                .baseUrl(URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return mRetrofit.create(BreakingApi::class.java)
        }
    }

}