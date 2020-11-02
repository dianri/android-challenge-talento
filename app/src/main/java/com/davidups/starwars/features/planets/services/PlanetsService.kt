package com.davidups.starwars.features.planets.services

import retrofit2.Retrofit

class PlanetsService(retrofit: Retrofit) : PlanetsApi {

    private val movieApi by lazy { retrofit.create(PlanetsApi::class.java) }

    override suspend fun getMovies() = movieApi.getMovies()
}
