package com.prof18.filmatic.features.home.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView.Adapter.StateRestorationPolicy
import coil.ImageLoader
import com.airbnb.lottie.LottieDrawable
import com.prof18.filmatic.core.architecture.UIState
import com.prof18.filmatic.core.utils.gone
import com.prof18.filmatic.core.utils.visible
import com.prof18.filmatic.features.home.R
import com.prof18.filmatic.features.home.databinding.FragmentHomeBinding
import com.prof18.filmatic.features.home.presentation.adapter.HomeAdapter
import com.prof18.filmatic.features.home.presentation.state.HomeListItem
import com.prof18.filmatic.libraries.navigation.Destinations
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    // A customize Coil image loader, that can be replaced during testing
    @Inject
    lateinit var imageLoader: ImageLoader

    private val viewModel by activityViewModels<HomeViewModel>()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getHomeState()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentHomeBinding.bind(view)

        val errorAnimation = binding.homeLayoutError.layoutErrorAnimation
        errorAnimation.setAnimation(R.raw.error)
        errorAnimation.repeatCount = LottieDrawable.INFINITE

        val adapter = HomeAdapter(imageLoader) { movieId ->
            // Go to movie details
            val action = HomeFragmentDirections.actionHomeFragmentToMovieDetailFragment(
                movieId.value,
            )
            view.findNavController().navigate(action)
        }
        binding.homeRecyclerView.adapter = adapter

        binding.homeInfoButton.setOnClickListener {
            // Open About Screen
            startActivity(Destinations.openAboutActivity(requireContext()))
        }

        adapter.stateRestorationPolicy = StateRestorationPolicy.PREVENT_WHEN_EMPTY

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                // Observe the state used to setup the entire view
                viewModel.homeState.collect { state ->
                    Timber.v("Got state -> $state")
                    setupUI(state, adapter)
                }
            }
        }
    }

    private fun setupUI(state: UIState<List<HomeListItem>>, recyclerViewAdapter: HomeAdapter) {
        when (state) {
            is UIState.Success<List<HomeListItem>> -> {
                binding.homeProgressBar.gone()
                binding.homeRecyclerView.visible()
                val data = state.data
                recyclerViewAdapter.submitList(data)
            }
            is UIState.Error -> {
                val errorData = state.errorStringsId
                binding.homeProgressBar.gone()
                with(binding.homeLayoutError) {
                    root.visible()
                    layoutErrorAnimation.playAnimation()
                    layoutErrorMessage.text = getText(errorData.messageStringResID)

                    with(layoutErrorButton) {
                        text = getString(errorData.buttonTextResId)
                        setOnClickListener { viewModel.reloadData() }
                    }
                }
            }
            UIState.Loading -> {
                binding.homeProgressBar.visible()
                binding.homeRecyclerView.gone()
                with(binding.homeLayoutError) {
                    layoutErrorAnimation.pauseAnimation()
                    root.gone()
                }
            }
            UIState.NoData -> {
                binding.homeProgressBar.gone()
                with(binding.homeLayoutError) {
                    root.visible()
                    layoutErrorAnimation.playAnimation()
                    layoutErrorMessage.text = getString(R.string.data_not_found)

                    with(layoutErrorButton) {
                        text = getString(R.string.retry_button)
                        setOnClickListener { viewModel.reloadData() }
                    }
                }
            }
        }
    }
}
