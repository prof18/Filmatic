package com.prof18.filmatic.features.home.data.discovery

import com.prof18.filmatic.core.UserPreferenceManager
import com.prof18.filmatic.features.home.data.api.HomeService
import com.prof18.filmatic.features.home.data.discovery.model.GenreResponse
import javax.inject.Inject

class DiscoveryRemoteDataSource @Inject constructor(
    private val service: HomeService,
    private val userPreferenceManager: UserPreferenceManager
) {

    suspend fun getGenres(): GenreResponse {
        try {
            return service.getGenres(userPreferenceManager.getUserPreferredLocale())
        } catch (e: Exception) {
            throw e
        }
    }
}