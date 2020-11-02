package com.davidups.starwars.core.di

import com.davidups.starwars.features.movies.usecases.GetMovies
import com.davidups.starwars.features.planets.usecases.GetPlanets
import org.koin.dsl.module

val useCaseModule = module {
    factory { GetPlanets(get()) }
    factory { GetMovies(get()) }
}
