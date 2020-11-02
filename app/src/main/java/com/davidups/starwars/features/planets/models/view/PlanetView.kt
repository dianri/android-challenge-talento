package com.davidups.starwars.features.planets.models.view

import com.davidups.starwars.core.extensions.empty
import java.io.Serializable

data class PlanetView(
    val title: String?,
    val episodeId: Int?,
    val openingCrawl: String?,
    val producer: String?,
    val releaseDate: String?,
    var favourite: Boolean = false
) : Serializable {

    companion object {
        fun empty() =
            PlanetView(String.empty(), Int.empty(), String.empty(), String.empty(), String.empty(), false)
    }
}
