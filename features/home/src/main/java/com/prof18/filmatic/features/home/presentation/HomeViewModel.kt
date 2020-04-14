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
import com.prof18.filmatic.core.architecture.CoroutinesDispatcherProvider
import com.prof18.filmatic.core.architecture.Result
import com.prof18.filmatic.core.architecture.ViewState
import com.prof18.filmatic.features.home.domain.entities.Movie
import com.prof18.filmatic.features.home.domain.usecases.GetPopularMoviesUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val popularMoviesUseCase: GetPopularMoviesUseCase,
    private val dispatcherProvider: CoroutinesDispatcherProvider
) : ViewModel() {

    // TODO: move to a more interesting ui state
    private val _exploreState = MutableLiveData<ViewState>()
    val exploreState: LiveData<ViewState>
        get() = _exploreState

    fun fetchPopularMovies() {
        _exploreState.postValue(ViewState.Loading)

        viewModelScope.launch(dispatcherProvider.io) {
            val movieResult = popularMoviesUseCase.execute()
            when (movieResult) {
                is Result.Success -> {
                    _exploreState.postValue(ViewState.Success(movieResult.data))
                }
                is Result.Error -> {
                    val exception = movieResult.exception
                    _exploreState.postValue(ViewState.Error(exception.localizedMessage ?: ""))
                }
            }
        }
    }
}