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

package com.prof18.filmatic.features.home.utils

import com.github.javafaker.Faker
import com.prof18.filmatic.features.home.data.models.MovieModel
import com.prof18.filmatic.features.home.domain.entities.Movie
import com.prof18.filmatic.features.home.remote.model.MovieResult
import com.prof18.filmatic.features.home.remote.model.PopularMoviesResult

object DataFactory {

    fun getMovie(): Movie {
        val faker = Faker()
        return Movie(
            id = faker.number().randomDigit(),
            title = faker.harryPotter().book(),
            _backdropPath = faker.lorem().word()
        )
    }

    fun getMovieWithNoBackdrop(): Movie {
        val faker = Faker()
        return Movie(
            id = faker.number().randomDigit(),
            title = faker.harryPotter().book(),
            _backdropPath = null
        )
    }

    fun getPopularMovies(count: Int): List<Movie> {
        val movies = mutableListOf<Movie>()
        repeat(count) {
            movies.add(getMovie())
        }
        return movies
    }

    fun getMovieResult(): MovieResult {
        val faker = Faker()
        return MovieResult(
            adult = faker.random().nextBoolean(),
            backdropPath = faker.lorem().word(),
            genreIds = listOf(faker.number().randomDigit()),
            id = faker.number().randomDigit(),
            originalLanguage = faker.lorem().word(),
            originalTitle = faker.lorem().sentence(),
            overview = faker.lorem().paragraph(),
            popularity = faker.random().nextDouble(),
            posterPath = faker.lorem().word(),
            releaseDate = faker.date().birthday().toString(),
            title = faker.lorem().sentence(),
            video = faker.random().nextBoolean(),
            voteAverage = faker.random().nextDouble(),
            voteCount = faker.number().randomDigit()
        )
    }

    fun getResults(count: Int): List<MovieResult> {
        val results = mutableListOf<MovieResult>()
        repeat(count) {
            results.add(getMovieResult())
        }
        return results
    }

    fun getPopularMovieResult(movieResults: List<MovieResult>): PopularMoviesResult {
        val faker = Faker()
        return PopularMoviesResult(
            page = faker.number().randomDigit(),
            movieResults = movieResults,
            totalPages = faker.number().randomDigit(),
            totalResults = faker.number().randomDigit()
        )
    }


    fun getMovieModel(): MovieModel {
        val faker = Faker()
        return MovieModel(
            backdropPath = faker.lorem().word(),
            genreIds = listOf(faker.number().randomDigit()),
            id = faker.number().randomDigit(),
            overview = faker.lorem().sentence(),
            posterPath = faker.lorem().word(),
            releaseDate = faker.date().birthday().toString(),
            title = faker.lorem().sentence()
        )
    }
}