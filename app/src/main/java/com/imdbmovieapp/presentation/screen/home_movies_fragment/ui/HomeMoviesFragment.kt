package com.imdbmovieapp.presentation.screen.home_movies_fragment.ui

import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.imdbmovieapp.R
import com.imdbmovieapp.databinding.FragmentHomeMoviesBinding
import com.imdbmovieapp.presentation.base.BaseFragment
import com.imdbmovieapp.presentation.model.GenreMoviesUI
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
        adapter = ResultsMoviesAdapter(genreMoviesUI)
        popularMoviesObserver()
        genreMoviesObserver()
        searchMoviesObserver()
        viewModel.getPopularMovies()
        viewModel.getGenreMovies()
        popularMoviesRecyclerView()
        with(binding) {
            customSearchBar.showGenreTags(binding.homeGenresChipGroup)
            customSearchBar.getSearchMovies(
                viewModel::getSearchMovies,
                viewLifecycleOwner.lifecycleScope,
                homeGenresChipGroup
            )// { searchMoviesObserver() }
            customSearchBar.hideKeyboard()
            customSearchBar.clickCancel(onClickAction = {
                if (homeGenresChipGroup.checkedChipId == R.id.genrePopularChip) {
                    viewModel.getPopularMovies()
                } else {
                    viewModel.getTopRatedMovies()
                }
            })
        }
        check()
    }

    private fun popularMoviesRecyclerView() {
        with(binding) {
            homeMoviesRecyclerView.adapter = adapter
            homeMoviesRecyclerView.layoutManager = GridLayoutManager(requireActivity(), 2)
        }
    }

    private fun check() {
        binding.homeGenresChipGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.genrePopularChip -> {
                    viewModel.getPopularMovies()
                }

                R.id.genreTopRatedChip -> {
                    topRatedMoviesObserver()
                    viewModel.getTopRatedMovies()
                }
            }
        }
    }

    private fun popularMoviesObserver() {
        observe(viewModel.popularMovies) { resource ->
            when (resource) {
                is Resource.Success -> {
                    hideDialog()
                    adapter.submitList(resource.data!!)
                }

                is Resource.Error -> {
                    hideDialog()
                    resource.message
                }

                is Resource.Loading -> {
                    showDialog()
                }
            }
        }
    }

    private fun topRatedMoviesObserver() {
        observe(viewModel.topRatedMovies) { resource ->
            when (resource) {
                is Resource.Success -> {
                    hideDialog()
                    adapter.submitList(resource.data!!)
                }

                is Resource.Error -> {
                    resource.message
                    hideDialog()
                }

                is Resource.Loading -> {
                    showDialog()
                }
            }
        }
    }

    private fun searchMoviesObserver() {
        observe(viewModel.searchMovies) { resource ->
            when (resource) {
                is Resource.Success -> {
                    adapter.submitList(resource.data!!)
                }

                is Resource.Error -> {
                    resource.message
                }

                is Resource.Loading -> {
                }
            }
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
}