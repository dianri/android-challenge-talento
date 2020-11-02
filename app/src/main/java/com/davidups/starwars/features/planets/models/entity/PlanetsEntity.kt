package com.davidups.starwars.features.planets.models.entity

import com.davidups.starwars.core.extensions.empty
import com.davidups.starwars.features.planets.models.data.Planets

data class PlanetsEntity(
    val count: Int?,
    val next: String?,
    val previus: String?,
    val results: MutableList<PlanetEntity>?
) {
    companion object {
        fun empty() = PlanetsEntity(Int.empty(), String.empty(), String.empty(), mutableListOf())
    }

    fun toMovies() = Planets(count, next, previus, results?.map { it.toMovie() }?.toMutableList())
}
