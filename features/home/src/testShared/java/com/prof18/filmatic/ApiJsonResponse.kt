package com.prof18.filmatic

// It's just a raw file
@Suppress("MaxLineLength")
const val POPULAR_MOVIES_JSON_RESPONSE = """
    {
      "page": 1,
      "results": [
        {
          "adult": false,
          "backdrop_path": "/6MKr3KgOLmzOP6MSuZERO41Lpkt.jpg",
          "genre_ids": [
            35,
            80
          ],
          "id": 337404,
          "original_language": "en",
          "original_title": "Cruella",
          "overview": "In 1970s London amidst the punk rock revolution, a young grifter named Estella is determined to make a name for herself with her designs. She befriends a pair of young thieves who appreciate her appetite for mischief, and together they are able to build a life for themselves on the London streets. One day, Estella’s flair for fashion catches the eye of the Baroness von Hellman, a fashion legend who is devastatingly chic and terrifyingly haute. But their relationship sets in motion a course of events and revelations that will cause Estella to embrace her wicked side and become the raucous, fashionable and revenge-bent Cruella.",
          "popularity": 5771.292,
          "poster_path": "/rTh4K5uw9HypmpGslcKd4QfHl93.jpg",
          "release_date": "2021-05-26",
          "title": "Cruella",
          "video": false,
          "vote_average": 8.6,
          "vote_count": 2515
        },
        {
          "adult": false,
          "backdrop_path": "/qi6Edc1OPcyENecGtz8TF0DUr9e.jpg",
          "genre_ids": [
            27,
            9648,
            53
          ],
          "id": 423108,
          "original_language": "en",
          "original_title": "The Conjuring: The Devil Made Me Do It",
          "overview": "Paranormal investigators Ed and Lorraine Warren encounter what would become one of the most sensational cases from their files. The fight for the soul of a young boy takes them beyond anything they'd ever seen before, to mark the first time in U.S. history that a murder suspect would claim demonic possession as a defense.",
          "popularity": 5355.58,
          "poster_path": "/xbSuFiJbbBWCkyCCKIMfuDCA4yV.jpg",
          "release_date": "2021-05-25",
          "title": "The Conjuring: The Devil Made Me Do It",
          "video": false,
          "vote_average": 8.3,
          "vote_count": 1832
        }
      ],
      "total_pages": 500,
      "total_results": 10000
    }
"""

const val POPULAR_MOVIES_EMPTY_JSON_RESPONSE = """
    {
      "page": 1,
      "results": [],
      "total_pages": 500,
      "total_results": 10000
    }
"""
