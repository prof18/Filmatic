package com.prof18.filmatic.core.architecture

import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

data class CoroutineDispatcherProvider @Inject constructor(
    val main: CoroutineDispatcher,
    val computation: CoroutineDispatcher,
    val io: CoroutineDispatcher
)
