package com.prof18.filmatic.features.home.presentation

import android.content.res.Resources
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.jakewharton.espresso.OkHttp3IdlingResource
import com.prof18.filmatic.features.home.R
import com.prof18.filmatic.features.home.launchFragmentInHiltContainer
import com.prof18.filmatic.libraries.testshared.enqueueResponse
import com.prof18.filmatic.libraries.testshared.fakes.POPULAR_MOVIES_EMPTY_JSON_RESPONSE
import com.prof18.filmatic.libraries.testshared.fakes.POPULAR_MOVIES_JSON_RESPONSE
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class HomeFragmentTest {

    private var resources: Resources =
        InstrumentationRegistry.getInstrumentation().targetContext.resources

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var okHttpClient: OkHttpClient

    @Inject
    lateinit var mockWebServer: MockWebServer

    @Before
    fun setup() {
        hiltRule.inject()
        IdlingRegistry.getInstance().register(
            com.prof18.filmatic.libraries.testshared.OkHttp3IdlingResource.create("okhttp", okHttpClient),
        )
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }

    @Test
    fun should_display_error_message_when_state_is_error() {
        mockWebServer.enqueueResponse(POPULAR_MOVIES_JSON_RESPONSE, 401)

        launchFragmentInHiltContainer<HomeFragment>(themeResId = R.style.FilmaticTheme)

        val errorText = resources.getString(R.string.error_message_not_allowed)
        onView(withId(R.id.layout_error_message)).check(matches(withText(errorText)))

        val buttonText = resources.getString(R.string.retry_button)
        onView(withId(R.id.layout_error_button)).check(matches(withText(buttonText)))
    }

    @Test
    fun should_display_data_when_success_is_returned() {
        mockWebServer.enqueueResponse(POPULAR_MOVIES_JSON_RESPONSE, 200)

        launchFragmentInHiltContainer<HomeFragment>(themeResId = R.style.FilmaticTheme)

        val headerText = resources.getString(R.string.popular_title)
        onView(withText(headerText)).check(matches(isDisplayed()))

        scrollRecyclerViewToPosition(1)
        onView(withId(R.id.home_trending_list)).check(matches(isDisplayed()))

        val headerTextNextMovie = resources.getString(R.string.next_movie_title)
        scrollRecyclerViewToPosition(2)
        onView(withText(headerTextNextMovie)).check(matches(isDisplayed()))

        scrollRecyclerViewToPosition(3)
        onView(withId(R.id.item_movie_big_card)).check(matches(isDisplayed()))
    }

    @Test
    fun should_display_no_data_if_movie_list_empty() {
        mockWebServer.enqueueResponse(POPULAR_MOVIES_EMPTY_JSON_RESPONSE, 200)

        launchFragmentInHiltContainer<HomeFragment>(themeResId = R.style.FilmaticTheme)

        val errorText = resources.getString(R.string.data_not_found)
        onView(withId(R.id.layout_error_message)).check(matches(withText(errorText)))

        val buttonText = resources.getString(R.string.retry_button)
        onView(withId(R.id.layout_error_button)).check(matches(withText(buttonText)))
    }

    private fun scrollRecyclerViewToPosition(position: Int) {
        onView(withId(R.id.home_recycler_view)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(position),
        )
    }
}
