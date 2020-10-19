/*
 * Copyright 2020 Marco Gomiero
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.prof18.filmatic.libraries.testshared

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import coil.ImageLoader
import coil.annotation.ExperimentalCoilApi
import coil.bitmap.BitmapPool
import coil.decode.DataSource
import coil.memory.MemoryCache
import coil.request.DefaultRequestOptions
import coil.request.Disposable
import coil.request.ImageRequest
import coil.request.ImageResult
import coil.request.SuccessResult

@ExperimentalCoilApi
val fakeImageLoader = object : ImageLoader {

    private val drawable = ColorDrawable(Color.BLACK)

    private val disposable = object : Disposable {
        override val isDisposed = true
        override fun dispose() {}
        override suspend fun await() {}
    }

    override val defaults = DefaultRequestOptions()

    // Optionally, you can add a custom fake memory cache implementation.
    override val memoryCache get() = throw UnsupportedOperationException()

    override val bitmapPool = BitmapPool(0)

    override fun enqueue(request: ImageRequest): Disposable {
        // Always call onStart before onSuccess.
        request.target?.onStart(drawable)
        request.target?.onSuccess(drawable)
        return disposable
    }

    override suspend fun execute(request: ImageRequest): ImageResult {
        return SuccessResult(
            drawable = drawable,
            request = request,
            metadata = ImageResult.Metadata(
                memoryCacheKey = MemoryCache.Key(""),
                isSampled = false,
                dataSource = DataSource.MEMORY_CACHE,
                isPlaceholderMemoryCacheKeyPresent = false
            )
        )
    }

    override fun shutdown() {}
}
