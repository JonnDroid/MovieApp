package com.example.movieapplication.presentation.main_activity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapplication.constant.AppConstant.API_KEY
import com.example.movieapplication.data.repository.PrefRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val prefRepo: PrefRepository) : ViewModel() {
    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()

    init {
        viewModelScope.launch {
            prefRepo.setApiKey(API_KEY)
            _isLoading.value = false
        }
    }
}