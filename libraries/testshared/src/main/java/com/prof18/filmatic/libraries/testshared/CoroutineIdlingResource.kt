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

import androidx.test.espresso.IdlingResource
import androidx.test.espresso.idling.CountingIdlingResource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

object CorutineIdlingResource {

    private val countingIdlingResource = CountingIdlingResource("COROUTINE")

    fun increment() {
        countingIdlingResource.increment()
    }

    fun decrement() {
        countingIdlingResource.decrement()
    }

    fun getIdlingResource(): IdlingResource = countingIdlingResource

}

fun CoroutineScope.launchIdling(
    context: CoroutineContext = EmptyCoroutineContext,
    start: CoroutineStart = CoroutineStart.DEFAULT,
    block: suspend CoroutineScope.() -> Unit
): Job {
    CorutineIdlingResource.increment()
    val job = this.launch(context, start, block)
    job.invokeOnCompletion { CorutineIdlingResource.decrement() }
    return job
}
