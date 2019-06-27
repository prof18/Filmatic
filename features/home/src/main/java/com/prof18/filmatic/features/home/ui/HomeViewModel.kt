package com.prof18.filmatic.features.home.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prof18.filmatic.features.home.data.api.HomeService
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

class HomeViewModel: ViewModel() {

    private lateinit var homeService: HomeService

    fun setService(service: HomeService) {
        this.homeService = service
    }

    fun getPopularMovies() {

        viewModelScope.launch {


            try {
                val response = homeService.getPopularMovies()

                val list = response.popularMovies

                Log.d("", list.toString())
            } catch (e: Exception) {
                Log.d("", " Unable to get things")
            }
        }

    }

}