package com.example.breakingapp.network

import com.example.breakingapp.pojos.BreakingEntityItem
import retrofit2.Call
import retrofit2.http.GET

interface BreakingApi {

    @GET("products")
    fun fetAllBreaking(): Call<BreakingEntityItem>
}