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

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieDrawable
import com.prof18.filmatic.core.architecture.ViewState
import com.prof18.filmatic.core.architecture.activityViewModels
import com.prof18.filmatic.core.dagger.helper.CoreInjectHelper
import com.prof18.filmatic.core.utils.gone
import com.prof18.filmatic.core.utils.visible
import com.prof18.filmatic.features.home.R
import com.prof18.filmatic.features.home.databinding.FragmentExploreBinding
import com.prof18.filmatic.features.home.di.DaggerHomeComponent
import com.prof18.filmatic.features.home.di.HomeComponent
import com.prof18.filmatic.features.home.di.HomeComponentProvider
import com.prof18.filmatic.features.home.presentation.HomeViewModel
import com.prof18.filmatic.features.home.presentation.explore.model.ExploreItem
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Provider

class ExploreFragment : Fragment(R.layout.fragment_explore) {

    @Inject
    lateinit var viewModelProvider: Provider<HomeViewModel>

    private val viewModel by activityViewModels { viewModelProvider }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val component = (requireActivity().application as HomeComponentProvider).getHomeComponent()
        component.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentExploreBinding.bind(view)
        val lottieAnim = binding.ExploreAnimation

        val adapter = ExploreAdapter(requireContext())
        binding.EXPLORERecyclerView.adapter = adapter

        viewModel.exploreState.observe(viewLifecycleOwner, Observer {
            it?.let { state ->
                Timber.d(">>> STATE FROM EXPLORE VIEW MODEL -> $state")

                when (state) {
                    is ViewState.Success<List<ExploreItem>> -> {
                        showLoader(lottieAnim, false)
                        binding.EXPLORERecyclerView.visible()
                        val data = state.data
                        adapter.items = data
                        adapter.notifyDataSetChanged()
                    }
                    is ViewState.Error -> {
                        showError(lottieAnim, true)
                    }
                    ViewState.Loading -> {
                        showLoader(lottieAnim, true)
                    }
                }
            }
        })
        viewModel.fetchExploreItems()
    }

    private fun showLoader(animation: LottieAnimationView, showLoader: Boolean) {
        if (showLoader) {
            animation.visible()
            animation.setAnimation("loader.json")
            animation.playAnimation()
            animation.repeatCount = LottieDrawable.INFINITE
        } else {
            animation.pauseAnimation()
            animation.gone()
        }
    }

    private fun showError(animation: LottieAnimationView, showLoader: Boolean) {
        if (showLoader) {
            animation.visible()
            animation.setAnimation("nodata.json")
            animation.playAnimation()
            animation.repeatCount = LottieDrawable.INFINITE
        } else {
            animation.pauseAnimation()
            animation.gone()
        }
    }
}
