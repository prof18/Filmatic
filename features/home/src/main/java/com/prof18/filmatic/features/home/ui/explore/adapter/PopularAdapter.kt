/*
 * Copyright 2019 Marco Gomiero
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

package com.prof18.filmatic.features.home.ui.explore.adapter;

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieComposition
import com.airbnb.lottie.LottieCompositionFactory
import com.airbnb.lottie.LottieDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.prof18.filmatic.core.utils.getFullImageUrl
import com.prof18.filmatic.features.home.R
import com.prof18.filmatic.features.home.data.popular.model.Movie

class PopularAdapter(val items: List<Movie>) :
    RecyclerView.Adapter<PopularAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.item_movie_bottom_title,
            parent,
            false
        )
    )

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(items[position])

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val card: CardView = itemView.findViewById(R.id.movie_card)
        private val movieImage: ImageView = itemView.findViewById(R.id.movie_image)
        private val movieTile: TextView = itemView.findViewById(R.id.movie_title)

        fun bind(item: Movie) {

            val lottieDrawable = LottieDrawable()
            LottieCompositionFactory.fromRawRes(itemView.context, R.raw.imageloader).addListener { lottieComposition ->
                lottieDrawable.composition = lottieComposition
                lottieDrawable.repeatCount = LottieDrawable.INFINITE
                lottieDrawable.playAnimation()
            }

            Glide.with(itemView.context)
                .load(item.backdropPath.getFullImageUrl())
                .placeholder(lottieDrawable)
                .fitCenter()
                .into(movieImage)

            movieTile.text = item.title
        }
    }
}
