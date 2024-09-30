package com.imdbmovieapp.presentation.screen.favorite_movies_fragment.ui

import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.imdbmovieapp.databinding.FragmentFavoriteMoviesBinding
import com.imdbmovieapp.presentation.base.BaseFragment
import com.imdbmovieapp.presentation.model.GenreMoviesUI
import com.imdbmovieapp.presentation.model.MoviesResultsUI
import com.imdbmovieapp.presentation.screen.favorite_movies_fragment.vm.FavoriteMoviesViewModel
import com.imdbmovieapp.presentation.screen.home_movies_fragment.adapter.ResultsMoviesAdapter
import com.imdbmovieapp.utils.lifecycle_scope_extensions.observe
import com.imdbmovieapp.utils.resource.Resource
import kotlin.reflect.KClass

class FavoriteMoviesFragment : BaseFragment<FragmentFavoriteMoviesBinding, FavoriteMoviesViewModel>(
    FragmentFavoriteMoviesBinding::inflate
) {
    override val viewModelClass: KClass<FavoriteMoviesViewModel> get() = FavoriteMoviesViewModel::class
    private lateinit var adapter: ResultsMoviesAdapter
    private var genreMoviesUI = GenreMoviesUI()
    override fun onBind() {
        viewModel.getFavoriteMovies()
        genreMoviesObserver()
        favoriteMoviesRecyclerView()
        viewModel.getGenreMovies()
        observer()
    }

    private fun favoriteMoviesRecyclerView() {
        with(binding) {
            adapter = ResultsMoviesAdapter(
                genreMoviesUI,
                onViewClick = { item ->
                    navigateToMovieDetailsFragment(item, genreMoviesUI)
                },
                insertOnClick = {

                },
                deleteOnClick = { item ->
                    viewModel.deleteMovie(item)
                }
            )
            favoritesMoviesRecyclerView.adapter = adapter
            favoritesMoviesRecyclerView.layoutManager = GridLayoutManager(requireActivity(), 2)
        }
    }

    private fun navigateToMovieDetailsFragment(
        moviesResultsUI: MoviesResultsUI,
        genreMoviesUI: GenreMoviesUI
    ) {
        viewModel.navigateToDetailsFragment(moviesResultsUI, genreMoviesUI)
    }

    private fun observer() {
        with(binding) {
            observe(viewModel.favoriteMovies) { item ->
                adapter.submitList(item)
            }
            observe(viewModel.emptyMovies) { emptyMovies ->
                if (emptyMovies) {
                    favoritesMoviesRecyclerView.visibility = View.GONE
                    favoritesNoMoviesTextview.visibility = View.VISIBLE
                    favoritesNoImageView.visibility = View.VISIBLE
                } else {
                    favoritesNoMoviesTextview.visibility = View.GONE
                    favoritesNoImageView.visibility = View.GONE
                    favoritesMoviesRecyclerView.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun genreMoviesObserver() {
        observe(viewModel.getGenres) { resource ->
            when (resource) {
                is Resource.Success -> {
                    genreMoviesUI.genres = resource.data!!
                    hideDialog()
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
}