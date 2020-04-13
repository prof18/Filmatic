/*
 * Copyright 2020 Marco Gomiero
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.prof18.filmatic.features.home.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.prof18.filmatic.core.architecture.viewModels
import com.prof18.filmatic.core.dagger.helper.CoreInjectHelper
import com.prof18.filmatic.features.home.R
import com.prof18.filmatic.features.home.di.DaggerHomeComponent
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Provider

class HomeActivity: AppCompatActivity() {

    @Inject
    lateinit var viewModelProvider: Provider<HomeViewModel>

    private val viewModel by viewModels { viewModelProvider }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DaggerHomeComponent
            .builder()
            .coreComponent(CoreInjectHelper.provideCoreComponent(applicationContext))
            .build()
            .inject(this)

        setContentView(R.layout.activity_home)

        viewModel.fetchPopularMovies()

        Timber.d("")


    }

}