package com.imdbmovieapp.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.imdbmovieapp.presentation.screen.favoriteMoviesFragment.ui.FavoriteMoviesFragment
import com.imdbmovieapp.presentation.screen.homeMoviesFragment.ui.HomeMoviesFragment

class ViewPagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {
    override fun getCount(): Int {
        return 2
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> HomeMoviesFragment()
            1 -> FavoriteMoviesFragment()
            else -> HomeMoviesFragment()
        }
    }
}