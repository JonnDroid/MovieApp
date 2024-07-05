package com.example.movieapplication.presentation.main_activity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movieapplication.data.repository.PrefRepository

class MainViewModelFactory(private val prefRepo: PrefRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(prefRepo) as T
    }
}