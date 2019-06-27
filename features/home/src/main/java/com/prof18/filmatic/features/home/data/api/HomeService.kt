package com.prof18.filmatic.features.home.data.api

import com.prof18.filmatic.features.home.data.popular.model.PopularMovieResponse
import retrofit2.http.GET

interface HomeService {

    @GET("movie/popular")
    suspend fun getPopularMovies(): PopularMovieResponse

}


/*

https://api.themoviedb.org/3/movie/popular?api_key=23f59b6f1d9ecc8065e8e705e9d61283&language=it-IT&page=1

 */