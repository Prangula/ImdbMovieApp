package com.imdbmovieapp.presentation.screen.home_movies_fragment.ui

import android.view.View
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.recyclerview.widget.GridLayoutManager
import com.imdbmovieapp.R
import com.imdbmovieapp.databinding.FragmentHomeMoviesBinding
import com.imdbmovieapp.presentation.base.BaseFragment
import com.imdbmovieapp.presentation.model.GenreMoviesUI
import com.imdbmovieapp.presentation.model.MoviesResultsUI
import com.imdbmovieapp.presentation.screen.home_movies_fragment.adapter.ResultsMoviesAdapter
import com.imdbmovieapp.presentation.screen.home_movies_fragment.vm.HomeViewModel
import com.imdbmovieapp.utils.resource.Resource
import com.imdbmovieapp.utils.lifecycle_scope_extensions.observe
import kotlin.reflect.KClass

class HomeMoviesFragment : BaseFragment<FragmentHomeMoviesBinding, HomeViewModel>(
    FragmentHomeMoviesBinding::inflate
) {
    override val viewModelClass: KClass<HomeViewModel> get() = HomeViewModel::class
    private lateinit var adapter: ResultsMoviesAdapter
    private var genreMoviesUI = GenreMoviesUI()

    override fun onBind() {
        setupPopularMoviesRecyclerView()
        viewModel.getGenreMovies()
        viewModel.getPopularMovies(requireContext())
        with(binding) {
            customSearchBar.getSearchMovies(
                viewModel::getSearchMovies,
                viewLifecycleOwner.lifecycleScope,
                homeGenresChipGroup,
                onClickAction = {
                    if (homeGenresChipGroup.checkedChipId == R.id.genrePopularChip) {
                        popularMoviesObserver()
                    } else {
                        topRatedMoviesObserver()
                    }
                }, homeNoMoviesTextview, homeNoImageView
            )
            customSearchBar.showGenreTags(homeGenresChipGroup)
            customSearchBar.hideKeyboard()
        }
        check()
        genreMoviesObserver()
        popularMoviesObserver()
        searchMoviesObserver()
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
        binding.homeGenresChipGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.genrePopularChip -> {
                    viewModel.getPopularMovies(requireContext())
                }

                R.id.genreTopRatedChip -> {
                    topRatedMoviesObserver()
                    viewModel.getTopRatedMovies(requireContext())
                }
            }
        }
    }

    private fun popularMoviesObserver() {
        observe(viewModel.popularMovies) { resource ->
            resource.data?.let { pagingData ->
                adapter.submitData(viewLifecycleOwner.lifecycle, pagingData)
            }
        }
    }

    private fun topRatedMoviesObserver() {
        observe(viewModel.topRatedMovies) { resource ->
            adapter.submitData(
                viewLifecycleOwner.lifecycle, resource.data
                    ?: PagingData.empty()
            )
        }
    }

    private fun searchMoviesObserver() {
        observe(viewModel.searchMovies) { resource ->
            adapter.submitData(
                viewLifecycleOwner.lifecycle, resource.data
                    ?: PagingData.empty()
            )
        }
    }

    private fun genreMoviesObserver() {
        observe(viewModel.getGenres) { resource ->
            when (resource) {
                is Resource.Success -> {
                    genreMoviesUI.genres = resource.data!!
                }

                is Resource.Error -> {
                    resource.message
                }

                is Resource.Loading -> {
                }
            }
        }
    }

    private fun navigateToMovieDetailsFragment(
        moviesResultsUI: MoviesResultsUI,
        genreMoviesUI: GenreMoviesUI
    ) {
        viewModel.navigateToDetailsFragment(moviesResultsUI, genreMoviesUI)
    }
}
