package com.prof18.filmatic.features.home.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.prof18.filmatic.core.UserPreferenceManager
import com.prof18.filmatic.core.dagger.helper.CoreInjectHelper
import com.prof18.filmatic.features.home.R
import com.prof18.filmatic.features.home.dagger.DaggerHomeComponent
import com.prof18.filmatic.features.home.dagger.HomeModule
import javax.inject.Inject

class HomeActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: HomeViewModel

    @Inject
    lateinit var preferenceManager: UserPreferenceManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        DaggerHomeComponent
            .builder()
            .coreComponent(CoreInjectHelper.provideCoreComponent(applicationContext))
            .homeModule(HomeModule(this@HomeActivity))
            .build()
            .inject(this)

        viewModel.getPopularMovies()
    }
}
