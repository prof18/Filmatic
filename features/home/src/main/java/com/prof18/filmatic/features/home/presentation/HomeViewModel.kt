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

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prof18.filmatic.core.architecture.CoroutinesDispatcherProvider
import com.prof18.filmatic.core.architecture.Result
import com.prof18.filmatic.core.architecture.ViewState
import com.prof18.filmatic.core.utils.Utils.retry
import com.prof18.filmatic.features.home.R
import com.prof18.filmatic.features.home.domain.entities.Genre
import com.prof18.filmatic.features.home.domain.entities.Movie
import com.prof18.filmatic.features.home.domain.usecases.GetGenresUseCase
import com.prof18.filmatic.features.home.domain.usecases.GetPopularMoviesUseCase
import com.prof18.filmatic.features.home.presentation.explore.model.ExploreItem
import com.prof18.filmatic.features.home.presentation.explore.model.ItemHorizontalCollection
import com.prof18.filmatic.libraries.uicomponents.listitem.ItemHeader
import com.prof18.filmatic.libraries.uicomponents.listitem.ItemMovieBig
import com.prof18.filmatic.libraries.uicomponents.listitem.ItemMovieBottomTitle
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val popularMoviesUseCase: GetPopularMoviesUseCase,
    private val genresUseCase: GetGenresUseCase,
    private val dispatcherProvider: CoroutinesDispatcherProvider
) : ViewModel() {

    // TODO: move to a more interesting ui state
    private val _exploreState = MutableLiveData<ViewState<List<ExploreItem>>>()
    val exploreState: LiveData<ViewState<List<ExploreItem>>>
        get() = _exploreState

    private val _discoverState = MutableLiveData<ViewState<List<Genre>>>()
    val discoverState: LiveData<ViewState<List<Genre>>>
        get() = _discoverState

    fun fetchExploreItems() {
        _exploreState.postValue(ViewState.Loading)

        viewModelScope.launch(dispatcherProvider.main) {
            val result = popularMoviesUseCase.execute()
            when (result) {
                is Result.Success -> {
                    val exploreItems = generateExploreItems(result.data)
                    _exploreState.postValue(ViewState.Success(exploreItems))
                }
                is Result.Error -> {
                    val exception = result.exception
                    // TODO: Maybe add an error mapper
                    _exploreState.postValue(ViewState.Error(exception.localizedMessage ?: ""))
                }
            }
        }
    }

    private fun generateExploreItems(movies: List<Movie>): List<ExploreItem> {
        val items = mutableListOf<ExploreItem>()
        if (movies.isNotEmpty()) {
            // Trending Header
            items.add(ExploreItem.Header(ItemHeader(titleResId = R.string.EXPLORE_popular_title)))


            val itemMovieBigList = movies.map { movie ->
                ItemMovieBottomTitle(
                    id = movie.id,
                    title = movie.title,
                    imageUrl = movie.backdropUrl
                )
            }

            // Trending collection
            items.add(
                ExploreItem.TrendingCollection(
                    data = ItemHorizontalCollection(
                        itemMovieBigList
                    )
                )
            )


            // Next Movie
            getNextMovieToSee(movies)?.let { nextMovie ->
                val itemMovieBig = ItemMovieBig(
                    id = nextMovie.id,
                    title = nextMovie.title,
                    /** The image url will be never null! See [getNextMovieToSee] */
                    imageUrl = nextMovie.backdropUrl!!
                )
                // Header
                items.add(ExploreItem.Header(ItemHeader(titleResId = R.string.EXPLORE_next_movie_title)))
                // Item
                items.add(ExploreItem.MovieBigCard(data = itemMovieBig))
            }
        }

        // TODO: Add other items

        return items
    }

    @VisibleForTesting
    fun getNextMovieToSee(movies: List<Movie>): Movie? {
        // TODO: choose a better approach in the future
        var movieToReturn: Movie? = null
        try {
            retry(5) {
                val randomInt = (0..movies.count()).random()
                val movie = movies[randomInt]
                if (movie.backdropUrl == null) {
                    throw IllegalArgumentException("No image url!")
                } else {
                    movieToReturn = movie
                    return@retry
                }
            }
        } catch (e: Exception) {
            return null
        }
        return movieToReturn
    }


    fun fetchGenres() {
        _discoverState.postValue(ViewState.Loading)

        viewModelScope.launch(dispatcherProvider.main) {
            val genreResult = genresUseCase.execute()
            when (genreResult) {
                is Result.Success -> {
                    val genres = genreResult.data
                    _discoverState.postValue(ViewState.Success(genres))
                }
                is Result.Error -> {
                    // TODO: Maybe add an error mapper
                    val error = genreResult.exception
                    _discoverState.postValue(ViewState.Error(error.localizedMessage ?: ""))
                }
            }
        }
    }

}