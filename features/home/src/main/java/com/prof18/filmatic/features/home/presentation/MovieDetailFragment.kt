package com.prof18.filmatic.features.home.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import coil.ImageLoader
import coil.load
import com.airbnb.lottie.LottieCompositionFactory
import com.airbnb.lottie.LottieDrawable
import com.prof18.filmatic.core.architecture.UIState
import com.prof18.filmatic.core.utils.gone
import com.prof18.filmatic.core.utils.visible
import com.prof18.filmatic.features.home.R
import com.prof18.filmatic.features.home.databinding.FragmentMovieDetailBinding
import com.prof18.filmatic.features.home.presentation.state.MovieDetailState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class MovieDetailFragment : Fragment(R.layout.fragment_movie_detail) {

    @Inject
    lateinit var imageLoader: ImageLoader

    private val viewModel by activityViewModels<HomeViewModel>()

    private val args: MovieDetailFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getMovie(args.movieId)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentMovieDetailBinding.bind(view)

        binding.movieDetailBackButton.setOnClickListener {
            view.findNavController().popBackStack()
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.movieDetailState.collect { state ->
                    Timber.v("Got state: $state")
                    setupDetailUI(binding, state)
                }
            }
        }
    }

    private fun setupDetailUI(
        binding: FragmentMovieDetailBinding,
        state: UIState<MovieDetailState>
    ) {
        val errorAnimation = binding.movieDetailLayoutError.layoutErrorAnimation
        errorAnimation.setAnimation(R.raw.error)
        errorAnimation.repeatCount = LottieDrawable.INFINITE

        when (state) {
            is UIState.Error -> {
                // Not used!
            }
            UIState.Loading -> {
                binding.movieDetailProgressBar.visible()
                binding.movieDetailOverview.gone()
            }
            UIState.NoData -> {
                binding.movieDetailProgressBar.gone()
                binding.movieDetailTitle.text = getString(R.string.title_not_found)
                with(binding.movieDetailLayoutError) {
                    root.visible()
                    layoutErrorAnimation.playAnimation()
                    layoutErrorMessage.text = getString(R.string.data_not_found)
                    // Not used in this case
                    layoutErrorButton.gone()
                }
            }
            is UIState.Success -> {
                binding.movieDetailProgressBar.gone()
                binding.movieDetailLayoutError.root.gone()

                val movieDetail = state.data

                val lottieDrawable = LottieDrawable()
                LottieCompositionFactory.fromRawRes(
                    requireContext(),
                    R.raw.image_loader
                ).addListener { lottieComposition ->
                    lottieDrawable.composition = lottieComposition
                    lottieDrawable.repeatCount = LottieDrawable.INFINITE
                    lottieDrawable.playAnimation()
                }

                with(binding.movieImage) {
                    transitionName = args.movieId.toString()
                    load(movieDetail.imageUrl, imageLoader) {
                        placeholder(lottieDrawable)
                    }
                }

                binding.movieDetailTitle.text = movieDetail.title

                with(binding.movieDetailOverview) {
                    text = movieDetail.summary
                    visible()
                }
            }
        }
    }
}
