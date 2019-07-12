package com.prof18.filmatic

import android.app.Application
import com.bumptech.glide.annotation.GlideModule
import com.prof18.filmatic.core.dagger.CoreComponent
import com.prof18.filmatic.core.dagger.DaggerCoreComponent
import com.prof18.filmatic.core.dagger.DataModule
import com.prof18.filmatic.core.dagger.helper.CoreComponentProvider
import timber.log.Timber
import com.bumptech.glide.module.AppGlideModule



class FilmaticApp: Application(), CoreComponentProvider {

    private lateinit var coreComponent: CoreComponent

    override fun provideCoreComponent(): CoreComponent {
        if (!this::coreComponent.isInitialized) {
            coreComponent = DaggerCoreComponent
                .builder()
                .dataModule(DataModule(this))
                .build()
        }
        return coreComponent
    }

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }


}

