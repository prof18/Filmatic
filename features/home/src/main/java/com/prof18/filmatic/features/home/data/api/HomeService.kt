package com.prof18.filmatic.features.home.data.api

import com.prof18.filmatic.features.home.data.discovery.model.GenreResponse
import com.prof18.filmatic.features.home.data.popular.model.PopularMovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface HomeService {

    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("language") locale: String): PopularMovieResponse

    @GET("genre/movie/list")
    suspend fun getGenres(@Query("language") locale: String): GenreResponse

}