package com.davidups.starwars.features.planets.services

import com.davidups.starwars.features.planets.models.entity.PlanetsEntity
import retrofit2.Response
import retrofit2.http.GET

internal interface PlanetsApi {

    companion object {
        private const val PLANET = "planets"
    }

    @GET(PLANET)
    suspend fun getPlanets(): Response<PlanetsEntity>
}
