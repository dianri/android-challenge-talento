package com.davidups.starwars.features.planets.models.view

import com.davidups.starwars.core.extensions.empty
import java.io.Serializable

data class PlanetView(
    val name: String?,
    val rotationPeriod: Int?,
    val orbitalPeriod:Int?,
    val diameter: Int?,
    val climate: String?,
    val gravity: String?,
    val terrain: String?,
    val population: String?,
    var favourite: Boolean = false
) : Serializable {

    companion object {
        fun empty() =
            PlanetView(String.empty(), Int.empty(), Int.empty(), Int.empty(), String.empty(), String.empty(), String.empty(), String.empty(),false)
    }
}
