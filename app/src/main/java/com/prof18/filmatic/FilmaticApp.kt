package com.prof18.filmatic

import android.app.Application
import android.content.Context
import com.prof18.filmatic.core.dagger.ApplicationModule
import com.prof18.filmatic.core.dagger.CoreComponent
import com.prof18.filmatic.core.dagger.DaggerCoreComponent
import com.prof18.filmatic.core.dagger.helper.CoreComponentProvider

class FilmaticApp: Application(), CoreComponentProvider {

    private lateinit var coreComponent: CoreComponent

    override fun provideCoreComponent(): CoreComponent {
        if (!this::coreComponent.isInitialized) {
            coreComponent = DaggerCoreComponent
                .builder()
                .applicationModule(ApplicationModule(this))
                .build()
        }
        return coreComponent
    }


}

