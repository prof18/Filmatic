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
import com.prof18.filmatic.core.architecture.Result
import com.prof18.filmatic.features.home.FakeErrorHomeRepository
import com.prof18.filmatic.features.home.FakeSuccessHomeRepository
import com.prof18.filmatic.features.home.domain.HomeRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*
import org.junit.Test

@ExperimentalCoroutinesApi
class GetGenresUseCaseTest {

    @Test
    fun getGenreUseCaseReturnsData() = runBlockingTest {
        val successRepository = FakeSuccessHomeRepository()
        val successResult = Result.Success(successRepository.genres)
        val getGenresUseCase = GetGenresUseCase(successRepository)

        val result = getGenresUseCase.execute()
        assertEquals(successResult, result)
    }

    @Test
    fun getGenreUseCaseReturnsError() = runBlockingTest {
        val fakeErrorHomeRepository = FakeErrorHomeRepository()
        val getGenresUseCase = GetGenresUseCase(fakeErrorHomeRepository)

        val result = getGenresUseCase.execute()

        assertEquals((result as Result.Error).exception, fakeErrorHomeRepository.exception)
    }
}