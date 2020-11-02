package com.davidups.starwars.core.di

import com.davidups.starwars.features.movies.usecases.MoviesRepository
import com.davidups.starwars.features.planets.usecases.PlanetsRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory<PlanetsRepository> { PlanetsRepository.Network(get(), get()) }
    factory<MoviesRepository> { MoviesRepository.Network(get(), get()) }
}
