package com.prof18.filmatic.features.home.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.load
import com.airbnb.lottie.LottieCompositionFactory
import com.airbnb.lottie.LottieDrawable
import com.prof18.filmatic.features.home.R
import com.prof18.filmatic.features.home.presentation.state.MovieId
import com.prof18.filmatic.libraries.uicomponents.databinding.ItemMovieBottomTextBinding
import com.prof18.filmatic.libraries.uicomponents.listitem.ItemMovieBottomText

class HorizontalCollectionAdapter(
    val imageLoader: ImageLoader,
    val onClick: (MovieId) -> Unit
) : ListAdapter<ItemMovieBottomText, HorizontalCollectionAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HorizontalCollectionAdapter.ViewHolder {
        val itemBinding = ItemMovieBottomTextBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(itemBinding)
    }

    override fun getItemCount() = currentList.size

    override fun onBindViewHolder(holder: HorizontalCollectionAdapter.ViewHolder, position: Int) =
        holder.bind(currentList[position])

    inner class ViewHolder(
        private val binding: ItemMovieBottomTextBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ItemMovieBottomText) {
            binding.itemMovieBottomTextTitle.text = item.title

            val lottieDrawable = LottieDrawable()
            LottieCompositionFactory.fromRawRes(itemView.context, R.raw.image_loader)
                .addListener { lottieComposition ->
                    lottieDrawable.composition = lottieComposition
                    lottieDrawable.repeatCount = LottieDrawable.INFINITE
                    lottieDrawable.playAnimation()
                }
            with(binding.itemMovieBottomTextImage) {
                transitionName = item.id.toString()
                load(item.imageUrl, imageLoader) {
                    placeholder(lottieDrawable)
                }
            }

            binding.itemMovieBottomTextCard.setOnClickListener {
                onClick(MovieId(value = item.id))
            }
        }
    }

    private class DiffCallback : DiffUtil.ItemCallback<ItemMovieBottomText>() {

        override fun areItemsTheSame(oldItem: ItemMovieBottomText, newItem: ItemMovieBottomText) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: ItemMovieBottomText, newItem: ItemMovieBottomText) =
            oldItem == newItem
    }
}
