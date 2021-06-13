package com.prof18.filmatic.core.error

sealed class ErrorEntity {
    object UnknownError : ErrorEntity()
}

sealed class NetworkError : ErrorEntity() {
    object ConnectionNotAvailable : NetworkError()
    object Network : NetworkError()
    object NotFound : NetworkError()
    object NotAuthorized : NetworkError()
    object ServiceUnavailable : NetworkError()
    object ServiceNotWorking : NetworkError()
    object Unknown : NetworkError()
}
