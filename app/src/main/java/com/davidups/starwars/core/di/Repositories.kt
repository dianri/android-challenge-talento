package com.davidups.starwars.core.di

import com.davidups.starwars.features.movies.usecases.MoviesRepository
import org.koin.dsl.module

val repositoryModule = module {

    factory<MoviesRepository> { MoviesRepository.Network(get(), get()) }

}
