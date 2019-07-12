package com.prof18.filmatic.features.home.ui.discover

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.prof18.filmatic.features.home.data.discovery.DiscoveryRepository
import java.lang.IllegalArgumentException
import javax.inject.Inject

class DiscoverViewModelFactory @Inject constructor(
    private val discoveryRepository: DiscoveryRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass != DiscoverViewModel::class.java) {
            throw IllegalArgumentException("Unknown View Model Class")
        }
        return DiscoverViewModel(discoveryRepository) as T
    }
}