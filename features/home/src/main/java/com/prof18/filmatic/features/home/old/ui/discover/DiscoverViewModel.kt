/*
 * Copyright 2019 Marco Gomiero
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.prof18.filmatic.features.home.old.ui.discover

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prof18.filmatic.core.utils.Event
import com.prof18.filmatic.features.home.old.data.discovery.DiscoveryRepository
import com.prof18.filmatic.features.home.old.data.discovery.model.Genre
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