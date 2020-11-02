package com.davidups.starwars.features.planets.usecases

import com.davidups.starwars.core.interactor.UseCase
import com.davidups.starwars.core.functional.State
import com.davidups.starwars.features.planets.models.view.PlanetsView

class GetPlanets(private val moviesRepository: PlanetsRepository) : UseCase<State<PlanetsView>, UseCase.None>() {
    override fun run(params: None?) = moviesRepository.movies()
}
