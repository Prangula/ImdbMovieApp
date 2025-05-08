package com.imdbmovieapp.utils

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.imdbmovieapp.R
import com.imdbmovieapp.databinding.CustomBottomNavigationBinding
import com.imdbmovieapp.utils.viewExtensions.hide

class CustomBottomNavigationView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : BottomNavigationView(context, attrs, defStyleAttr) {
    private val binding =
        CustomBottomNavigationBinding.inflate(LayoutInflater.from(context), this, true)

    operator fun invoke(viewPager: ViewPager) {
        binding.bottomChipGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.homeFragment -> {
                    viewPager.currentItem = 0
                }

                R.id.favoritesFragment -> {
                    viewPager.currentItem = 1
                }
            }
        }
        viewPager.addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {
            override fun onPageSelected(position: Int) {
                when (position) {
                    0 -> {
                        binding.homeFragment.isChecked = true
                        binding.favoritesFragment.isChecked = false
                    }

                    1 -> {
                        binding.homeFragment.isChecked = false
                        binding.favoritesFragment.isChecked = true
                    }
                }
            }
        })
    }
}