package com.example.movieapplication.data.repository

import android.content.Context
import android.content.SharedPreferences

class PrefRepository(private val context: Context) {
    private val sharedPreferences: SharedPreferences by lazy {
        context.getSharedPreferences("movie_api_key", Context.MODE_PRIVATE)
    }
    private val editor: SharedPreferences.Editor by lazy {
        sharedPreferences.edit()
    }

    fun setApiKey(key: String) {
        editor.putString("api_key", key).apply()
    }

    fun getApiKey() : String{
        return sharedPreferences.getString("api_key", "").toString()
    }

}