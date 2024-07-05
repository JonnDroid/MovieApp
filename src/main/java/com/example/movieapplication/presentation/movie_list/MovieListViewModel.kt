package com.example.movieapplication.presentation.movie_list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapplication.data.model.Movie
import com.example.movieapplication.data.repository.MovieRepository
import com.example.movieapplication.data.repository.PrefRepository
import kotlinx.coroutines.launch

class MovieListViewModel(private val movieRepo: MovieRepository, private val prefRepo: PrefRepository) : ViewModel() {
    private val _movieList: MutableLiveData<List<Movie>> = MutableLiveData()
    val movieList: MutableLiveData<List<Movie>> = _movieList
    private val _hasError: MutableLiveData<Boolean> = MutableLiveData()
    val hasError: MutableLiveData<Boolean> = _hasError
    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val isLoading: MutableLiveData<Boolean> = _isLoading

    init{
        getMovieData()
    }
    fun getMovieData() {
        _hasError.value = false
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val apiKey = prefRepo.getApiKey()
                val listOfMovies = movieRepo.getMovieData(apiKey)
                _movieList.value = listOfMovies.movies
                _hasError.value = false
            } catch(e: Exception) {
                _hasError.value = true
            }
            _isLoading.value = false
        }
    }
}