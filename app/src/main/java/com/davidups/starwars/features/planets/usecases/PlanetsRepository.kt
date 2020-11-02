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

    fun planets(): Flow<State<PlanetsView>>

    class Network(
        private val ioDispatcher: CoroutineDispatcher,
        private val service: PlanetsService
    ) : PlanetsRepository {

        override fun planets() =
            flow {
                emit(getPlanetsFromApi())
            }
                .catch { emit(Error(Throwable("s"))) }
                .flowOn(ioDispatcher)

        private suspend fun getPlanetsFromApi() = service.getPlanets()
            .run {
                if (isSuccessful && body() != null) {
                    Success(body()!!.toPlanets().toPlanetsView())
                } else {
                    Error(Throwable("s"))
                }
            }
    }
}
