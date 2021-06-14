package com.prof18.filmatic.features.home.presentation.state

import com.prof18.filmatic.libraries.uicomponents.listitem.ItemHeader
import com.prof18.filmatic.libraries.uicomponents.listitem.ItemMovieBig
import com.prof18.filmatic.libraries.uicomponents.listitem.ItemMovieBottomText

/**
 * This class represents the different elements that can be contained in the home RecyclerView
 */
sealed class HomeListItem {
    data class Header(val data: ItemHeader) : HomeListItem()
    data class TrendingCollection(val data: List<ItemMovieBottomText>) : HomeListItem()
    data class MovieBigCard(val data: ItemMovieBig) : HomeListItem()
}
