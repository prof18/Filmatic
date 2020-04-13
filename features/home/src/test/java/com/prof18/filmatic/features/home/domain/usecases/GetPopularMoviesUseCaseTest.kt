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

package com.prof18.filmatic.features.home.domain.usecases

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.prof18.filmatic.core.architecture.Result
import com.prof18.filmatic.features.home.domain.HomeRepository
import com.prof18.filmatic.features.home.utils.DataFactory
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.io.IOException

@RunWith(JUnit4::class)
class GetPopularMoviesUseCaseTest {

    private val homeRepository = mock<HomeRepository>()
    private val getPopularMoviesUseCase = GetPopularMoviesUseCase(homeRepository)

    @Test
    fun getPopularMoviesReturnsData() = runBlocking {
        val successResult = Result.Success(DataFactory.getPopularMovies(3))
        whenever(homeRepository.getPopularMovies()).thenReturn(successResult)

        val result = getPopularMoviesUseCase.execute()

        verify(homeRepository).getPopularMovies()
        assertEquals(successResult, result)
    }

    @Test
    fun getPopularMoviesReturnsError() = runBlocking {
        val errorResponse = Result.Error(IOException())
        whenever(homeRepository.getPopularMovies())
            .thenReturn(errorResponse)

        val result = getPopularMoviesUseCase.execute()
        assertEquals(errorResponse, result)
    }
}


