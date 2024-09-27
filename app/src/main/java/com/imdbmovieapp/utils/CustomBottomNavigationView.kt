package com.imdbmovieapp.utils

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.imdbmovieapp.R
import com.imdbmovieapp.databinding.CustomBottomNavigationBinding
import com.imdbmovieapp.presentation.screen.detail_movie_fragment.ui.DetailMovieFragment

class CustomBottomNavigationView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : BottomNavigationView(context, attrs, defStyleAttr) {
    private val binding =
        CustomBottomNavigationBinding.inflate(LayoutInflater.from(context), this, true)

    operator fun invoke(navController: NavController) {
        binding.bottomChipGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.homeFragment -> {
                    navController.navigate(R.id.homeMoviesFragment)
                }

                R.id.favoritesFragment -> {
                    navController.navigate(R.id.favoriteMoviesFragment)
                }
            }
        }
    }
}