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
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.airbnb.lottie.LottieDrawable
import com.prof18.filmatic.core.architecture.ViewState
import com.prof18.filmatic.core.architecture.activityViewModels
import com.prof18.filmatic.core.dagger.helper.CoreInjectHelper
import com.prof18.filmatic.core.utils.gone
import com.prof18.filmatic.core.utils.visible
import com.prof18.filmatic.features.home.R
import com.prof18.filmatic.features.home.databinding.FragmentExploreBinding
import com.prof18.filmatic.features.home.di.DaggerHomeComponent
import com.prof18.filmatic.features.home.presentation.HomeViewModel
import com.prof18.filmatic.features.home.presentation.explore.model.ExploreItem
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Provider

class ExploreFragment : Fragment(R.layout.fragment_explore) {

    @Inject
    lateinit var viewModelProvider: Provider<HomeViewModel>

    private val viewModel by activityViewModels { viewModelProvider }

    private var _binding: FragmentExploreBinding? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

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

        _binding = FragmentExploreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


//        showLoader(view, true)


        val adapter = ExploreAdapter(requireContext())
        binding.EXPLORERecyclerView.adapter = adapter

        viewModel.exploreState.observe(viewLifecycleOwner, Observer {
            it?.let { state ->
                Timber.d(">>> STATE FROM EXPLORE VIEW MODEL -> $state")

                when (state) {
                    is ViewState.Success<List<ExploreItem>> -> {
                        showLoader(false)
                        binding.EXPLORERecyclerView.visible()
                        val data = state.data
                        adapter.items = data
                        adapter.notifyDataSetChanged()
                    }
                    is ViewState.Error -> {
                        showError(true)
                    }
                    ViewState.Loading -> {
                        showLoader(true)
                    }
                }

            }
        })

        viewModel.fetchExploreItems()

    }

    private fun showLoader(showLoader: Boolean) {
        if (showLoader) {
            binding.ExploreAnimation.visible()
            binding.ExploreAnimation.setAnimation("loader.json")
            binding.ExploreAnimation.playAnimation()
            binding.ExploreAnimation.repeatCount = LottieDrawable.INFINITE
        } else {
            binding.ExploreAnimation.pauseAnimation()
            binding.ExploreAnimation.gone()
        }
    }

    private fun showError(showLoader: Boolean) {
        if (showLoader) {
            binding.ExploreAnimation.visible()
            binding.ExploreAnimation.setAnimation("nodata.json")
            binding.ExploreAnimation.playAnimation()
            binding.ExploreAnimation.repeatCount = LottieDrawable.INFINITE
        } else {
            binding.ExploreAnimation.pauseAnimation()
            binding.ExploreAnimation.gone()
        }
    }
}
