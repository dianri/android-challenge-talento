package com.davidups.starwars.features.movies.view.adapters

import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.davidups.starwars.R
import com.davidups.starwars.core.extensions.inflate
import com.davidups.starwars.core.extensions.loadFromUrl
import com.davidups.starwars.core.extensions.randomImage
import com.davidups.starwars.features.movies.models.view.MovieView
import kotlin.properties.Delegates
import kotlinx.android.synthetic.main.item_person_row.view.*

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    internal var collection: List<MovieView> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    internal var clickListener: (MovieView, View) -> Unit = { movieView: MovieView, view1: View ->
        val bundle = bundleOf("episodeInfo" to movieView)
        view1.findNavController().navigate(R.id.detail_movie, bundle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(parent.inflate(R.layout.item_person_row))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(collection[position], clickListener)
    }

    override fun getItemCount() = collection.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(movie: MovieView, clickListener: (MovieView, View) -> Unit) {
            itemView.ivBanner.loadFromUrl(String.randomImage())
            itemView.tvName.text = movie.title
            itemView.cvPerson.setOnClickListener {
                clickListener(movie, it)
            }
        }
    }
}
