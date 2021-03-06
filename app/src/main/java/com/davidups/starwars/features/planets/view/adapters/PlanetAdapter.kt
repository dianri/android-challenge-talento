package com.davidups.starwars.features.planets.view.adapters

import android.content.Context
import android.content.SharedPreferences
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.davidups.starwars.R
import com.davidups.starwars.core.extensions.inflate
import com.davidups.starwars.core.extensions.loadFromUrl
import com.davidups.starwars.core.extensions.randomImage
import com.davidups.starwars.features.planets.models.view.PlanetView
import kotlin.properties.Delegates
import kotlinx.android.synthetic.main.item_person_row.view.*

class PlanetAdapter : RecyclerView.Adapter<PlanetAdapter.ViewHolder>() {


    internal var collection: List<PlanetView> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    internal var clickListener: (PlanetView, View) -> Unit = { planetView: PlanetView, view1: View ->
        val bundle = bundleOf("episodeInfo" to planetView)
        view1.findNavController().navigate(R.id.detail_planets, bundle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(parent.inflate(R.layout.item_person_row))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(collection[position], clickListener)
    }

    override fun getItemCount() = collection.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var sharedPreference: SharedPreferences? = null

        fun bind(planet: PlanetView, clickListener: (PlanetView, View) -> Unit) {
            itemView.ivBanner.loadFromUrl(String.randomImage())
            itemView.tvName.text = planet.name
            itemView.cvPerson.setOnClickListener {
                clickListener(planet, it)
            }
            itemView.ivFavorite.setOnClickListener {
                getSahrePreferences(itemView.context)
                if (planet.favourite) {
                    itemView.ivFavorite.setImageDrawable(itemView.resources.getDrawable(R.drawable.ic_star_deselected))
                    sharedPreferenceRemove(planet.name.toString())
                    planet.favourite = false
                } else {
                    itemView.ivFavorite.setImageDrawable(itemView.resources.getDrawable(R.drawable.ic_star_selected))
                    sharedPreferenceInsert(planet.name.toString(), planet.name.toString())
                    planet.favourite = true
                }

            }

            if (planet.favourite) {
                itemView.ivFavorite.setImageDrawable(itemView.resources.getDrawable(R.drawable.ic_star_selected))
            }
        }

        private fun getSahrePreferences(context: Context) {
            sharedPreference = context.getSharedPreferences("pref", Context.MODE_PRIVATE)
        }

        private fun sharedPreferenceInsert(name: String, value: String) {
            val editor = sharedPreference?.edit()
            editor.let {
                editor?.putString(name, value)
                editor?.commit()
            }
        }

        private fun sharedPreferenceRemove(name: String) {
            val editor = sharedPreference?.edit()
            editor.let {
                editor?.remove(name)
                editor?.commit()
            }
        }
    }

}
