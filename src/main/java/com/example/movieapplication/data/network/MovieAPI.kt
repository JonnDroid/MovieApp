package com.example.movieapplication.data.network

import com.example.movieapplication.data.model.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieAPI {
    @GET("movie/popular")
    suspend fun getMovieData(@Query("api_key") key: String): MovieResponse


}