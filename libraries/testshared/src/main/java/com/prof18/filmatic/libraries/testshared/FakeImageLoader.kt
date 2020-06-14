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
import coil.DefaultRequestOptions
import coil.ImageLoader
import coil.annotation.ExperimentalCoilApi
import coil.decode.DataSource
import coil.request.*

@ExperimentalCoilApi
val fakeImageLoader = object : ImageLoader {

    private val drawable = ColorDrawable(Color.BLACK)

    private val disposable = object : RequestDisposable {
        override val isDisposed = true
        override fun dispose() {}
        override suspend fun await() {}
    }

    override val defaults = DefaultRequestOptions()

    override fun execute(request: LoadRequest): RequestDisposable {
        // Always call onStart before onSuccess.
        request.target?.onStart(drawable)
        request.target?.onSuccess(drawable)
        return disposable
    }

    override suspend fun execute(request: GetRequest): RequestResult {
        return SuccessResult(drawable, DataSource.MEMORY_CACHE)
    }

    override fun invalidate(key: String) {}

    override fun clearMemory() {}

    override fun shutdown() {}
}