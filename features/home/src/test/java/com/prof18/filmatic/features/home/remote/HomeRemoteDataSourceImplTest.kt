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

package com.prof18.filmatic.features.home.remote

import com.prof18.filmatic.core.architecture.Result
import com.prof18.filmatic.features.home.DataFactory
import com.prof18.filmatic.features.home.data.remote.HomeRemoteDataSource
import com.prof18.filmatic.features.home.remote.api.HomeService
import com.prof18.filmatic.features.home.remote.mapper.MovieResultMapper
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.net.HttpURLConnection

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class HomeRemoteDataSourceImplTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var homeService: HomeService
    private lateinit var remoteDataSource: HomeRemoteDataSource

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        mockWebServer.start()

        homeService = Retrofit.Builder()
            .client(OkHttpClient.Builder().build())
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(HomeService::class.java)

        remoteDataSource = HomeRemoteDataSourceImpl(homeService, MovieResultMapper())

    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun getPopularMoviesReturnsData() = runBlocking {
        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(DataFactory.getPopularMovieJsonResponse())

        mockWebServer.enqueue(response)


        val dataSourceResult = remoteDataSource.getPopularMovies()
        val movieModelList = (dataSourceResult as Result.Success).data

        assertEquals(movieModelList[0].backdropPath, "/t93doi7EzoqLFckidrGGnukFPwd.jpg")
        assertEquals(movieModelList[0].genreIds, listOf(28, 80, 53))
        assertEquals(movieModelList[0].id, 339095)
        assertEquals(
            movieModelList[0].overview,
            "In the not-too-distant future, where as a final response to crime and terrorism, the U.S. government plans to broadcast a signal that will make it impossible for anyone to knowingly break the law."
        )
        assertEquals(movieModelList[0].posterPath, "/ygCQnDEqUEIamBpdQdDYnFfxvgM.jpg")
        assertEquals(movieModelList[0].releaseDate, "2020-06-05")
        assertEquals(movieModelList[0].title, "The Last Days of American Crime")
    }

    @Test
    fun getGenresReturnsData() = runBlocking {
        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(DataFactory.getAllGenresJsonResponse())

        mockWebServer.enqueue(response)

        val result = remoteDataSource.getAllGenres()
        val genres = (result as Result.Success).data

        assertEquals(genres.genres[0].name, "Action")
        assertEquals(genres.genres[1].name, "Adventure")
    }

    @Test
    fun getPopularMoviesReturnsError() = runBlocking {
        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_NOT_FOUND)
            .setBody(DataFactory.getPopularMovieJsonResponse())

        mockWebServer.enqueue(response)

        val result = remoteDataSource.getPopularMovies()
        assertTrue(result is Result.Error)
    }

    @Test
    fun getGenresReturnsError() = runBlocking {
        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_NOT_FOUND)
            .setBody(DataFactory.getAllGenresJsonResponse())

        mockWebServer.enqueue(response)

        val result = remoteDataSource.getAllGenres()
        assertTrue(result is Result.Error)
    }
}