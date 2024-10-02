package com.imdbmovieapp.presentation.screen.home_movies_fragment.ui

import android.view.View
import androidx.lifecycle.lifecycleScope
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
        viewModel.getPopularMovies()
        popularMoviesObserver()
        with(binding) {
            customSearchBar.getSearchMovies(
                viewModel::getSearchMovies,
                viewLifecycleOwner.lifecycleScope,
                homeGenresChipGroup,
            ) { searchMoviesObserver() }
            customSearchBar.showGenreTags(homeGenresChipGroup)
            customSearchBar.hideKeyboard()
            customSearchBar.clickCancel(onClickAction = {
                if (homeGenresChipGroup.checkedChipId == R.id.genrePopularChip) {
                    viewModel.getPopularMovies()
                } else {
                    viewModel.getTopRatedMovies()
                }
            }, homeNoMoviesTextview, homeNoImageView)
        }
        check()
        genreMoviesObserver()
    }

    private fun setupPopularMoviesRecyclerView() {
        with(binding) {
            adapter = ResultsMoviesAdapter(
                genreMoviesUI,
                onViewClick = { item ->
//                    navigateToMovieDetailsFragment(item, genreMoviesUI)
                },
                insertOnClick = { item ->
                    viewModel.insert(item)
                    item.isFavorite = true
                },
                deleteOnClick = { item ->
                    viewModel.deleteMovie(item)
                    item.isFavorite = false
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
                    viewModel.getPopularMovies()
                    popularMoviesObserver()
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
                    adapter.submitData(viewLifecycleOwner.lifecycle, resource.data!!)
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

    private fun topRatedMoviesObserver() {
        observe(viewModel.topRatedMovies) { resource ->
            when (resource) {
                is Resource.Success -> {
                    hideDialog()
                    adapter.submitData(viewLifecycleOwner.lifecycle, resource.data!!)
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
                    with(binding) {
                        adapter.submitData(
                            viewLifecycleOwner.lifecycle,
                            resource.data!!
                        )

                        homeNoMoviesTextview.visibility = View.VISIBLE
                        homeNoImageView.visibility = View.VISIBLE
                        adapter.submitData(viewLifecycleOwner.lifecycle, PagingData.empty())
                    }
                }

                is Resource.Error -> {
                    resource.message?.let { errorMessage ->
                        resource.message
                    }
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

    private fun navigateToMovieDetailsFragment(
        moviesResultsUI: MoviesResultsUI,
        genreMoviesUI: GenreMoviesUI
    ) {
        viewModel.navigateToDetailsFragment(moviesResultsUI, genreMoviesUI)
    }
}
