package com.prof18.filmatic.features.home.ui.explore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prof18.filmatic.core.utils.Event
import com.prof18.filmatic.features.home.data.popular.PopularRepository
import com.prof18.filmatic.features.home.data.popular.model.Movie
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class ExploreViewModel @Inject constructor(private val popularRepository: PopularRepository) :
    ViewModel() {

    private val _popularMovies = MutableLiveData<List<Movie>>()
    val popularMovie: LiveData<List<Movie>>
        get() = _popularMovies

    private val _showError = MutableLiveData<Event<Boolean>>()
    val showError: LiveData<Event<Boolean>>
        get() = _showError

    fun getPopularMovies() {
        viewModelScope.launch {
            try {
                val list = popularRepository.getPopularMovies()
                _popularMovies.postValue(list)
            } catch (e: Exception) {
                Timber.e(e)
                _showError.value = Event(true)
            }
        }
    }
}