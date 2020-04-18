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

import com.prof18.filmatic.features.home.remote.model.MovieResult
import com.prof18.filmatic.features.home.remote.model.PopularMoviesResult

object MockResponses {

    fun getEmptyPopularMovieResult(): PopularMoviesResult {
        return PopularMoviesResult(
            page = 1,
            movieResults = listOf(),
            totalPages = 500,
            totalResults = 1000
        )
    }

    fun getPopularMovieResult(): PopularMoviesResult {
        return PopularMoviesResult(
            page = 1,
            movieResults = listOf(
                MovieResult(
                    adult = false,
                    backdropPath = "/5BwqwxMEjeFtdknRV792Svo0K1v.jpg",
                    genreIds = listOf(18, 878),
                    id = 419704,
                    originalLanguage = "en",
                    originalTitle = "Ad Astra",
                    overview = "The near future, a time when both hope and hardships drive humanity to look to the stars and beyond. While a mysterious phenomenon menaces to destroy life on planet Earth, astronaut Roy McBride undertakes a mission across the immensity of space and its many perils to uncover the truth about a lost expedition that decades before boldly faced emptiness and silence in search of the unknown.",
                    popularity = 492.069,
                    posterPath = "/xJUILftRf6TJxloOgrilOTJfeOn.jpg",
                    releaseDate = "2019-09-17",
                    title = "Ad Astra",
                    video = false,
                    voteAverage = 6.0,
                    voteCount = 3023
                ),
                MovieResult(
                    adult = false,
                    backdropPath = "/zNyNPn86myH5Ix1demDa5dfYDao.jpg",
                    genreIds = listOf(
                        28,
                        12,
                        53
                    ),
                    id = 56292,
                    originalLanguage = "en",
                    originalTitle = "Mission: Impossible - Ghost Protocol",
                    overview = "Ethan Hunt and his team are racing against time to track down a dangerous terrorist named Hendricks, who has gained access to Russian nuclear launch codes and is planning a strike on the United States. An attempt to stop him ends in an explosion causing severe destruction to the Kremlin and the IMF to be implicated in the bombing, forcing the President to disavow them. No longer being aided by the government, Ethan and his team chase Hendricks around the globe, although they might still be too late to stop a disaster.",
                    popularity = 312.399,
                    posterPath = "/khlcsYHr7Hi4Tkymc7UGDvcUcx.jpg",
                    releaseDate = "2011-12-07",
                    title = "Mission: Impossible - Ghost Protocol",
                    video = false,
                    voteAverage = 7.0,
                    voteCount = 6802
                ),
                MovieResult(
                    adult = false,
                    backdropPath = "/qsxhnirlp7y4Ae9bd11oYJSX59j.jpg",
                    genreIds = listOf(
                        12,
                        16,
                        35,
                        14,
                        10402,
                        10751
                    ),
                    id = 446893,
                    originalLanguage = "en",
                    originalTitle = "Trolls World Tour",
                    overview = "Queen Poppy and Branch make a surprising discovery — there are other Troll worlds beyond their own, and their distinct differences create big clashes between these various tribes. When a mysterious threat puts all of the Trolls across the land in danger, Poppy, Branch, and their band of friends must embark on an epic quest to create harmony among the feuding Trolls to unite them against certain doom.",
                    popularity = 231.061,
                    posterPath = "/7W0G3YECgDAfnuiHG91r8WqgIOe.jpg",
                    releaseDate = "2020-03-12",
                    title = "Trolls World Tour",
                    video = false,
                    voteAverage = 7.7,
                    voteCount = 204
                )
            ),
            totalPages = 500,
            totalResults = 1000
        )
    }

    val popularMovieResponse = """
{
  "page": 1,
  "results": [
    {
      "adult": false,
      "backdrop_path": "/5BwqwxMEjeFtdknRV792Svo0K1v.jpg",
      "genre_ids": [
        18,
        878
      ],
      "id": 419704,
      "original_language": "en",
      "original_title": "Ad Astra",
      "overview": "The near future, a time when both hope and hardships drive humanity to look to the stars and beyond. While a mysterious phenomenon menaces to destroy life on planet Earth, astronaut Roy McBride undertakes a mission across the immensity of space and its many perils to uncover the truth about a lost expedition that decades before boldly faced emptiness and silence in search of the unknown.",
      "popularity": 492.069,
      "poster_path": "/xJUILftRf6TJxloOgrilOTJfeOn.jpg",
      "release_date": "2019-09-17",
      "title": "Ad Astra",
      "video": false,
      "vote_average": 6,
      "vote_count": 3023
    },
    {
      "adult": false,
      "backdrop_path": "/zNyNPn86myH5Ix1demDa5dfYDao.jpg",
      "genre_ids": [
        28,
        12,
        53
      ],
      "id": 56292,
      "original_language": "en",
      "original_title": "Mission: Impossible - Ghost Protocol",
      "overview": "Ethan Hunt and his team are racing against time to track down a dangerous terrorist named Hendricks, who has gained access to Russian nuclear launch codes and is planning a strike on the United States. An attempt to stop him ends in an explosion causing severe destruction to the Kremlin and the IMF to be implicated in the bombing, forcing the President to disavow them. No longer being aided by the government, Ethan and his team chase Hendricks around the globe, although they might still be too late to stop a disaster.",
      "popularity": 312.399,
      "poster_path": "/khlcsYHr7Hi4Tkymc7UGDvcUcx.jpg",
      "release_date": "2011-12-07",
      "title": "Mission: Impossible - Ghost Protocol",
      "video": false,
      "vote_average": 7,
      "vote_count": 6802
    },
    {
      "adult": false,
      "backdrop_path": "/qsxhnirlp7y4Ae9bd11oYJSX59j.jpg",
      "genre_ids": [
        12,
        16,
        35,
        14,
        10402,
        10751
      ],
      "id": 446893,
      "original_language": "en",
      "original_title": "Trolls World Tour",
      "overview": "Queen Poppy and Branch make a surprising discovery — there are other Troll worlds beyond their own, and their distinct differences create big clashes between these various tribes. When a mysterious threat puts all of the Trolls across the land in danger, Poppy, Branch, and their band of friends must embark on an epic quest to create harmony among the feuding Trolls to unite them against certain doom.",
      "popularity": 231.061,
      "poster_path": "/7W0G3YECgDAfnuiHG91r8WqgIOe.jpg",
      "release_date": "2020-03-12",
      "title": "Trolls World Tour",
      "video": false,
      "vote_average": 7.7,
      "vote_count": 204
    }
  ],
  "total_pages": 500,
  "total_results": 10000
}
        
    """.trimIndent()

}