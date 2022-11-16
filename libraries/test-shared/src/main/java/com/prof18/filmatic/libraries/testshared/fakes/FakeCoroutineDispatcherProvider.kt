package com.prof18.filmatic.libraries.testshared.fakes

import com.prof18.filmatic.core.architecture.CoroutineDispatcherProvider
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestDispatcher

fun provideFakeCoroutinesDispatcherProvider(
    dispatcher: TestDispatcher?
): CoroutineDispatcherProvider {
    val sharedTestCoroutineDispatcher = StandardTestDispatcher()
    return CoroutineDispatcherProvider(
        dispatcher ?: sharedTestCoroutineDispatcher,
        dispatcher ?: sharedTestCoroutineDispatcher,
        dispatcher ?: sharedTestCoroutineDispatcher
    )
}
