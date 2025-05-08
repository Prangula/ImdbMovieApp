package com.imdbmovieapp.presentation.screen.homeMoviesFragment.ui

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.imdbmovieapp.R
import com.imdbmovieapp.databinding.FragmentHomeMoviesBinding
import com.imdbmovieapp.presentation.base.BaseFragment
import com.imdbmovieapp.presentation.model.GenreMoviesUI
import com.imdbmovieapp.presentation.model.MoviesResultsUI
import com.imdbmovieapp.presentation.screen.activity_screen.MoviesActivity
import com.imdbmovieapp.presentation.screen.detailMovieFragment.ui.DetailMovieFragment
import com.imdbmovieapp.presentation.screen.homeMoviesFragment.adapter.ResultsMoviesAdapter
import com.imdbmovieapp.presentation.screen.homeMoviesFragment.vm.HomeViewModel
import com.imdbmovieapp.utils.lifecycleScopeExtensions.observe
import com.imdbmovieapp.utils.viewExtensions.hide
import com.imdbmovieapp.utils.viewExtensions.show
import kotlin.reflect.KClass

class HomeMoviesFragment : BaseFragment<FragmentHomeMoviesBinding, HomeViewModel>(
    FragmentHomeMoviesBinding::inflate
) {
    override val viewModelClass: KClass<HomeViewModel> get() = HomeViewModel::class
    private lateinit var adapter: ResultsMoviesAdapter
    private var genreMoviesUI = GenreMoviesUI()

    override fun onBind() {
        setupPopularMoviesRecyclerView()
        popularMoviesObserver()
        genreMoviesObserver()
        viewModel.getPopularMovies(
            requireContext(), binding.homeNoConnectionImageView,
            binding.homeNoConnectionTextView, binding.homeErrorTextView, binding.homeErrorButton
        )
        viewModel.getGenreMovies(requireContext())
        with(binding) {
            customSearchBar.getSearchMovies(
                searchClickAction = { query, context ->
                    viewModel.getSearchMovies(query, context)
                },
                lifecycleScope = viewLifecycleOwner.lifecycleScope,
                chipGroup = homeGenresChipGroup,
                observer = {
                    searchMoviesObserver()
                }, homeNoMoviesTextview, homeNoImageView
            )
            customSearchBar.showGenreTags(binding.homeGenresChipGroup)
            customSearchBar.hideKeyboard()
            customSearchBar.clickCancel(onClickAction = {
                if (homeGenresChipGroup.checkedChipId == R.id.genrePopularChip) {
                    viewModel.getPopularMovies(
                        requireContext(), homeNoConnectionImageView,
                        homeNoConnectionTextView, homeErrorTextView, homeErrorButton
                    )
                } else {
                    viewModel.getTopRatedMovies(requireContext())
                }
            }, homeNoMoviesTextview, homeNoImageView)
        }
        check()
    }

    private fun setupPopularMoviesRecyclerView() {
        with(binding) {
            adapter = ResultsMoviesAdapter(
                genreMoviesUI,
                onViewClick = { item ->
                    navigateToMovieDetailsFragment(item, genreMoviesUI)
                },
                insertOnClick = { item ->
                    viewModel.insert(item)
                },
                deleteOnClick = { item ->
                    viewModel.deleteMovie(item)
                }
            )
            homeMoviesRecyclerView.adapter = adapter
            homeMoviesRecyclerView.layoutManager = GridLayoutManager(requireActivity(), 2)
        }
    }

    private fun check() {
        binding.homeGenresChipGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.genrePopularChip -> {
                    viewModel.getPopularMovies(
                        requireContext(),
                        binding.homeNoConnectionImageView,
                        binding.homeNoConnectionTextView,
                        binding.homeErrorTextView,
                        binding.homeErrorButton
                    )
                }

                R.id.genreTopRatedChip -> {
                    topRatedMoviesObserver()
                    viewModel.getTopRatedMovies(requireContext())
                }
            }
        }
    }

    private fun popularMoviesObserver() {
        observe(viewModel.popularMovie) { resource ->
            resource.movieList.let { results ->
                adapter.submitList(results)
            }
        }
    }

    private fun topRatedMoviesObserver() {
        observe(viewModel.topRatedMovies) { resource ->
            resource.movieList.let { results ->
                adapter.submitList(results)
            }
        }
    }

    private fun searchMoviesObserver() {
        observe(viewModel.searchMovies) { resource ->
            resource.movieList.let { results ->
                adapter.submitList(results)
                with(binding) {
                    if (results.isEmpty()) {
                        homeNoMoviesTextview.show()
                        homeNoImageView.show()
                        adapter.submitList(emptyList())
                    } else {
                        homeNoMoviesTextview.hide()
                        homeNoImageView.hide()
                        adapter.submitList(results)
                    }
                }
            }
        }
    }

    private fun genreMoviesObserver() {
        observe(viewModel.getGenres) { resource ->
            genreMoviesUI.genres = resource.genreList
        }
    }

    private fun navigateToMovieDetailsFragment(
        moviesResultsUI: MoviesResultsUI,
        genreMoviesUI: GenreMoviesUI
    ) {
        val bundle = Bundle().apply {
            putParcelable("moviesResults", moviesResultsUI)
            putParcelable("genreMovies", genreMoviesUI)
        }
        val detailMovieFragment = DetailMovieFragment().apply {
            arguments = bundle
        }
        (requireActivity() as MoviesActivity).binding.customBottomNav.hide()
        viewModel.navigateTo(detailMovieFragment)
    }
}