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
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.prof18.filmatic.features.home.R
import org.hamcrest.Matchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ExploreFragmentTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    // TODO stub

    @Test
    fun checkLoadingIsVisibleAndListNot() {

//        mockServer.dispatcher = MockServerDispatcher.ResponseDispatcher()
        launchFragmentInContainer<ExploreFragment>()

        onView(withId(R.id.ExploreAnimation)).check(matches(isDisplayed()))
        onView(withId(R.id.EXPLORE_recycler_view)).check(matches(not(isDisplayed())))
    }

    @Test
    fun checkListShowed() {



        launchFragmentInContainer<ExploreFragment>(themeResId = R.style.AppTheme)

//        activityRule.launchActivity(Actions.openHomeIntent(InstrumentationRegistry.getInstrumentation().targetContext))



        onView(withId(R.id.EXPLORE_recycler_view)).check(matches(isDisplayed()))



    }




}