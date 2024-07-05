package com.example.movieapplication.data.repository

import com.example.movieapplication.data.model.MovieResponse
import com.example.movieapplication.data.network.RetrofitInstance

class MovieRepository {

        suspend fun getMovieData(key: String): MovieResponse {
            return RetrofitInstance.api.getMovieData(key)
        }

}