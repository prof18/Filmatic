package com.prof18.filmatic.features.home.ui.explore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.airbnb.lottie.LottieCompositionFactory
import com.airbnb.lottie.LottieDrawable
import com.bumptech.glide.Glide
import com.prof18.filmatic.core.dagger.helper.CoreInjectHelper
import com.prof18.filmatic.core.utils.getFullImageUrl
import com.prof18.filmatic.core.utils.gone
import com.prof18.filmatic.core.utils.visibile
import com.prof18.filmatic.features.home.R
import com.prof18.filmatic.features.home.dagger.DaggerHomeComponent
import com.prof18.filmatic.features.home.ui.HomeActivity
import com.prof18.filmatic.features.home.ui.explore.adapter.PopularAdapter
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.fragment_explore.view.*
import javax.inject.Inject

class ExploreFragment : Fragment() {

    @Inject
    lateinit var factory: ExploreViewModelFactory

    private lateinit var viewModel: ExploreViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_explore, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.EXPLORE_toolbar.setTitleTextAppearance(requireContext(), R.style.TextAppearance_Filmatic_Headline4)

        DaggerHomeComponent
            .builder()
            .coreComponent(CoreInjectHelper.provideCoreComponent(requireActivity().applicationContext))
            .build()
            .inject(this)

        showLoader(view, true)

        val nextMovieImage = view.findViewById<ImageView>(R.id.movie_image)
        val nextMovieTitle = view.findViewById<TextView>(R.id.movie_title)

        viewModel =
            ViewModelProviders.of(this@ExploreFragment, factory).get(ExploreViewModel::class.java)
        viewModel.getPopularMovies()

        viewModel.popularMovie.observe(this@ExploreFragment, Observer {
            it?.let { popularMovies ->
                showLoader(view, false)
                view.EXPLORE_popular_title.visibile()
                val popularAdapter = PopularAdapter(popularMovies)
                view.EXPLORE_popular_list.adapter = popularAdapter

                // TODO: use a better logic
                if (popularMovies.isNotEmpty()) {
                    val movie = popularMovies.random()
                    view.EXPLORE_next_movie_title.visibile()
                    view.EXPLORE_next_movie_tile.visibile()
                    nextMovieTitle.text = movie.title

                    val lottieDrawable = LottieDrawable()
                    LottieCompositionFactory.fromRawRes(requireContext(), R.raw.imageloader)
                        .addListener { lottieComposition ->
                            lottieDrawable.composition = lottieComposition
                            lottieDrawable.repeatCount = LottieDrawable.INFINITE
                            lottieDrawable.playAnimation()
                        }

                    Glide.with(this@ExploreFragment)
                        .load(movie.backdropPath.getFullImageUrl())
                        .placeholder(lottieDrawable)
                        .fitCenter()
                        .into(nextMovieImage)

                }
            }
        })

        viewModel.showError.observe(this@ExploreFragment, Observer {
            it?.consume()?.let { showError ->
                showError(view, showError)
            }
        })
    }

    private fun showLoader(view: View, showLoader: Boolean) {
        if (showLoader) {
            view.EXPLORE_animation.visibile()
            view.EXPLORE_animation.setAnimation("loader.json")
            view.EXPLORE_animation.playAnimation()
            view.EXPLORE_animation.repeatCount = LottieDrawable.INFINITE
        } else {
            view.EXPLORE_animation.pauseAnimation()
            view.EXPLORE_animation.gone()
        }
    }

    private fun showError(view: View, showLoader: Boolean) {
        if (showLoader) {
            view.EXPLORE_animation.visibile()
            view.EXPLORE_animation.setAnimation("nodata.json")
            view.EXPLORE_animation.playAnimation()
            view.EXPLORE_animation.repeatCount = LottieDrawable.INFINITE
        } else {
            view.EXPLORE_animation.pauseAnimation()
            view.EXPLORE_animation.gone()
        }
    }
}
