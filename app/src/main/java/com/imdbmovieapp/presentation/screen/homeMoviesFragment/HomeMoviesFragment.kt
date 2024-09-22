package com.imdbmovieapp.presentation.screen.homeMoviesFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.imdbmovieapp.R
import com.imdbmovieapp.databinding.FragmentHomeMoviesBinding
import com.imdbmovieapp.utils.CustomSearchBar

class HomeMoviesFragment : Fragment(R.layout.fragment_home_movies) {
    private lateinit var binding: FragmentHomeMoviesBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeMoviesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.customSearchBar.showGenreTags()

    }
}