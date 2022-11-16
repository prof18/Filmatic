package com.prof18.filmatic.libraries.testshared

import com.prof18.filmatic.core.architecture.CoroutineDispatcherProvider
import kotlinx.coroutines.test.StandardTestDispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer

fun MockWebServer.enqueueResponse(response: String, code: Int) {
    enqueue(
        MockResponse()
            .setResponseCode(code)
            .setBody(response)
    )
}

private val standardDispatcher = StandardTestDispatcher()

val testCoroutineDispatcherProvider = CoroutineDispatcherProvider(
    main = standardDispatcher,
    io = standardDispatcher,
    computation = standardDispatcher,
)
