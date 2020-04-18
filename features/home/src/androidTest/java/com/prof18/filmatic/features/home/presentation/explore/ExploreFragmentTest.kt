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

package com.prof18.filmatic.features.home.presentation.explore

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.nhaarman.mockitokotlin2.doThrow
import com.nhaarman.mockitokotlin2.whenever
import com.prof18.filmatic.features.home.HomeTestApp
import com.prof18.filmatic.features.home.MockResponses
import com.prof18.filmatic.features.home.R
import com.prof18.filmatic.features.home.di.TestHomeComponent
import com.prof18.filmatic.features.home.remote.api.HomeService
import com.prof18.filmatic.features.home.remote.model.PopularMoviesResult
import com.prof18.filmatic.libraries.testshared.CoroutinesTestRule
import com.prof18.filmatic.libraries.testshared.RecyclerViewAssertions
import com.prof18.filmatic.libraries.testshared.RecyclerViewMatcher
import com.prof18.filmatic.libraries.testshared.Utils.getResourceString
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.Matchers.not
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class ExploreFragmentTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var coroutinesTestRule = CoroutinesTestRule()

    private lateinit var service: HomeService
    private lateinit var testDispatcher: TestCoroutineDispatcher

    @Before
    fun setup() {
        val app =
            InstrumentationRegistry.getInstrumentation().targetContext.applicationContext as HomeTestApp
        val homeComponent = (app.getHomeComponent() as TestHomeComponent)
        service = homeComponent.provideHomeService()
        testDispatcher =
            homeComponent.provideCoroutineDisparcherProvider().computation as TestCoroutineDispatcher

    }

    @After
    fun tearDown() {
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun checkErrorViewIsShowed() {
        stubServiceWithError()
        launchFragmentInContainer<ExploreFragment>(themeResId = R.style.AppTheme)
        onView(withId(R.id.ExplorErrorAnimation)).check(matches(isDisplayed()))
        onView(withId(R.id.ExploreLoadingAnimation)).check(matches(not(isDisplayed())))
        onView(withId(R.id.EXPLORE_recycler_view)).check(matches(not(isDisplayed())))
    }

    @Test
    fun checkShowsNoData() {
        val popularResult = MockResponses.getEmptyPopularMovieResult()
        stubServiceWithData(popularResult)

        launchFragmentInContainer<ExploreFragment>(themeResId = R.style.AppTheme)

        onView(withId(R.id.EXPLORE_recycler_view)).check(matches(isDisplayed()))
        onView(withId(R.id.EXPLORE_recycler_view)).check(RecyclerViewAssertions.hasItemsCount(0))
    }

    @Test
    fun checkShowsData() {
        val popularResult = MockResponses.getPopularMovieResult()
        stubServiceWithData(popularResult)

        launchFragmentInContainer<ExploreFragment>(themeResId = R.style.AppTheme)

        onView(listMatcher().atPosition(0))
            .check(matches(hasDescendant(withText(getResourceString(R.string.EXPLORE_popular_title)))))

        onView(listMatcher().atPosition(1))
            .check(matches(hasDescendant(withText("Ad Astra"))))

        onView(listMatcher().atPosition(2))
            .check(matches(hasDescendant(withText(getResourceString(R.string.EXPLORE_next_movie_title)))))

    }

    private fun listMatcher(): RecyclerViewMatcher {
        return RecyclerViewMatcher(R.id.EXPLORE_recycler_view)
    }

    private fun stubServiceWithError() = runBlocking {
        doThrow(IOException()).whenever(service).getPopularMovies()
    }

    private fun stubServiceWithData(popularMoviesResult: PopularMoviesResult) = runBlocking {
        whenever(service.getPopularMovies())
            .thenReturn(popularMoviesResult)
    }
}