package com.prof18.filmatic.core.di

import android.content.Context
import coil.ImageLoader
import com.prof18.filmatic.core.BuildConfig
import com.prof18.filmatic.core.architecture.CoroutineDispatcherProvider
import com.prof18.filmatic.core.net.AuthInterceptor
import com.prof18.filmatic.core.net.ConnectivityChecker
import com.prof18.filmatic.core.net.ConnectivityCheckerImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class CoreModule {

    @Binds
    @Singleton
    abstract fun bindConnectivityChecker(impl: ConnectivityCheckerImpl): ConnectivityChecker

    companion object {
        @Singleton
        @Provides
        fun provideOkHttpClient(
            interceptor: AuthInterceptor,
        ): OkHttpClient {
            val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
                level = if (BuildConfig.DEBUG) {
                    HttpLoggingInterceptor.Level.BODY
                } else {
                    HttpLoggingInterceptor.Level.NONE
                }
            }

            return OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addInterceptor(httpLoggingInterceptor)
                .build()
        }

        @Provides
        @Singleton
        fun provideImageLoader(
            @ApplicationContext context: Context,
        ): ImageLoader {
            val imageLoaderBuilder = ImageLoader.Builder(context)
            return imageLoaderBuilder.build()
        }

        @Provides
        @Singleton
        fun provideCoroutineDispatcherProvider() = CoroutineDispatcherProvider(
            main = Dispatchers.Main,
            computation = Dispatchers.Default,
            io = Dispatchers.IO,
        )
    }
}
