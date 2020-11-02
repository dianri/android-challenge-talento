package com.davidups.starwars.features.planets.models.view

import com.davidups.starwars.core.extensions.empty
import java.io.Serializable

data class PlanetsView(
    val count: Int?,
    val next: String?,
    val previous: String?,
    val results: MutableList<PlanetView>?
) : Serializable {
    companion object {
        fun empty() = PlanetsView(Int.empty(), String.empty(), String.empty(), mutableListOf())
    }
}
