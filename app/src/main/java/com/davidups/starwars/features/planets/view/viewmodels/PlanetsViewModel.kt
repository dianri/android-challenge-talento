package com.davidups.starwars.features.planets.view.viewmodels

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.davidups.starwars.core.extensions.cancelIfActive
import com.davidups.starwars.core.interactor.UseCase
import com.davidups.starwars.core.functional.Error
import com.davidups.starwars.core.functional.Success
import com.davidups.starwars.core.platform.BaseViewModel
import com.davidups.starwars.features.planets.models.view.PlanetsView
import com.davidups.starwars.features.planets.usecases.GetPlanets
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class PlanetsViewModel(
    private val getPlanets: GetPlanets
) : BaseViewModel() {

    var planets = MutableLiveData<PlanetsView>()
    private var getPlanetsJob: Job? = null
    var sharedPreference: SharedPreferences? = null

    fun getPlanets() {
        getPlanetsJob.cancelIfActive()
        getPlanetsJob = viewModelScope.launch {
            getPlanets(UseCase.None())
                .onStart { handleShowSpinner(true) }
                .onEach { handleShowSpinner(false) }
                .catch { failure -> handleFailure(failure) }
                .collect { result ->
                    when (result) {
                        is Success<PlanetsView> -> {
                            planets.value = getFavourites(result.data)
                        }
                        is Error -> {
                        }
                    }
                }
        }
    }

    private fun getFavourites(planets: PlanetsView): PlanetsView {
        if (planets.results != null) {
            for (planet in planets.results) {
                if (planet.name.toString() == getFavourite(planet.name.toString())) {
                    planet.favourite = true
                }
            }
        }
        return planets
    }

    fun getSharePreferences(context: Context) {
        sharedPreference = context.getSharedPreferences("pref", Context.MODE_PRIVATE)
    }

    private fun getFavourite(KEY_NAME: String): String? {
        return sharedPreference?.getString(KEY_NAME, null)
    }
}
