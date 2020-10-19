/*
 * Copyright 2020 Marco Gomiero
 *
 * Licensed under the Apcense, Version 2.0 (the "License");
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

package com.prof18.filmatic.features.home.presentation.explore

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.load
import com.airbnb.lottie.LottieCompositionFactory
import com.airbnb.lottie.LottieDrawable
import com.prof18.filmatic.features.home.R
import com.prof18.filmatic.features.home.databinding.ItemTrendingCollectionBinding
import com.prof18.filmatic.features.home.presentation.explore.model.ExploreItem
import com.prof18.filmatic.features.home.presentation.explore.model.ItemHorizontalCollection
import com.prof18.filmatic.libraries.uicomponents.databinding.ItemHeaderBinding
import com.prof18.filmatic.libraries.uicomponents.databinding.ItemMovieBigBinding
import com.prof18.filmatic.libraries.uicomponents.listitem.ItemHeader
import com.prof18.filmatic.libraries.uicomponents.listitem.ItemMovieBig
import com.prof18.filmatic.libraries.uicomponents.listitem.ItemMovieBottomTitle

class ExploreAdapter(val context: Context, val imageLoader: ImageLoader) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var items: List<ExploreItem> = emptyList()

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is ExploreItem.Header -> HEADER
            is ExploreItem.TrendingCollection -> TRENDING_COLLECTION
            else -> MOVIE_BIG_CARD
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            HEADER -> {
                val itemBinding =
                    ItemHeaderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                HeaderViewHolder(itemBinding)
            }
            TRENDING_COLLECTION -> {
                val itemBinding = ItemTrendingCollectionBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                TrendingCollectionViewHolder(itemBinding)
            }
            else -> {
                val itemBinding =
                    ItemMovieBigBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                MovieBigCardViewHolder(itemBinding)
            }
        }
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val item = items[position]) {
            is ExploreItem.Header -> (holder as HeaderViewHolder).bind(item.data)
            is ExploreItem.TrendingCollection -> (holder as TrendingCollectionViewHolder).bind(item.data)
            is ExploreItem.MovieBigCard -> (holder as MovieBigCardViewHolder).bind(item.data)
        }
    }

    inner class HeaderViewHolder(val binding: ItemHeaderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ItemHeader) {
            binding.ItemHeaderTitle.text = context.getText(item.titleResId)
        }
    }

    inner class TrendingCollectionViewHolder(val binding: ItemTrendingCollectionBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ItemHorizontalCollection<ItemMovieBottomTitle>) {
            val adapter = TrendingCollectionAdapter(item.items, context, imageLoader)
            binding.ExploreTrendingList.adapter = adapter
        }
    }

    inner class MovieBigCardViewHolder(val binding: ItemMovieBigBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ItemMovieBig) {
            binding.ItemMovieBigTitle.text = item.title

            val lottieDrawable = LottieDrawable()
            LottieCompositionFactory.fromRawRes(itemView.context, R.raw.imageloader)
                .addListener { lottieComposition ->
                    lottieDrawable.composition = lottieComposition
                    lottieDrawable.repeatCount = LottieDrawable.INFINITE
                    lottieDrawable.playAnimation()
                }

            binding.ItemMovieBigImage.load(item.imageUrl, imageLoader) {
                placeholder(lottieDrawable)
            }

            binding.ItemMovieBigCard.setOnClickListener {
                // TODO: Add on click
            }
        }
    }

    companion object {
        const val HEADER = 0
        const val TRENDING_COLLECTION = 1
        const val MOVIE_BIG_CARD = 2
    }
}
