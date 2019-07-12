package com.prof18.filmatic.features.home.ui.discover.adapter;

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.prof18.filmatic.features.home.R
import com.prof18.filmatic.features.home.data.discovery.model.Genre
import kotlinx.android.synthetic.main.item_genre.view.*
import org.jetbrains.anko.toast


class GenreAdapter(val items: List<Genre>) :
    RecyclerView.Adapter<GenreAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_genre, parent, false))

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(items[position])

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Genre) {
            itemView.genre_title.text = item.name

            itemView.genre_title.setOnClickListener {
                itemView.context.toast("Genre Clicked")
            }

        }
    }
}
