package com.prof18.filmatic.features.home

import com.github.javafaker.Faker
import com.prof18.filmatic.features.home.data.remote.dto.MovieDTO
import com.prof18.filmatic.features.home.data.remote.dto.PopularMoviesDTO

object DataFactory {

    val faker = Faker()

    val actionAdventureMovie = MovieDTO(
        genreIds = listOf(),
        id = 1,
        adult = faker.random().nextBoolean(),
        backdropPath = faker.lorem().word(),
        originalLanguage = faker.lorem().word(),
        originalTitle = faker.lorem().sentence(),
        overview = faker.lorem().paragraph(),
        popularity = faker.random().nextDouble(),
        posterPath = faker.lorem().word(),
        releaseDate = faker.date().birthday().toString(),
        title = faker.lorem().sentence(),
        video = faker.random().nextBoolean(),
        voteAverage = faker.random().nextDouble(),
        voteCount = faker.number().randomDigit(),
    )

    val adventureMovie = MovieDTO(
        genreIds = listOf(),
        id = 2,
        adult = faker.random().nextBoolean(),
        backdropPath = faker.lorem().word(),
        originalLanguage = faker.lorem().word(),
        originalTitle = faker.lorem().sentence(),
        overview = faker.lorem().paragraph(),
        popularity = faker.random().nextDouble(),
        posterPath = faker.lorem().word(),
        releaseDate = faker.date().birthday().toString(),
        title = faker.lorem().sentence(),
        video = faker.random().nextBoolean(),
        voteAverage = faker.random().nextDouble(),
        voteCount = faker.number().randomDigit(),
    )

    val actionMovie = MovieDTO(
        genreIds = listOf(),
        id = 3,
        adult = faker.random().nextBoolean(),
        backdropPath = faker.lorem().word(),
        originalLanguage = faker.lorem().word(),
        originalTitle = faker.lorem().sentence(),
        overview = faker.lorem().paragraph(),
        popularity = faker.random().nextDouble(),
        posterPath = faker.lorem().word(),
        releaseDate = faker.date().birthday().toString(),
        title = faker.lorem().sentence(),
        video = faker.random().nextBoolean(),
        voteAverage = faker.random().nextDouble(),
        voteCount = faker.number().randomDigit(),
    )

    val popularMoviesDTO = PopularMoviesDTO(
        page = 1,
        movieResults = listOf(actionMovie, actionAdventureMovie, adventureMovie),
        totalPages = 1,
        totalResults = 3,
    )

    val emptyPopularMovieDTO = PopularMoviesDTO(
        page = 1,
        movieResults = listOf(),
        totalPages = 1,
        totalResults = 0,
    )
}
