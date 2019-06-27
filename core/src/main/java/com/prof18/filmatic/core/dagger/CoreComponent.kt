package com.prof18.filmatic.core.dagger

import dagger.Component
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Component(modules = [CoreModule::class])
@Singleton
interface CoreComponent {

    @Component.Builder interface Builder {
        fun build(): CoreComponent
    }

    // Things that we want to expose
    //  downstream components need these exposed
    fun provideOkHttpClient(): OkHttpClient
}