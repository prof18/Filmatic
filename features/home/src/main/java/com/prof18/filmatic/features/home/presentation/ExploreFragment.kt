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
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.airbnb.lottie.LottieCompositionFactory
import com.airbnb.lottie.LottieDrawable
import com.bumptech.glide.Glide
import com.prof18.filmatic.core.architecture.activityViewModels
import com.prof18.filmatic.core.dagger.helper.CoreInjectHelper
import com.prof18.filmatic.core.utils.gone
import com.prof18.filmatic.core.utils.visibile
import com.prof18.filmatic.features.home.R
import com.prof18.filmatic.features.home.di.DaggerHomeComponent
import kotlinx.android.synthetic.main.fragment_explore.view.*
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Provider

class ExploreFragment : Fragment(R.layout.fragment_explore) {

     @Inject
    lateinit var viewModelProvider: Provider<HomeViewModel>

    private val viewModel by activityViewModels { viewModelProvider }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        DaggerHomeComponent
            .builder()
            .coreComponent(CoreInjectHelper.provideCoreComponent(requireActivity().applicationContext))
            .build()
            .inject(this)

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


//        showLoader(view, true)

        val nextMovieImage = view.findViewById<ImageView>(R.id.movie_image)
        val nextMovieTitle = view.findViewById<TextView>(R.id.movie_title)

        viewModel.exploreState.observe(viewLifecycleOwner, Observer {
            it?.let { state ->
                Timber.d(">>> STATE FROM EXPLORE VIEW MODEL -> $state")
            }
        })

            viewModel.fetchPopularMovies()
    }

//    private fun showLoader(view: View, showLoader: Boolean) {
//        if (showLoader) {
//            view.EXPLORE_animation.visibile()
//            view.EXPLORE_animation.setAnimation("loader.json")
//            view.EXPLORE_animation.playAnimation()
//            view.EXPLORE_animation.repeatCount = LottieDrawable.INFINITE
//        } else {
//            view.EXPLORE_animation.pauseAnimation()
//            view.EXPLORE_animation.gone()
//        }
//    }
//
//    private fun showError(view: View, showLoader: Boolean) {
//        if (showLoader) {
//            view.EXPLORE_animation.visibile()
//            view.EXPLORE_animation.setAnimation("nodata.json")
//            view.EXPLORE_animation.playAnimation()
//            view.EXPLORE_animation.repeatCount = LottieDrawable.INFINITE
//        } else {
//            view.EXPLORE_animation.pauseAnimation()
//            view.EXPLORE_animation.gone()
//        }
//    }
}
