package com.davidups.starwars.features.planets.models.data

import com.davidups.starwars.features.planets.models.view.PlanetsView

data class Planets(
    val count: Int?,
    val next: String?,
    val previous: String?,
    val results: MutableList<Planet>?
) {

    fun toMoviesView() =
        PlanetsView(count, next, previous, results?.map { it.toMovieView() }?.toMutableList())
}
