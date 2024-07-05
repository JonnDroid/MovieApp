package com.example.movieapplication.data.network

import com.example.movieapplication.constant.AppConstant
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val retrofit by lazy{
        Retrofit.Builder()
            .baseUrl(AppConstant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: MovieAPI by lazy{
        retrofit.create(MovieAPI::class.java)
    }
}