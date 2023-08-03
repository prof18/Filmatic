package com.prof18.filmatic.features.about.di

import com.prof18.filmatic.features.about.AboutContractImpl
import com.prof18.filmatic.features.about.contract.AboutContract
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
internal abstract class AnalyticsModule {
    @Binds
    abstract fun bindAboutContract(
        aboutContractImpl: AboutContractImpl
    ): AboutContract
}
