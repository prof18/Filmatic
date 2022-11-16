package com.prof18.filmatic.features.home.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.load
import com.airbnb.lottie.LottieCompositionFactory
import com.airbnb.lottie.LottieDrawable
import com.prof18.filmatic.features.home.R
import com.prof18.filmatic.features.home.databinding.ItemTrendingCollectionBinding
import com.prof18.filmatic.features.home.presentation.state.HomeListItem
import com.prof18.filmatic.features.home.presentation.state.MovieId
import com.prof18.filmatic.libraries.uicomponents.databinding.ItemHeaderBinding
import com.prof18.filmatic.libraries.uicomponents.databinding.ItemMovieBigBinding
import com.prof18.filmatic.libraries.uicomponents.listitem.ItemHeader
import com.prof18.filmatic.libraries.uicomponents.listitem.ItemMovieBig
import com.prof18.filmatic.libraries.uicomponents.listitem.ItemMovieBottomText

class HomeAdapter(
    private val imageLoader: ImageLoader,
    private val onClick: (MovieId) -> Unit,
) : ListAdapter<HomeListItem, RecyclerView.ViewHolder>(DiffCallback()) {

    // Useful when there will be multiple horizontal lists
    private val viewPool = RecyclerView.RecycledViewPool()

    override fun getItemViewType(position: Int): Int {
        return when (currentList[position]) {
            is HomeListItem.Header -> HEADER
            is HomeListItem.TrendingCollection -> TRENDING_COLLECTION
            else -> MOVIE_BIG_CARD
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            HEADER -> {
                val itemBinding = ItemHeaderBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false,
                )
                HeaderViewHolder(itemBinding)
            }
            TRENDING_COLLECTION -> {
                val itemBinding = ItemTrendingCollectionBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false,
                )
                TrendingCollectionViewHolder(itemBinding)
            }
            else -> {
                val itemBinding = ItemMovieBigBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false,
                )
                MovieBigCardViewHolder(itemBinding)
            }
        }
    }

    override fun getItemCount() = currentList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val item = currentList[position]) {
            is HomeListItem.Header -> (holder as HeaderViewHolder).bind(item.data)
            is HomeListItem.TrendingCollection -> (holder as TrendingCollectionViewHolder).bind(item.data)
            is HomeListItem.MovieBigCard -> (holder as MovieBigCardViewHolder).bind(item.data)
        }
    }

    inner class HeaderViewHolder(
        private val binding: ItemHeaderBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ItemHeader) {
            binding.itemHeaderTitle.text = itemView.context.getText(item.titleResId)
        }
    }

    inner class TrendingCollectionViewHolder(
        private val binding: ItemTrendingCollectionBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: List<ItemMovieBottomText>) {

            val childLayoutManager = LinearLayoutManager(
                binding.homeTrendingList.context,
                RecyclerView.HORIZONTAL,
                false,
            )
            childLayoutManager.initialPrefetchItemCount = PREFETCHED_ITEM_COUNT

            binding.homeTrendingList.apply {

                layoutManager = childLayoutManager
                val horizontalAdapter = HorizontalCollectionAdapter(imageLoader, onClick)
                horizontalAdapter.submitList(item)
                horizontalAdapter.stateRestorationPolicy = StateRestorationPolicy.PREVENT_WHEN_EMPTY

                adapter = horizontalAdapter
                setRecycledViewPool(viewPool)
            }
        }
    }

    inner class MovieBigCardViewHolder(val binding: ItemMovieBigBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ItemMovieBig) {
            binding.itemMovieBigCardTitle.text = item.title

            val lottieDrawable = LottieDrawable()
            LottieCompositionFactory.fromRawRes(itemView.context, R.raw.image_loader)
                .addListener { lottieComposition ->
                    lottieDrawable.composition = lottieComposition
                    lottieDrawable.repeatCount = LottieDrawable.INFINITE
                    lottieDrawable.playAnimation()
                }

            with(binding.itemMovieBigCardImage) {
                transitionName = item.id.toString()
                load(item.imageUrl, imageLoader) {
                    placeholder(lottieDrawable)
                }
            }

            binding.itemMovieBigCard.setOnClickListener {
                onClick(MovieId(value = item.id))
            }
        }
    }

    private class DiffCallback : DiffUtil.ItemCallback<HomeListItem>() {

        override fun areItemsTheSame(oldItem: HomeListItem, newItem: HomeListItem): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: HomeListItem, newItem: HomeListItem) =
            oldItem == newItem
    }

    companion object {
        private const val HEADER = 0
        private const val TRENDING_COLLECTION = 1
        private const val MOVIE_BIG_CARD = 2
        private const val PREFETCHED_ITEM_COUNT = 4
    }
}
