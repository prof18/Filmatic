package com.prof18.filmatic.features.home.data.remote

import com.prof18.filmatic.features.home.data.remote.dto.PopularMoviesDTO
import retrofit2.Response
import retrofit2.http.GET

interface HomeApiService {

    @GET(ApiEndpoint.POPULAR_MOVIES)
    suspend fun getPopularMovies(): Response<PopularMoviesDTO>
}
