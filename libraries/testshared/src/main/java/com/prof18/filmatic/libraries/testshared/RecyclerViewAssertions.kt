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

package com.prof18.filmatic.libraries.testshared

import android.view.View
import android.view.View.FIND_VIEWS_WITH_TEXT
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.*
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.ViewAssertion
import org.hamcrest.MatcherAssert
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThat
import java.util.regex.Matcher


object RecyclerViewAssertions {
    fun hasItemsCount(count: Int): ViewAssertion {
        return ViewAssertion { view, e ->
            if (view !is RecyclerView) {
                throw e
            }
            val rv: RecyclerView = view
            assertEquals(rv.getAdapter()?.getItemCount(), count)
        }
    }

    fun hasHolderItemAtPosition(
        index: Int,
        viewHolderMatcher: Matcher
    ): ViewAssertion {
        return ViewAssertion { view, e ->
            if (view !is RecyclerView) {
                throw e
            }
            val rv: RecyclerView = view
            assertEquals(rv.findViewHolderForAdapterPosition(index), viewHolderMatcher)
        }
    }

    fun hasViewWithTextAtPosition(index: Int, text: CharSequence): ViewAssertion {
        return ViewAssertion { view, e ->
            if (view !is RecyclerView) {
                throw e
            }
            val rv: RecyclerView = view
            val outviews: ArrayList<View> = ArrayList()
            rv.findViewHolderForAdapterPosition(index)?.itemView?.findViewsWithText(
                outviews, text,
                FIND_VIEWS_WITH_TEXT
            )
            MatcherAssert.assertThat("There's no view at index $index of recyclerview that has text : $text", outviews.isNotEmpty())
        }
    }

    fun doesNotHaveViewWithText(text: String?): ViewAssertion {
        return ViewAssertion { view, e ->
            if (view !is RecyclerView) {
                throw e
            }
            val rv: RecyclerView = view
            val outviews: ArrayList<View> = ArrayList()
            for (index in 0 until (rv.adapter?.itemCount ?: 0)) {
                rv.findViewHolderForAdapterPosition(index)?.itemView?.findViewsWithText(
                    outviews, text,
                    FIND_VIEWS_WITH_TEXT
                )
                if (outviews.size > 0) break
            }
            assert(outviews.isEmpty())
        }
    }

    
}
