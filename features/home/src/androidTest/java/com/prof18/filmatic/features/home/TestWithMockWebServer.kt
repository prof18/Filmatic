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

//
//
//fun getEmptyMovieJsonResponse(): String {
//    return """
//            {
//                "page": 1,
//                "total_results": 10000,
//                "total_pages": 500,
//                "results": []
//            }
//        """.trimIndent()
//}
//
//fun getPopularMovieJsonResponse(): String {
//    return """
//            {
//              "page": 1,
//              "total_results": 10000,
//              "total_pages": 500,
//              "results": [
//                {
//                  "popularity": 122.637,
//                  "vote_count": 114,
//                  "video": false,
//                  "poster_path": "/ygCQnDEqUEIamBpdQdDYnFfxvgM.jpg",
//                  "id": 339095,
//                  "adult": false,
//                  "backdrop_path": "/t93doi7EzoqLFckidrGGnukFPwd.jpg",
//                  "original_language": "en",
//                  "original_title": "The Last Days of American Crime",
//                  "genre_ids": [
//                    28,
//                    80,
//                    53
//                  ],
//                  "title": "The Last Days of American Crime",
//                  "vote_average": 5.6,
//                  "overview": "In the not-too-distant future, where as a final response to crime and terrorism, the U.S. government plans to broadcast a signal that will make it impossible for anyone to knowingly break the law.",
//                  "release_date": "2020-06-05"
//                },
//                {
//                  "popularity": 153.887,
//                  "vote_count": 4539,
//                  "video": false,
//                  "poster_path": "/aQvJ5WPzZgYVDrxLX4R6cLJCEaQ.jpg",
//                  "id": 454626,
//                  "adult": false,
//                  "backdrop_path": "/stmYfCUGd8Iy6kAMBr6AmWqx8Bq.jpg",
//                  "original_language": "en",
//                  "original_title": "Sonic the Hedgehog",
//                  "genre_ids": [
//                    28,
//                    35,
//                    878,
//                    10751
//                  ],
//                  "title": "Sonic the Hedgehog",
//                  "vote_average": 7.5,
//                  "overview": "Based on the global blockbuster videogame franchise from Sega, Sonic the Hedgehog tells the story of the world’s speediest hedgehog as he embraces his new home on Earth. In this live-action adventure comedy, Sonic and his new best friend team up to defend the planet from the evil genius Dr. Robotnik and his plans for world domination.",
//                  "release_date": "2020-02-12"
//                }
//              ]
//            }
//        """.trimIndent()
//}
//
//internal class MockServerDispatcher {
//    /**
//     * Return ok response from mock server
//     */
//    internal inner class RequestDispatcher : Dispatcher() {
//        override fun dispatch(request: RecordedRequest): MockResponse {
//            if (request.path.toString().contains("movie/popular")) {
//                return MockResponse().setResponseCode(200).setBody(MockResponses.getPopularMovieJsonResponse())
//            }
//            return MockResponse().setResponseCode(404)
//        }
//    }
//
//    /**
//     * Return error response from mock server
//     */
//    internal inner class ErrorDispatcher : Dispatcher() {
//        override fun dispatch(request: RecordedRequest): MockResponse {
//            return MockResponse().setResponseCode(400)
//        }
//    }
//
//    internal inner class EmptyDispatcher : Dispatcher() {
//        override fun dispatch(request: RecordedRequest): MockResponse {
//            return MockResponse().setResponseCode(200).setBody(MockResponses.getEmptyMovieJsonResponse())
//        }
//    }
//}
//
//
//@ExperimentalCoroutinesApi
//@RunWith(AndroidJUnit4::class)
//class ExploreFragmentTest {
//
//    @get:Rule
//    var instantTaskExecutorRule = InstantTaskExecutorRule()
//
//
//
//    private lateinit var mockWebServer: MockWebServer
//
//    private val app =
//        InstrumentationRegistry.getInstrumentation().targetContext.applicationContext as HomeTestApp
//    private val okHttpClient = app.provideCoreComponent().provideOkHttpClient()
//
//    private val res = OkHttp3IdlingResource.create(
//        "okhttp",
//        okHttpClient
//    )
//
//    private val homeComponent = (app.getHomeComponent() as TestHomeComponent)
//    private val testDispatcher =
//        homeComponent.provideCoroutineDisparcherProvider().main as TestCoroutineDispatcher
//
//    @get:Rule
//    var coroutinesTestRule = CoroutinesTestRule(testDispatcher)
//
//    @Before
//    fun setup() {
//        IdlingRegistry.getInstance().register(res)
//        mockWebServer = MockWebServer()
//        mockWebServer.start(8080)
//    }
//
//    @After
//    fun tearDown() {
//        mockWebServer.shutdown()
//        testDispatcher.cleanupTestCoroutines()
//        IdlingRegistry.getInstance().unregister(res)
//    }
//
//    @Test
//    fun checkShowsData() = testDispatcher.runBlockingTest {
//        mockWebServer.dispatcher = MockServerDispatcher().RequestDispatcher()
//
//        launchFragmentInContainer<ExploreFragment>(themeResId = R.style.AppTheme)
//
//        onView(listMatcher().atPosition(0))
//            .check(matches(hasDescendant(withText(getResourceString(R.string.EXPLORE_popular_title)))))
//
//        onView(listMatcher().atPosition(1))
//            .check(matches(hasDescendant(withText("The Last Days of American Crime"))))
//
//        onView(listMatcher().atPosition(2))
//            .check(matches(hasDescendant(withText(getResourceString(R.string.EXPLORE_next_movie_title)))))
//
//    }
//
//
//    @Test
//    fun checkErrorViewIsShowed() = testDispatcher.runBlockingTest {
//
//        mockWebServer.dispatcher = MockServerDispatcher().ErrorDispatcher()
//
//        launchFragmentInContainer<ExploreFragment>(themeResId = R.style.AppTheme)
//        onView(withId(R.id.ExplorErrorAnimation)).check(matches(isDisplayed()))
//        onView(withId(R.id.ExploreLoadingAnimation)).check(matches(not(isDisplayed())))
//        onView(withId(R.id.EXPLORE_recycler_view)).check(matches(not(isDisplayed())))
//    }
//
//    @Test
//    fun checkShowsNoData() =  testDispatcher.runBlockingTest {
//        mockWebServer.dispatcher = MockServerDispatcher().EmptyDispatcher()
//
//        launchFragmentInContainer<ExploreFragment>(themeResId = R.style.AppTheme)
//
//        onView(withId(R.id.EXPLORE_recycler_view)).check(matches(isDisplayed()))
//        onView(withId(R.id.EXPLORE_recycler_view)).check(RecyclerViewAssertions.hasItemsCount(0))
//    }
//
//
//    private fun listMatcher(): RecyclerViewMatcher {
//        return RecyclerViewMatcher(R.id.EXPLORE_recycler_view)
//    }
//}