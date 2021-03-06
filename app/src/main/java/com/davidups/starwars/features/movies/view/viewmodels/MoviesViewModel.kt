package com.davidups.starwars.features.movies.view.viewmodels

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.davidups.starwars.core.extensions.cancelIfActive
import com.davidups.starwars.core.interactor.UseCase
import com.davidups.starwars.core.functional.Error
import com.davidups.starwars.core.functional.Success
import com.davidups.starwars.core.platform.BaseViewModel
import com.davidups.starwars.features.movies.models.view.MoviesView
import com.davidups.starwars.features.movies.usecases.GetMovies
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class MoviesViewModel(
    private val getMovies: GetMovies
) : BaseViewModel() {

    var movies = MutableLiveData<MoviesView>()
    private var getMoviesJob: Job? = null
    private var sharedPreference: SharedPreferences? = null

    fun getMovies() {
        getMoviesJob.cancelIfActive()
        getMoviesJob = viewModelScope.launch {
            getMovies(UseCase.None())
                .onStart { handleShowSpinner(true) }
                .onEach { handleShowSpinner(false) }
                .catch { failure -> handleFailure(failure) }
                .collect { result ->
                    when (result) {
                        is Success<MoviesView> -> {
                            movies.value = getFavourites(result.data)
                        }
                        is Error -> {
                        }
                    }
                }
        }
    }

    private fun getFavourites(movies: MoviesView): MoviesView {
        if (movies.results != null) {
            for (movie in movies.results) {
                if (movie.episodeId.toString() == getFavourite(movie.episodeId.toString())) {
                    movie.favourite = true
                }
            }
        }
        return movies
    }

    fun getSharePreferences(context: Context) {
        sharedPreference = context.getSharedPreferences("pref", Context.MODE_PRIVATE)
    }

    private fun getFavourite(KEY_NAME: String): String? {
        return sharedPreference?.getString(KEY_NAME, null)
    }
}
