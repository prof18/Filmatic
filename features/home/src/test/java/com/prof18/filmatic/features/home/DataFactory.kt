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

package com.prof18.filmatic.features.home

import com.github.javafaker.Faker
import com.prof18.filmatic.features.home.data.models.MovieModel
import com.prof18.filmatic.features.home.domain.entities.Genre
import com.prof18.filmatic.features.home.domain.entities.Movie
import com.prof18.filmatic.features.home.remote.model.*

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

    fun getGenreResponse(): AllGenresResponse {
        val faker = Faker()
        return AllGenresResponse(listOf(
            GenreResult(
                id = faker.number().randomDigit(),
                name = faker.lorem().word()
            ),
            GenreResult(
                id = faker.number().randomDigit(),
                name = faker.lorem().word()
            )
        ))
    }

    fun getGenreResult(): GenreResult {
        val faker = Faker()
        return GenreResult(
            id = faker.number().randomDigit(),
            name = faker.lorem().word()
        )
    }

    fun getGenre(): Genre {
        val faker = Faker()
        return Genre(
            id = faker.number().randomDigit(),
            name = faker.lorem().word()
        )
    }

    fun getPopularMovieJsonResponse(): String {
        return """
            {
              "page": 1,
              "total_results": 10000,
              "total_pages": 500,
              "results": [
                {
                  "popularity": 122.637,
                  "vote_count": 114,
                  "video": false,
                  "poster_path": "/ygCQnDEqUEIamBpdQdDYnFfxvgM.jpg",
                  "id": 339095,
                  "adult": false,
                  "backdrop_path": "/t93doi7EzoqLFckidrGGnukFPwd.jpg",
                  "original_language": "en",
                  "original_title": "The Last Days of American Crime",
                  "genre_ids": [
                    28,
                    80,
                    53
                  ],
                  "title": "The Last Days of American Crime",
                  "vote_average": 5.6,
                  "overview": "In the not-too-distant future, where as a final response to crime and terrorism, the U.S. government plans to broadcast a signal that will make it impossible for anyone to knowingly break the law.",
                  "release_date": "2020-06-05"
                },
                {
                  "popularity": 153.887,
                  "vote_count": 4539,
                  "video": false,
                  "poster_path": "/aQvJ5WPzZgYVDrxLX4R6cLJCEaQ.jpg",
                  "id": 454626,
                  "adult": false,
                  "backdrop_path": "/stmYfCUGd8Iy6kAMBr6AmWqx8Bq.jpg",
                  "original_language": "en",
                  "original_title": "Sonic the Hedgehog",
                  "genre_ids": [
                    28,
                    35,
                    878,
                    10751
                  ],
                  "title": "Sonic the Hedgehog",
                  "vote_average": 7.5,
                  "overview": "Based on the global blockbuster videogame franchise from Sega, Sonic the Hedgehog tells the story of the world’s speediest hedgehog as he embraces his new home on Earth. In this live-action adventure comedy, Sonic and his new best friend team up to defend the planet from the evil genius Dr. Robotnik and his plans for world domination.",
                  "release_date": "2020-02-12"
                }
              ]
            }
        """.trimIndent()
    }

    fun getAllGenresJsonResponse() : String {
        return """
            {
              "genres": [
                {
                  "id": 28,
                  "name": "Action"
                },
                {
                  "id": 12,
                  "name": "Adventure"
                },
                {
                  "id": 16,
                  "name": "Animation"
                },
                {
                  "id": 35,
                  "name": "Comedy"
                },
                {
                  "id": 80,
                  "name": "Crime"
                },
                {
                  "id": 99,
                  "name": "Documentary"
                },
                {
                  "id": 18,
                  "name": "Drama"
                },
                {
                  "id": 10751,
                  "name": "Family"
                },
                {
                  "id": 14,
                  "name": "Fantasy"
                },
                {
                  "id": 36,
                  "name": "History"
                },
                {
                  "id": 27,
                  "name": "Horror"
                },
                {
                  "id": 10402,
                  "name": "Music"
                },
                {
                  "id": 9648,
                  "name": "Mystery"
                },
                {
                  "id": 10749,
                  "name": "Romance"
                },
                {
                  "id": 878,
                  "name": "Science Fiction"
                },
                {
                  "id": 10770,
                  "name": "TV Movie"
                },
                {
                  "id": 53,
                  "name": "Thriller"
                },
                {
                  "id": 10752,
                  "name": "War"
                },
                {
                  "id": 37,
                  "name": "Western"
                }
              ]
            }
        """.trimIndent()
    }
}