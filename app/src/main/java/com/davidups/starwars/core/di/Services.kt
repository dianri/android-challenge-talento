package com.davidups.starwars.core.di

import com.davidups.starwars.features.movies.services.MoviesService
import com.davidups.starwars.features.planets.services.PlanetsService
import org.koin.dsl.module

val dataSourceModule = module {

    factory { PlanetsService(get()) }
    factory { MoviesService(get()) }
}
