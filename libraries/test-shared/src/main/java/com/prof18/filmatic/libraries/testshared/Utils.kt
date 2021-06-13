package com.prof18.filmatic.libraries.testshared

import com.prof18.filmatic.core.architecture.CoroutineDispatcherProvider
import kotlinx.coroutines.test.TestCoroutineDispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer

fun MockWebServer.enqueueResponse(response: String, code: Int) {
    enqueue(
        MockResponse()
            .setResponseCode(code)
            .setBody(response)
    )
}

val testCoroutineDispatcherProvider = CoroutineDispatcherProvider(
    main = TestCoroutineDispatcher(),
    io = TestCoroutineDispatcher(),
    computation = TestCoroutineDispatcher()
)
