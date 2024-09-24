package com.imdbmovieapp.utils

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.navigation.NavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.imdbmovieapp.R
import com.imdbmovieapp.databinding.CustomBottomNavigationBinding

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
                    navController.navigate(R.id.popularMoviesFragment)
                }

                R.id.favoritesFragment -> {
                    navController.navigate(R.id.favoriteMoviesFragment)
                }
            }
        }
    }
}