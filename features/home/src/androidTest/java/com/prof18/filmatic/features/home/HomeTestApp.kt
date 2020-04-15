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

package com.prof18.filmatic.features.home

import com.prof18.filmatic.features.home.di.DaggerTestHomeComponent
import com.prof18.filmatic.features.home.di.HomeComponent
import com.prof18.filmatic.features.home.di.HomeComponentProvider
import com.prof18.filmatic.features.home.di.TestHomeComponent
import com.prof18.filmatic.libraries.testshared.BaseTestApp

class HomeTestApp: BaseTestApp(), HomeComponentProvider {

    override fun getHomeComponent(): HomeComponent {
        return if (componentsMap.containsKey(HomeComponent::class)) {
            componentsMap[HomeComponent::class] as TestHomeComponent
        } else {
            val component = DaggerTestHomeComponent
                .builder()
                .coreComponent(provideCoreComponent())
                .build()
            componentsMap[HomeComponent::class] = component
            component
        }
    }

    override fun setHomeComponent(homeComponent: HomeComponent) {
        componentsMap[HomeComponent::class] = homeComponent
    }

}