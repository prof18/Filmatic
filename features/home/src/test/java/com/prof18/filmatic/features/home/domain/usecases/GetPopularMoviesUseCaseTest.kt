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

import com.prof18.filmatic.core.architecture.Result
import com.prof18.filmatic.features.home.FakeErrorHomeRepository
import com.prof18.filmatic.features.home.FakeSuccessHomeRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class GetPopularMoviesUseCaseTest {
    private val successHomeRepository = FakeSuccessHomeRepository()
    private val errorHomeRepository = FakeErrorHomeRepository()

    private val successGetPopularMoviesUseCase = GetPopularMoviesUseCase(successHomeRepository)
    private val errorGetPopularMoviesUseCase = GetPopularMoviesUseCase(errorHomeRepository)


    @Test
    fun getPopularMoviesReturnsData() = runBlocking {
        val result = successGetPopularMoviesUseCase.execute()
        assertEquals(successHomeRepository.movies, (result as Result.Success).data)
    }

    @Test
    fun getPopularMoviesReturnsError() = runBlocking {
        val result = errorGetPopularMoviesUseCase.execute()
        assertEquals(errorHomeRepository.exception, (result as Result.Error).exception)
    }
}


