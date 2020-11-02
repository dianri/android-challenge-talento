package com.davidups.starwars.core.di

import com.davidups.starwars.features.movies.view.viewmodels.MoviesViewModel
import com.davidups.starwars.features.planets.view.viewmodels.PlanetsViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { PlanetsViewModel(get()) }
    viewModel { MoviesViewModel(get()) }
}
