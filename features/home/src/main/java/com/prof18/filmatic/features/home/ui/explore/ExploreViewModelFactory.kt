package com.prof18.filmatic.features.home.ui.explore

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.prof18.filmatic.features.home.data.popular.PopularRepository
import javax.inject.Inject

class ExploreViewModelFactory @Inject constructor(
    private val popularRepository: PopularRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass != ExploreViewModel::class.java) {
            throw IllegalArgumentException("Unknown View Model Class")
        }
        return ExploreViewModel(popularRepository) as T
    }
}