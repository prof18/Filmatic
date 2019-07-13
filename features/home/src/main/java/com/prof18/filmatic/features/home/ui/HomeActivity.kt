package com.prof18.filmatic.features.home.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.prof18.filmatic.features.home.R
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {


    private lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // Get the navigation controller
        navController = Navigation.findNavController(this, R.id.HOME_nav_host)

        // Set the navigation controller to the nav bar
        HOME_bottom_nav.setupWithNavController(navController)

    }
}
