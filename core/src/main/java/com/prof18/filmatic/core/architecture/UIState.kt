package com.prof18.filmatic.core.architecture

import com.prof18.filmatic.core.error.ErrorData

/**
 * A wrapper to contain different UI states
 */
sealed class UIState<out T> {
    data class Success<out T>(val data: T) : UIState<T>()
    data class Error(val errorStringsId: ErrorData) : UIState<Nothing>()
    object Loading : UIState<Nothing>()
    object NoData : UIState<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error"
            Loading -> "Loading"
            NoData -> "No Data"
        }
    }
}
