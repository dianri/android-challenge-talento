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
    private val getMovies: GetPlanets
) : BaseViewModel() {

    var movies = MutableLiveData<PlanetsView>()
    var getMoviesJob: Job? = null
    var sharedPreference: SharedPreferences? = null

    fun getMovies() {
        getMoviesJob.cancelIfActive()
        getMoviesJob = viewModelScope.launch {
            getMovies(UseCase.None())
                .onStart { handleShowSpinner(true) }
                .onEach { handleShowSpinner(false) }
                .catch { failure -> handleFailure(failure) }
                .collect { result ->
                    when (result) {
                        is Success<PlanetsView> -> {
                            movies.value = getFavourites(result.data)
                        }
                        is Error -> {
                        }
                    }
                }
        }
    }

    private fun getFavourites(movies: PlanetsView) : PlanetsView{
        if (movies.results != null) {
            for (movie in movies.results) {
                if (movie.title.toString() == getValueString(movie.title.toString())) {
                    movie.favourite = true
                }
            }
        }
        return movies
    }
    fun getSahrePreferences(context: Context){
        sharedPreference = context.getSharedPreferences("pref", Context.MODE_PRIVATE)
    }

    fun getValueString(KEY_NAME: String): String? {
        return sharedPreference?.getString(KEY_NAME, null)
    }
}
