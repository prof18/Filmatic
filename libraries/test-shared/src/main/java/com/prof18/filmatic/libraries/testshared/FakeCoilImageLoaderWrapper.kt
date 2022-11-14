package com.prof18.filmatic.libraries.testshared

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import coil.ComponentRegistry
import coil.ImageLoader
import coil.decode.DataSource
import coil.disk.DiskCache
import coil.memory.MemoryCache
import coil.request.DefaultRequestOptions
import coil.request.Disposable
import coil.request.ImageRequest
import coil.request.ImageResult
import coil.request.SuccessResult
import kotlinx.coroutines.Deferred

// For the fake loader, some methods are not requested
@Suppress("EmptyFunctionBlock")
class FakeCoilImageLoaderWrapper(private val context: Context) {

    val fakeImageLoader = object : ImageLoader {

        private val disposable = object : Disposable {
            override val isDisposed get() = true
            override val job: Deferred<ImageResult>
                get() = TODO("Not yet implemented")

            override fun dispose() {}
        }
        override val components: ComponentRegistry
            get() = TODO("Not yet implemented")

        override val defaults = DefaultRequestOptions()
        override val diskCache: DiskCache?
            get() = TODO("Not yet implemented")

        // Optionally, you can add a custom fake memory cache implementation.
        override val memoryCache get() = throw UnsupportedOperationException()

        override fun enqueue(request: ImageRequest): Disposable {
            // Always call onStart before onSuccess.
            request.target?.onStart(placeholder = ColorDrawable(Color.BLACK))
            request.target?.onSuccess(result = ColorDrawable(Color.BLACK))
            return disposable
        }

        override suspend fun execute(request: ImageRequest): ImageResult {
            return SuccessResult(
                drawable = ColorDrawable(Color.BLACK),
                request = request,
                dataSource = DataSource.MEMORY_CACHE,
                memoryCacheKey = null,
                diskCacheKey = null,
                isSampled = false,
                isPlaceholderCached = false
            )
        }

        override fun shutdown() {}

        override fun newBuilder() = ImageLoader.Builder(context)
    }
}
