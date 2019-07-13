package com.prof18.filmatic.features.home.ui.discover

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.prof18.filmatic.core.dagger.helper.CoreInjectHelper
import com.prof18.filmatic.core.utils.gone
import com.prof18.filmatic.features.home.R
import com.prof18.filmatic.features.home.dagger.DaggerHomeComponent
import com.prof18.filmatic.features.home.ui.HomeActivity
import com.prof18.filmatic.features.home.ui.discover.adapter.GenreAdapter
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.fragment_discover.view.*
import timber.log.Timber
import javax.inject.Inject


class DiscoverFragment : Fragment() {

    @Inject
    lateinit var factory: DiscoverViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_discover, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        DaggerHomeComponent
            .builder()
            .coreComponent(CoreInjectHelper.provideCoreComponent(requireActivity().applicationContext))
            .build()
            .inject(this)

        val viewModel =
            ViewModelProviders.of(this@DiscoverFragment, factory).get(DiscoverViewModel::class.java)

        viewModel.genres.observe(this@DiscoverFragment, Observer {
            it?.let { genres ->
                val adapter = GenreAdapter(genres)
                view.DISCOVERY_genre_list.adapter = adapter

            }
        })

        viewModel.showError.observe(this@DiscoverFragment, Observer {
            it?.consume()?.let { showError ->
//                showError(view, showError)
            }
        })

        viewModel.getGenres()

    }
}
