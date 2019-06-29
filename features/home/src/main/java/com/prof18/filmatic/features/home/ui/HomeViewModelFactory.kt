package com.prof18.filmatic.features.home.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.prof18.filmatic.features.home.data.popular.PopularRepository

class HomeViewModelFactory(
    private val popularRepository: PopularRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass != HomeViewModel::class.java) {
            throw IllegalArgumentException("Unknown View Model Class")
        }
        return HomeViewModel(popularRepository) as T
    }
}