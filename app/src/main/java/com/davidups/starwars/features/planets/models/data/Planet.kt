package com.davidups.starwars.features.planets.models.data

import com.davidups.starwars.features.planets.models.view.PlanetView

data class Planet(
    val name: String?,
    val rotationPeriod: Int?,
    val orbitalPeriod: Int?,
    val diameter: Int?,
    val climate: String?,
    val gravity: String?,
    val terrain: String?,
    val population: String?

) {

    fun toPlanetView() = PlanetView(name, rotationPeriod, orbitalPeriod, diameter, climate, gravity, terrain, population)
}
