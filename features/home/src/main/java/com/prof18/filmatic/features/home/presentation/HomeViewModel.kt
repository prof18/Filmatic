package com.prof18.filmatic.features.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prof18.filmatic.core.architecture.DataResult
import com.prof18.filmatic.core.architecture.UIState
import com.prof18.filmatic.core.error.getStringResId
import com.prof18.filmatic.features.home.R
import com.prof18.filmatic.features.home.domain.entities.Movie
import com.prof18.filmatic.features.home.domain.usecases.GetPopularMoviesUseCase
import com.prof18.filmatic.features.home.presentation.mapper.toDetailState
import com.prof18.filmatic.features.home.presentation.state.HomeListItem
import com.prof18.filmatic.features.home.presentation.state.MovieDetailState
import com.prof18.filmatic.libraries.uicomponents.listitem.ItemHeader
import com.prof18.filmatic.libraries.uicomponents.listitem.ItemMovieBig
import com.prof18.filmatic.libraries.uicomponents.listitem.ItemMovieBottomText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val popularMoviesUseCase: GetPopularMoviesUseCase,
) : ViewModel() {

    private val _homeState = MutableStateFlow<UIState<List<HomeListItem>>>(UIState.Loading)
    val homeState: StateFlow<UIState<List<HomeListItem>>> = _homeState.asStateFlow()

    private val _movieDetailState = MutableStateFlow<UIState<MovieDetailState>>(UIState.Loading)
    val movieDetailState: StateFlow<UIState<MovieDetailState>> = _movieDetailState.asStateFlow()

    private var movies: List<Movie> = listOf()

    fun getHomeState() {
        viewModelScope.launch {
            when (val result = popularMoviesUseCase()) {
                is DataResult.Success -> {
                    movies = result.data
                    val homeData = generateHomeData()
                    if (result.data.isNotEmpty()) {
                        _homeState.value = UIState.Success(homeData)
                    } else {
                        _homeState.value = UIState.NoData
                    }
                }
                is DataResult.Error -> {
                    _homeState.value = UIState.Error(result.error.getStringResId())
                }
            }
        }
    }

    fun reloadData() {
        movies = listOf()
        _homeState.value = UIState.Loading
        getHomeState()
    }

    private fun generateHomeData(): List<HomeListItem> {
        val items = mutableListOf<HomeListItem>()
        if (movies.isNotEmpty()) {
            // Trending Header
            items.add(
                HomeListItem.Header(
                    ItemHeader(titleResId = R.string.popular_title)
                )
            )

            // Trending collection
            items.add(
                HomeListItem.TrendingCollection(
                    data = movies.map { movie ->
                        ItemMovieBottomText(
                            id = movie.id,
                            title = movie.title,
                            imageUrl = movie.posterPath
                        )
                    }
                )
            )

            movies.random().let { nextMovie ->
                val itemMovieBig = ItemMovieBig(
                    id = nextMovie.id,
                    title = nextMovie.title,
                    imageUrl = nextMovie.posterPath
                )
                // Header
                items.add(HomeListItem.Header(ItemHeader(titleResId = R.string.next_movie_title)))
                // Item
                items.add(HomeListItem.MovieBigCard(data = itemMovieBig))
            }
        }

        return items
    }

    fun getMovie(movieId: Int) {
        val movie = movies.firstOrNull { it.id == movieId }
        if (movie == null) {
            _movieDetailState.value = UIState.NoData
        } else {
            _movieDetailState.value = UIState.Success(movie.toDetailState())
        }
    }
}
