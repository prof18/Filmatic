package com.prof18.filmatic

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.prof18.filmatic.features.home.contract.HomeContract
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var homeContract: HomeContract

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Decide what to open -> In the future open the onboarding activity if it's a new user
        startActivity(
            homeContract.getIntent(this),
        )
    }
}
