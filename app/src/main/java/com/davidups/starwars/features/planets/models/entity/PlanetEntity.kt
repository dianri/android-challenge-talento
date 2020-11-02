package com.davidups.starwars.features.planets.models.entity

import com.davidups.starwars.core.extensions.empty
import com.davidups.starwars.features.planets.models.data.Planet

data class PlanetEntity(
    val name: String?,
    val episode_id: Int?,
    val opening_crawl: String?,
    val producer: String?,
    val release_date: String?
) {

    companion object {
        fun empty() =
            PlanetEntity(String.empty(), Int.empty(), String.empty(), String.empty(), String.empty())
    }

    fun toMovie() = Planet(name, episode_id, opening_crawl, producer, release_date)
}