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

package com.prof18.filmatic.features.home.presentation.explore

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import coil.ImageLoader
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieDrawable
import com.prof18.filmatic.core.architecture.ViewState
import com.prof18.filmatic.core.utils.gone
import com.prof18.filmatic.core.utils.visible
import com.prof18.filmatic.features.home.R
import com.prof18.filmatic.features.home.databinding.FragmentExploreBinding
import com.prof18.filmatic.features.home.presentation.HomeViewModel
import com.prof18.filmatic.features.home.presentation.explore.model.ExploreItem
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class ExploreFragment : Fragment(R.layout.fragment_explore) {

    @Inject lateinit var imageLoader: ImageLoader

    private val viewModel by viewModels<HomeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.fetchExploreItems()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentExploreBinding.bind(view)
        val loadingAnimation = binding.ExploreLoadingAnimation
        loadingAnimation.setAnimation("loader.json")
        loadingAnimation.repeatCount = LottieDrawable.INFINITE

        val errorAnimation = binding.ExplorErrorAnimation
        errorAnimation.setAnimation("nodata.json")
        errorAnimation.repeatCount = LottieDrawable.INFINITE

        val adapter = ExploreAdapter(requireContext(), imageLoader)
        binding.EXPLORERecyclerView.adapter = adapter

        viewModel.exploreState.observe(
            viewLifecycleOwner,
            Observer {
                it?.let { state ->
                    Timber.d(">>> STATE FROM EXPLORE VIEW MODEL -> $state")

                    when (state) {
                        is ViewState.Success<List<ExploreItem>> -> {
                            showAnimation(loadingAnimation, false)
                            showAnimation(errorAnimation, false)
                            binding.EXPLORERecyclerView.visible()
                            val data = state.data
                            adapter.items = data
                            adapter.notifyDataSetChanged()
                        }
                        is ViewState.Error -> {
                            showAnimation(loadingAnimation, false)
                            showAnimation(errorAnimation, true)
                        }
                        ViewState.Loading -> {
                            showAnimation(loadingAnimation, true)
                            showAnimation(errorAnimation, false)
                        }
                    }
                }
            }
        )
    }

    private fun showAnimation(animation: LottieAnimationView, showLoader: Boolean) {
        if (showLoader) {
            animation.visible()
            animation.playAnimation()
        } else {
            animation.pauseAnimation()
            animation.gone()
        }
    }
}
