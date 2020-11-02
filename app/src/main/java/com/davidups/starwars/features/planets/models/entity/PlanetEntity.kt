package com.davidups.starwars.features.planets.models.entity

import com.davidups.starwars.core.extensions.empty
import com.davidups.starwars.features.planets.models.data.Planet

data class PlanetEntity(
    val name: String?,
    val rotation_period: Int?,
    val orbital_period: Int?,
    val diameter: Int?,
    val climate: String?,
    val gravity: String?,
    val terrain: String?,
    val population: String?


) {

    companion object {
        fun empty() =
            PlanetEntity(String.empty(), Int.empty(), Int.empty(), Int.empty(), String.empty(), String.empty(), String.empty(), String.empty())
    }

    fun toPlanet() = Planet(name, rotation_period, orbital_period, diameter, climate, gravity, terrain, population)
}