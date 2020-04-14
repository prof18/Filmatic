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

package com.prof18.filmatic.features.home.di

import com.prof18.filmatic.core.dagger.CoreComponent
import com.prof18.filmatic.core.dagger.scope.FeatureScope
import com.prof18.filmatic.features.home.presentation.DiscoverFragment
import com.prof18.filmatic.features.home.presentation.explore.ExploreFragment
import com.prof18.filmatic.features.home.presentation.ProfileFragment
import dagger.Component

@Component(
    modules = [HomeModule::class],
    dependencies = [CoreComponent::class]
)
@FeatureScope
interface HomeComponent {
    fun inject(fragment: ExploreFragment)
    fun inject(fragment: DiscoverFragment)
    fun inject(fragment: ProfileFragment)
}