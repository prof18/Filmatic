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

package com.prof18.filmatic.features.home.presentation.explore;

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.api.load
import com.airbnb.lottie.LottieCompositionFactory
import com.airbnb.lottie.LottieDrawable
import com.prof18.filmatic.features.home.R
import com.prof18.filmatic.libraries.uicomponents.databinding.ItemMovieBottomTitleBinding
import com.prof18.filmatic.libraries.uicomponents.listitem.ItemMovieBottomTitle

class TrendingCollectionAdapter(
    val items: List<ItemMovieBottomTitle> = emptyList(),
    val context: Context,
    val imageLoader: ImageLoader
) :
    RecyclerView.Adapter<TrendingCollectionAdapter.ViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TrendingCollectionAdapter.ViewHolder {
        val itemBinding =
            ItemMovieBottomTitleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: TrendingCollectionAdapter.ViewHolder, position: Int) =
        holder.bind(items[position])

    inner class ViewHolder(val binding: ItemMovieBottomTitleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ItemMovieBottomTitle) {
            binding.IteMovieBottomTitleTitle.text = item.title

            val lottieDrawable = LottieDrawable()
            LottieCompositionFactory.fromRawRes(itemView.context, R.raw.imageloader)
                .addListener { lottieComposition ->
                    lottieDrawable.composition = lottieComposition
                    lottieDrawable.repeatCount = LottieDrawable.INFINITE
                    lottieDrawable.playAnimation()
                }

            // TODO: add null image placeholder
            val imageUrl = item.imageUrl
            if (imageUrl != null) {
                binding.IteMovieBottomTitleImage.load(item.imageUrl, imageLoader) {
                    placeholder(lottieDrawable)
                }
            }

            binding.IteMovieBottomTitleCard.setOnClickListener {
                // TODO: Add on click
            }
        }
    }
}
