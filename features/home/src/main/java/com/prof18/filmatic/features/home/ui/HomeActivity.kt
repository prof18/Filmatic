package com.prof18.filmatic.features.home.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.prof18.filmatic.core.dagger.helper.CoreInjectHelper
import com.prof18.filmatic.features.home.R
import com.prof18.filmatic.features.home.dagger.DaggerHomeComponent
import com.prof18.filmatic.features.home.data.api.HomeService
import javax.inject.Inject

class HomeActivity : AppCompatActivity() {

    @Inject
    lateinit var service: HomeService


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        DaggerHomeComponent
            .builder()
            .coreComponent(CoreInjectHelper.provideCoreComponent(applicationContext))
            .build()
            .inject(this)


        val viewModel = ViewModelProviders.of(this@HomeActivity).get(HomeViewModel::class.java)
        viewModel.setService(service)
        viewModel.getPopularMovies()


    }
}
