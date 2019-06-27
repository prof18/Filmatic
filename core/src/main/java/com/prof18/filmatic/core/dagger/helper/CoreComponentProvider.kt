package com.prof18.filmatic.core.dagger.helper

import com.prof18.filmatic.core.dagger.CoreComponent

interface CoreComponentProvider  {
    fun provideCoreComponent(): CoreComponent
}