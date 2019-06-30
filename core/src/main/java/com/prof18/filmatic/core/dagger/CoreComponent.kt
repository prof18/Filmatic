package com.prof18.filmatic.core.dagger

import android.content.Context
import com.prof18.filmatic.core.UserPreferenceManager
import dagger.Component
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Component(modules = [CoreModule::class, DataModule::class])
@Singleton
interface CoreComponent {

    @Component.Builder interface Builder {
        fun build(): CoreComponent
        fun dataModule(dataModule: DataModule): Builder
    }

    // Things that we want to expose
    //  downstream components need these exposed
    fun provideOkHttpClient(): OkHttpClient
    fun provideContext(): Context
    fun getUserPreferencesManager(): UserPreferenceManager

}