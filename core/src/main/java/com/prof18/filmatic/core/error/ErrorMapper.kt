package com.prof18.filmatic.core.error

import retrofit2.HttpException
import java.io.IOException
import java.net.HttpURLConnection
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ErrorMapper @Inject constructor() {
    fun mapError(exception: Exception): ErrorEntity {
        return when (exception) {
            is IOException -> NetworkError.Network
            is HttpException -> {
                when (exception.code()) {
                    // 404
                    HttpURLConnection.HTTP_NOT_FOUND -> NetworkError.NotFound
                    // 401
                    HttpURLConnection.HTTP_UNAUTHORIZED -> NetworkError.NotAuthorized
                    // 503
                    HttpURLConnection.HTTP_UNAVAILABLE -> NetworkError.ServiceUnavailable
                    // 500
                    HttpURLConnection.HTTP_INTERNAL_ERROR -> NetworkError.ServiceNotWorking
                    else -> NetworkError.Unknown
                }
            }
            else -> ErrorEntity.UnknownError
        }
    }
}
