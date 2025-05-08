package com.imdbmovieapp.presentation.screen.activity_screen

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.fragment.NavHostFragment
import com.imdbmovieapp.R
import com.imdbmovieapp.databinding.ActivityMovieBinding

class MoviesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMovieBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        binding = ActivityMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupNavigation()
    }

    private fun setupNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        val navController = navHostFragment.navController
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.detailMovieFragment -> {

                    binding.customBottomNav.visibility = View.GONE

                }

                else -> {
                    binding.customBottomNav.visibility = View.VISIBLE
                }
            }
        }
        binding.customBottomNav.invoke(navController)
    }
}