package com.prof18.filmatic.core.dagger

import android.content.Context
import dagger.Component
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Component(modules = [CoreModule::class, ApplicationModule::class])
@Singleton
interface CoreComponent {

    @Component.Builder interface Builder {
        fun build(): CoreComponent
        fun applicationModule(applicationModule: ApplicationModule): Builder
    }

    // Things that we want to expose
    //  downstream components need these exposed
    fun provideOkHttpClient(): OkHttpClient
    fun provideContext(): Context

}