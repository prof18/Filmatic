package com.prof18.filmatic.features.home.data.discovery

import com.prof18.filmatic.features.home.data.discovery.model.Genre
import java.lang.Exception
import javax.inject.Inject

class DiscoveryRepository @Inject constructor(
    private val discoveryRemoteDataSource: DiscoveryRemoteDataSource
) {

    suspend fun getGenres(): List<Genre> {
        try {
            val response = discoveryRemoteDataSource.getGenres()
            return response.genres
        } catch (e: Exception) {
            throw e
        }
    }

}