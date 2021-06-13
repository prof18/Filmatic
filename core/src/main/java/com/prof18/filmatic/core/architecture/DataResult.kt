package com.prof18.filmatic.core.architecture

import com.prof18.filmatic.core.error.ErrorEntity

/**
 * A wrapper to contain successful and failure states
 */
sealed class DataResult<out T> {
    data class Success<T>(val data: T) : DataResult<T>()
    data class Error(val error: ErrorEntity) : DataResult<Nothing>()
}
