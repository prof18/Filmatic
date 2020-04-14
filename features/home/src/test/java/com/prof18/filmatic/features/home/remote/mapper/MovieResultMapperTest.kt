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

package com.prof18.filmatic.features.home.remote.mapper

import com.prof18.filmatic.features.home.DataFactory
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class MovieResultMapperTest {

    private val mapper = MovieResultMapper()

    @Test
    fun mapperMapsCorrectly() {
        val movieResult = DataFactory.getMovieResult()
        val movie = mapper.map(movieResult)

        assertEquals(movie.backdropPath, movieResult.backdropPath)
        assertEquals(movie.genreIds, movieResult.genreIds)
        assertEquals(movie.id, movieResult.id)
        assertEquals(movie.overview, movieResult.overview)
        assertEquals(movie.posterPath, movieResult.posterPath)
        assertEquals(movie.releaseDate, movieResult.releaseDate)
        assertEquals(movie.title, movieResult.title)
    }
}