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

object MockResponses {

    val popularMovieResponse = """
        {
          "page": 1,
          "total_results": 10000,
          "total_pages": 500,
          "results": [
            {
              "popularity": 492.069,
              "vote_count": 3023,
              "video": false,
              "poster_path": "/xJUILftRf6TJxloOgrilOTJfeOn.jpg",
              "id": 419704,
              "adult": false,
              "backdrop_path": "/5BwqwxMEjeFtdknRV792Svo0K1v.jpg",
              "original_language": "en",
              "original_title": "Ad Astra",
              "genre_ids": [
                18,
                878
              ],
              "title": "Ad Astra",
              "vote_average": 6,
              "overview": "The near future, a time when both hope and hardships drive humanity to look to the stars and beyond. While a mysterious phenomenon menaces to destroy life on planet Earth, astronaut Roy McBride undertakes a mission across the immensity of space and its many perils to uncover the truth about a lost expedition that decades before boldly faced emptiness and silence in search of the unknown.",
              "release_date": "2019-09-17"
            },
            {
              "popularity": 312.399,
              "vote_count": 6802,
              "video": false,
              "poster_path": "/khlcsYHr7Hi4Tkymc7UGDvcUcx.jpg",
              "id": 56292,
              "adult": false,
              "backdrop_path": "/zNyNPn86myH5Ix1demDa5dfYDao.jpg",
              "original_language": "en",
              "original_title": "Mission: Impossible - Ghost Protocol",
              "genre_ids": [
                28,
                12,
                53
              ],
              "title": "Mission: Impossible - Ghost Protocol",
              "vote_average": 7,
              "overview": "Ethan Hunt and his team are racing against time to track down a dangerous terrorist named Hendricks, who has gained access to Russian nuclear launch codes and is planning a strike on the United States. An attempt to stop him ends in an explosion causing severe destruction to the Kremlin and the IMF to be implicated in the bombing, forcing the President to disavow them. No longer being aided by the government, Ethan and his team chase Hendricks around the globe, although they might still be too late to stop a disaster.",
              "release_date": "2011-12-07"
            },
            {
              "popularity": 231.061,
              "vote_count": 204,
              "video": false,
              "poster_path": "/7W0G3YECgDAfnuiHG91r8WqgIOe.jpg",
              "id": 446893,
              "adult": false,
              "backdrop_path": "/qsxhnirlp7y4Ae9bd11oYJSX59j.jpg",
              "original_language": "en",
              "original_title": "Trolls World Tour",
              "genre_ids": [
                12,
                16,
                35,
                14,
                10402,
                10751
              ],
              "title": "Trolls World Tour",
              "vote_average": 7.7,
              "overview": "Queen Poppy and Branch make a surprising discovery — there are other Troll worlds beyond their own, and their distinct differences create big clashes between these various tribes. When a mysterious threat puts all of the Trolls across the land in danger, Poppy, Branch, and their band of friends must embark on an epic quest to create harmony among the feuding Trolls to unite them against certain doom.",
              "release_date": "2020-03-12"
            }
          ]
        }
        
    """.trimIndent()

}