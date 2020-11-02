package com.davidups.starwars.features.planets.usecases

import com.davidups.starwars.core.functional.Error
import com.davidups.starwars.core.functional.State
import com.davidups.starwars.core.functional.Success
import com.davidups.starwars.features.planets.models.view.PlanetsView
import com.davidups.starwars.features.planets.services.PlanetsService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

interface PlanetsRepository {

    fun movies(): Flow<State<PlanetsView>>

    class Network(
        private val ioDispatcher: CoroutineDispatcher,
        private val service: PlanetsService
    ) : PlanetsRepository {

        override fun movies() =
            flow {
                emit(getMoviesFromApi())
            }
                .catch { emit(Error(Throwable("s"))) }
                .flowOn(ioDispatcher)

        private suspend fun getMoviesFromApi() = service.getMovies()
            .run {
                if (isSuccessful && body() != null) {
                    Success(body()!!.toMovies().toMoviesView())
                } else {
                    Error(Throwable("s"))
                }
            }
    }
}
