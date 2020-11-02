package com.davidups.starwars.core.navigation

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.davidups.starwars.R
import kotlinx.android.synthetic.main.navigation_activity.*

class MainActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.navigation_activity)
        setSupportActionBar(toolbar)
        val navController = findNavController(R.id.fragment)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupWithNavController(bottom_nav, navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            toolbar.title = destination.label

            bottom_nav.visibility = when (destination.id) {
                else -> View.VISIBLE
            }
        }
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }
}
