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

package com.prof18.filmatic.libraries.testshared

import android.app.Application
import com.prof18.filmatic.core.dagger.CoreComponent
import com.prof18.filmatic.core.dagger.DaggerCoreComponent
import com.prof18.filmatic.core.dagger.DataModule
import com.prof18.filmatic.core.dagger.helper.CoreComponentProvider
import kotlin.reflect.KClass

open class BaseTestApp: Application(), CoreComponentProvider {

    private lateinit var coreComponent: CoreComponent
    protected val componentsMap = mutableMapOf<KClass<*>, Any>()

    override fun provideCoreComponent(): CoreComponent {
        if (!this::coreComponent.isInitialized) {
            coreComponent = DaggerCoreComponent
                .builder()
                .dataModule(DataModule(this))
                .build()
        }
        return coreComponent
    }

}