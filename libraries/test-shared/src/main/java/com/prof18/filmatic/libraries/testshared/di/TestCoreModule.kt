package com.prof18.filmatic.libraries.testshared.di

import android.content.Context
import coil.ImageLoader
import com.prof18.filmatic.core.di.CoreModule
import com.prof18.filmatic.core.net.ConnectivityChecker
import com.prof18.filmatic.libraries.testshared.FakeCoilImageLoaderWrapper
import com.prof18.filmatic.libraries.testshared.fakes.FakeConnectivityCheckReturnSuccess
import com.prof18.filmatic.libraries.testshared.provideFakeCoroutinesDispatcherProvider
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import kotlinx.coroutines.test.TestCoroutineDispatcher
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockWebServer
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [CoreModule::class]
)
abstract class TestCoreModule {

    @Binds
    @Singleton
    abstract fun bindConnectivityChecker(impl: FakeConnectivityCheckReturnSuccess): ConnectivityChecker

    companion object {

        @Singleton
        @Provides
        fun provideOkHttpClient(): OkHttpClient {
            return OkHttpClient.Builder()
                .build()
        }

        @Provides
        @Singleton
        fun provideImageLoader(
            @ApplicationContext context: Context
        ): ImageLoader {
            return FakeCoilImageLoaderWrapper(context).fakeImageLoader
        }

        @Provides
        fun provideCoroutineDispatcherProvider() = provideFakeCoroutinesDispatcherProvider(
            TestCoroutineDispatcher()
        )

        @Provides
        fun provideMockWebServer(): MockWebServer {
            return MockWebServer()
        }
    }
}
