package com.prof18.filmatic.features.home.dagger

import com.prof18.filmatic.core.dagger.CoreComponent
import com.prof18.filmatic.core.dagger.scope.FeatureScope
import com.prof18.filmatic.features.home.ui.HomeActivity
import dagger.Component

@Component(
    modules = [HomeModule::class],
    dependencies = [CoreComponent::class]
)
@FeatureScope
interface HomeComponent {
    fun inject(activity: HomeActivity)
}