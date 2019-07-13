package com.prof18.filmatic.features.home.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.prof18.filmatic.core.dagger.helper.CoreInjectHelper
import com.prof18.filmatic.core.utils.gone
import com.prof18.filmatic.features.home.R
import com.prof18.filmatic.features.home.dagger.DaggerHomeComponent
import com.prof18.filmatic.features.home.ui.HomeActivity
import com.prof18.filmatic.features.home.ui.explore.ExploreViewModel
import com.prof18.filmatic.features.home.ui.explore.ExploreViewModelFactory
import kotlinx.android.synthetic.main.activity_home.*
import javax.inject.Inject

class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }
}
