package com.imdbmovieapp.presentation.screen.activity_screen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.imdbmovieapp.databinding.ActivityMovieBinding
import com.imdbmovieapp.utils.ViewPagerAdapter
import com.imdbmovieapp.utils.viewExtensions.hide
import com.imdbmovieapp.utils.viewExtensions.show

class MoviesActivity : AppCompatActivity() {
    lateinit var binding: ActivityMovieBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        binding = ActivityMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupNavigation()
    }

    private fun setupNavigation() {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        binding.viewPager.adapter = adapter
        binding.customBottomNav.invoke(binding.viewPager)
    }
}