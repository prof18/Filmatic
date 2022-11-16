package com.prof18.filmatic.core.error

import com.prof18.filmatic.core.R

/**
 * Generates the String Resource ID for the error message and the text button from an [ErrorEntity]
 */
fun ErrorEntity.getStringResId(): ErrorData {
    return when (this) {
        NetworkError.ConnectionNotAvailable -> {
            ErrorData(
                messageStringResID = R.string.missing_network_message,
                buttonTextResId = R.string.retry_button,
            )
        }
        NetworkError.Network, NetworkError.NotFound -> {
            ErrorData(
                messageStringResID = R.string.unknown_network_error,
                buttonTextResId = R.string.retry_button,
            )
        }
        NetworkError.NotAuthorized -> {
            ErrorData(
                messageStringResID = R.string.error_message_not_allowed,
                buttonTextResId = R.string.retry_button,
            )
        }

        NetworkError.ServiceNotWorking, NetworkError.ServiceUnavailable -> {
            ErrorData(
                messageStringResID = R.string.server_unreachable,
                buttonTextResId = R.string.retry_button,
            )
        }
        NetworkError.Unknown -> {
            ErrorData(
                messageStringResID = R.string.unknown_network_error,
                buttonTextResId = R.string.retry_button,
            )
        }
        ErrorEntity.UnknownError -> {
            ErrorData(
                messageStringResID = R.string.unknown_error,
                buttonTextResId = R.string.retry_button,
            )
        }
    }
}
