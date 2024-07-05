package com.example.movieapplication.presentation.movie_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movieapplication.data.repository.MovieRepository
import com.example.movieapplication.data.repository.PrefRepository

class MovieListViewModelFactory (private val movieRepo: MovieRepository, private val prefRepo: PrefRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MovieListViewModel(movieRepo, prefRepo) as T
    }
}