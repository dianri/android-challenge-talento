package com.davidups.starwars.features.planets.view.fragments

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.davidups.starwars.R
import com.davidups.starwars.core.extensions.failure
import com.davidups.starwars.core.extensions.observe
import com.davidups.starwars.core.extensions.showInfoAlertDialog
import com.davidups.starwars.core.platform.BaseFragment
import com.davidups.starwars.core.platform.viewBinding.viewBinding
import com.davidups.starwars.databinding.FragmentMoviesBinding
import com.davidups.starwars.features.planets.models.view.PlanetsView
import com.davidups.starwars.features.planets.view.adapters.PlanetAdapter
import com.davidups.starwars.features.planets.view.viewmodels.PlanetsViewModel
import com.kotlinpermissions.notNull
import org.koin.android.ext.android.inject

class PlanetsListFragment : BaseFragment(R.layout.fragment_movies) {

    private val binding by viewBinding(FragmentMoviesBinding::bind)

    private val planetsViewModel: PlanetsViewModel by inject()
    private val planetAdapter: PlanetAdapter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        with(planetsViewModel) {
            observe(showSpinner, ::handleShowSpinner)
            observe(movies, ::handleMovies)
            failure(failure, ::handleFailure)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initListeners()

    }

    private fun initView() {
        planetsViewModel.getMovies()
        planetsViewModel.getSahrePreferences(requireActivity())
        binding.rvMovies.apply {
            layoutManager = GridLayoutManager(requireActivity(), 2)
            adapter = planetAdapter
        }
    }

    private fun initListeners() {}

    private fun handleMovies(movies: PlanetsView?) {
        movies.notNull { movies ->
            planetAdapter.collection = movies.results.orEmpty()
        }
    }
    private fun handleShowSpinner(show: Boolean?) {
        showSpinner(show ?: false)
    }

    private fun handleFailure(failure: Throwable?) {
        showInfoAlertDialog {
            setTitle(getString(R.string.common_error))
        }.show()
    }
}
