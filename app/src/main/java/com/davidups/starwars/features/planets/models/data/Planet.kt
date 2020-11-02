package com.davidups.starwars.features.planets.models.data

import com.davidups.starwars.features.planets.models.view.PlanetView

data class Planet(
    val title: String?,
    val episodeId: Int?,
    val openingCrawl: String?,
    val producer: String?,
    val releaseDate: String?
) {

    fun toMovieView() = PlanetView(title, episodeId, openingCrawl, producer, releaseDate)
}
