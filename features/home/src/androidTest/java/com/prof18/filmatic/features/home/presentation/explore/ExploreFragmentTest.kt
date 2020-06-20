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
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.nhaarman.mockitokotlin2.doThrow
import com.nhaarman.mockitokotlin2.whenever
import com.prof18.filmatic.features.home.MockResponses
import com.prof18.filmatic.features.home.R
import com.prof18.filmatic.features.home.di.HomeModule
import com.prof18.filmatic.features.home.launchFragmentInHiltContainer
import com.prof18.filmatic.features.home.remote.api.HomeService
import com.prof18.filmatic.features.home.remote.model.PopularMoviesResult
import com.prof18.filmatic.libraries.testshared.CoroutinesTestRule
import com.prof18.filmatic.libraries.testshared.RecyclerViewAssertions
import com.prof18.filmatic.libraries.testshared.RecyclerViewMatcher
import com.prof18.filmatic.libraries.testshared.Utils.getResourceString
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.hamcrest.Matchers.not
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.io.IOException
import javax.inject.Inject

@UninstallModules(HomeModule::class)
@HiltAndroidTest
@ExperimentalCoroutinesApi
class ExploreFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var coroutinesTestRule = CoroutinesTestRule()

    @Inject
     lateinit var service: HomeService
//    @Inject
//     lateinit var testDispatcher: TestCoroutineDispatcher

    @Before
    fun init() {
        hiltRule.inject()
    }


    @After
    fun tearDown() {
//        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun checkErrorViewIsShowed() {
        stubServiceWithError()
        launchFragmentInHiltContainer<ExploreFragment>(themeResId = R.style.AppTheme)
        onView(withId(R.id.ExplorErrorAnimation)).check(matches(isDisplayed()))
        onView(withId(R.id.ExploreLoadingAnimation)).check(matches(not(isDisplayed())))
        onView(withId(R.id.EXPLORE_recycler_view)).check(matches(not(isDisplayed())))
    }

    @Test
    fun checkShowsNoData() {
        val popularResult = MockResponses.getEmptyPopularMovieResult()
        stubServiceWithData(popularResult)

        launchFragmentInHiltContainer<ExploreFragment>(themeResId = R.style.AppTheme)

        onView(withId(R.id.EXPLORE_recycler_view)).check(matches(isDisplayed()))
        onView(withId(R.id.EXPLORE_recycler_view)).check(RecyclerViewAssertions.hasItemsCount(0))
    }

    @Test
    fun checkShowsData() {
        val popularResult = MockResponses.getPopularMovieResult()
        stubServiceWithData(popularResult)

        launchFragmentInHiltContainer<ExploreFragment>(themeResId = R.style.AppTheme)

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