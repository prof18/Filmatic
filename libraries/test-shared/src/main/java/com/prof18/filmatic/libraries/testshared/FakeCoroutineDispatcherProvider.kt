package com.prof18.filmatic.libraries.testshared

import com.prof18.filmatic.core.architecture.CoroutineDispatcherProvider
import kotlinx.coroutines.test.TestCoroutineDispatcher

fun provideFakeCoroutinesDispatcherProvider(
    dispatcher: TestCoroutineDispatcher?
): CoroutineDispatcherProvider {
    val sharedTestCoroutineDispatcher = TestCoroutineDispatcher()
    return CoroutineDispatcherProvider(
        dispatcher ?: sharedTestCoroutineDispatcher,
        dispatcher ?: sharedTestCoroutineDispatcher,
        dispatcher ?: sharedTestCoroutineDispatcher
    )
}
