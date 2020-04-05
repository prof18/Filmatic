/*
 * Copyright 2019 Marco Gomiero
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

package com.prof18.filmatic.features.home.old.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.prof18.filmatic.features.home.R

//class HomeActivity : AppCompatActivity() {
//
//
//    private lateinit var navController: NavController
//
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_home)
//
//        // Get the navigation controller
//        navController = Navigation.findNavController(this, R.id.HOME_nav_host)
//
//        // Set the navigation controller to the nav bar
//        HOME_bottom_nav.setupWithNavController(navController)
//
//    }
//}

class HomeActivity : AppCompatActivity() {
    private val navSectionsStateKeeper by lazy {
        NavigationBottomBarSectionsStateKeeperWorkaround(
            activity = this,
            navHostContainerID = R.id.nav_host_fragment,
            navGraphIds = listOf(
                R.navigation.nav_explore,
                R.navigation.nav_discover,
                R.navigation.nav_profile
            ),
            bottomNavigationViewID = R.id.bottom_navigation
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        navSectionsStateKeeper.onCreate(savedInstanceState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        navSectionsStateKeeper.onRestoreInstanceState(savedInstanceState)
    }

    override fun onSupportNavigateUp() =
        navSectionsStateKeeper.onSupportNavigateUp()

    override fun onBackPressed() {
        if (!navSectionsStateKeeper.onSupportNavigateUp())
            super.onBackPressed()
    }
}