package com.prof18.filmatic.features.home.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prof18.filmatic.features.home.data.popular.PopularRepository
import kotlinx.coroutines.launch

class HomeViewModel(private val popularRepository: PopularRepository): ViewModel() {

    fun getPopularMovies() {

        viewModelScope.launch {
            try {
                val list = popularRepository.getPopularMovies()
            } catch (e: Exception) {
                Log.d("", " Unable to get things")
            }
        }
    }
}