package com.davidups.starwars.features.planets.services

import retrofit2.Retrofit

class PlanetsService(retrofit: Retrofit) : PlanetsApi {

    private val planetApi by lazy { retrofit.create(PlanetsApi::class.java) }

    override suspend fun getPlanets() = planetApi.getPlanets()
}
