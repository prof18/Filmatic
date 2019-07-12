package com.prof18.filmatic.features.home.ui.discover

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prof18.filmatic.core.utils.Event
import com.prof18.filmatic.features.home.data.discovery.DiscoveryRepository
import com.prof18.filmatic.features.home.data.discovery.model.Genre
import kotlinx.coroutines.launch
import timber.log.Timber
import java.lang.Exception
import javax.inject.Inject

class DiscoverViewModel @Inject constructor
    (private val discoveryRepository: DiscoveryRepository) : ViewModel() {

    private val _genres = MutableLiveData<List<Genre>>()
    val genres: LiveData<List<Genre>>
        get() = _genres

    private val _showError = MutableLiveData<Event<Boolean>>()
    val showError: LiveData<Event<Boolean>>
        get() = _showError

    fun getGenres() {
        viewModelScope.launch {
            try {
                val list = discoveryRepository.getGenres()
                _genres.postValue(list)
            } catch (e: Exception) {
                Timber.e(e)
                _showError.value = Event(true)
            }
        }
    }
}