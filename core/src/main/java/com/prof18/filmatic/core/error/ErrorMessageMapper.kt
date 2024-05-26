package com.prof18.filmatic.core.error

import com.m2f.archer.failure.DataEmpty
import com.m2f.archer.failure.DataNotFound
import com.m2f.archer.failure.Failure
import com.m2f.archer.failure.Invalid
import com.m2f.archer.failure.NetworkFailure
import com.m2f.archer.failure.Unhandled
import com.m2f.archer.failure.Unknown
import com.prof18.filmatic.core.R

/**
 * Generates the String Resource ID for the error message and the text button from an [ErrorEntity]
 */
fun Failure.getStringResId(): ErrorData {
    return when (this) {
        NetworkFailure.NoConnection -> {
            ErrorData(
                messageStringResID = R.string.missing_network_message,
                buttonTextResId = R.string.retry_button,
            )
        }

        DataNotFound -> {
            ErrorData(
                messageStringResID = R.string.unknown_network_error,
                buttonTextResId = R.string.retry_button,
            )
        }

        NetworkFailure.Unauthorised -> {
            ErrorData(
                messageStringResID = R.string.error_message_not_allowed,
                buttonTextResId = R.string.retry_button,
            )
        }

        NetworkFailure.ServerFailure -> {
            ErrorData(
                messageStringResID = R.string.server_unreachable,
                buttonTextResId = R.string.retry_button,
            )
        }

        NetworkFailure.UnhandledNetworkFailure,
        DataEmpty,
        Invalid,
        is NetworkFailure.NetworkError,
        NetworkFailure.Redirect,
        is Unhandled,
        Unknown -> {
            ErrorData(
                messageStringResID = R.string.unknown_network_error,
                buttonTextResId = R.string.retry_button,
            )
        }
    }
}
