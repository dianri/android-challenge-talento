package com.davidups.starwars.features.planets.view.fragments

import android.os.Bundle
import android.view.View
import com.davidups.starwars.R
import com.davidups.starwars.core.extensions.loadFromUrl
import com.davidups.starwars.core.extensions.randomImage
import com.davidups.starwars.core.platform.BaseFragment
import com.davidups.starwars.features.planets.models.view.PlanetView
import kotlinx.android.synthetic.main.fragment_detail_movie.*


class PlanetDetailFragment : BaseFragment(R.layout.fragment_detail_movie) {
    var episodeInfo: PlanetView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        episodeInfo = arguments?.getSerializable("episodeInfo") as PlanetView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    private fun initView() {
        ivBanner.loadFromUrl(String.randomImage())
        tvName.text = episodeInfo?.title
        tvReleaseDate.text = episodeInfo?.releaseDate
        tvOpeningCrawl.text = episodeInfo?.openingCrawl
        tvProducer.text = episodeInfo?.producer
    }
}
