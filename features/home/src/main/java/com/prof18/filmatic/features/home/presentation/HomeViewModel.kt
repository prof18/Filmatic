/*
 * Copyright 2020 Marco Gomiero
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

package com.prof18.filmatic.features.home.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prof18.filmatic.core.architecture.Result
import com.prof18.filmatic.core.architecture.SingleLiveEvent
import com.prof18.filmatic.features.home.domain.entities.Movie
import com.prof18.filmatic.features.home.domain.usecases.GetPopularMoviesUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val popularMoviesUseCase: GetPopularMoviesUseCase
) : ViewModel() {

    // TODO: move to a more interesting ui state
    private val _loader = SingleLiveEvent<Boolean>()
    val loader: LiveData<Boolean>
        get() = _loader

    // TODO: return the ui list visualization not only this list
    private val _movieList = MutableLiveData<List<Movie>>()
    val movieList: LiveData<List<Movie>>
        get() = _movieList

    private val _showError = MutableLiveData<Boolean>()
    val showError: LiveData<Boolean>
        get() = _showError

    fun fetchPopularMovies() {
        viewModelScope.launch {
            _loader.value = true
            val movieResult = popularMoviesUseCase.execute()

            when (movieResult) {
                is Result.Success -> {
                    _movieList.value = movieResult.data
                }
                is Result.Error -> {
                    val exception = movieResult.exception
                    _showError.value = true
                }
            }
            _loader.value = false
        }
    }


}