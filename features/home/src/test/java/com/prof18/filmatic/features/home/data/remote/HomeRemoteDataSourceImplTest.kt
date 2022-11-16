package com.prof18.filmatic.features.home.data.remote

import com.prof18.filmatic.core.architecture.DataResult
import com.prof18.filmatic.core.error.ErrorMapper
import com.prof18.filmatic.core.error.NetworkError
import com.prof18.filmatic.features.home.data.datasource.HomeRemoteDataSource
import com.prof18.filmatic.libraries.testshared.enqueueResponse
import com.prof18.filmatic.libraries.testshared.fakes.FakeConnectivityCheckReturnNotSuccess
import com.prof18.filmatic.libraries.testshared.fakes.FakeConnectivityCheckReturnSuccess
import com.prof18.filmatic.libraries.testshared.fakes.POPULAR_MOVIES_JSON_RESPONSE
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class HomeRemoteDataSourceImplTest {

    private val mockWebServer = MockWebServer()

    private val apiService = Retrofit.Builder()
        .baseUrl(mockWebServer.url("/"))
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create(HomeApiService::class.java)

    private lateinit var systemUnderTest: HomeRemoteDataSource

    @Before
    fun setup() {
        systemUnderTest = HomeRemoteDataSourceImpl(
            homeService = apiService,
            connectivityChecker = FakeConnectivityCheckReturnSuccess(),
            errorMapper = ErrorMapper(),
        )
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `getPopularMovies returns ConnectionNotAvailable when phone not connected`() = runTest {
        systemUnderTest = HomeRemoteDataSourceImpl(
            homeService = apiService,
            connectivityChecker = FakeConnectivityCheckReturnNotSuccess(),
            errorMapper = ErrorMapper(),
        )
        mockWebServer.enqueueResponse(POPULAR_MOVIES_JSON_RESPONSE, 200)

        val response = systemUnderTest.getPopularMovies()

        assertTrue((response as DataResult.Error).error is NetworkError.ConnectionNotAvailable)
    }

    @Test
    fun `getPopularMovies returns ServiceNotWorking when http code is 500`() = runTest {
        mockWebServer.enqueueResponse(POPULAR_MOVIES_JSON_RESPONSE, 500)

        val response = systemUnderTest.getPopularMovies()

        assertTrue((response as DataResult.Error).error is NetworkError.ServiceNotWorking)
    }

    @Test
    fun `getPopularMovies returns NotAuthorized when http code is 401`() = runTest {
        mockWebServer.enqueueResponse(POPULAR_MOVIES_JSON_RESPONSE, 401)

        val response = systemUnderTest.getPopularMovies()

        assertTrue((response as DataResult.Error).error is NetworkError.NotAuthorized)
    }

    @Test
    fun `getPopularMovies returns NotFound when http code is 404`() = runTest {
        mockWebServer.enqueueResponse(POPULAR_MOVIES_JSON_RESPONSE, 404)

        val response = systemUnderTest.getPopularMovies()

        assertTrue((response as DataResult.Error).error is NetworkError.NotFound)
    }

    @Test
    fun `getPopularMovies returns ServiceUnavailable when http code is 503`() = runTest {
        mockWebServer.enqueueResponse(POPULAR_MOVIES_JSON_RESPONSE, 503)

        val response = systemUnderTest.getPopularMovies()

        assertTrue((response as DataResult.Error).error is NetworkError.ServiceUnavailable)
    }

    @Test
    fun `getPopularMovies returns Unknown when http code is 418`() = runTest {
        mockWebServer.enqueueResponse(POPULAR_MOVIES_JSON_RESPONSE, 418)

        val response = systemUnderTest.getPopularMovies()

        assertTrue((response as DataResult.Error).error is NetworkError.Unknown)
    }

    @Test
    fun `getPopularMovies returns success`() = runTest {
        mockWebServer.enqueueResponse(POPULAR_MOVIES_JSON_RESPONSE, 200)

        val response = systemUnderTest.getPopularMovies()

        assertEquals(
            337404,
            (response as DataResult.Success).data.movieResults.first().id,
        )
    }
}
