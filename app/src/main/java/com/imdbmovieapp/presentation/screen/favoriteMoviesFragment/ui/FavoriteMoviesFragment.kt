package com.imdbmovieapp.presentation.screen.favoriteMoviesFragment.ui

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.imdbmovieapp.databinding.FragmentFavoriteMoviesBinding
import com.imdbmovieapp.presentation.base.BaseFragment
import com.imdbmovieapp.presentation.model.GenreMoviesUI
import com.imdbmovieapp.presentation.model.MoviesResultsUI
import com.imdbmovieapp.presentation.screen.activity_screen.MoviesActivity
import com.imdbmovieapp.presentation.screen.detailMovieFragment.ui.DetailMovieFragment
import com.imdbmovieapp.presentation.screen.favoriteMoviesFragment.vm.FavoriteMoviesViewModel
import com.imdbmovieapp.presentation.screen.homeMoviesFragment.adapter.ResultsMoviesAdapter
import com.imdbmovieapp.utils.lifecycleScopeExtensions.observe
import com.imdbmovieapp.utils.viewExtensions.hide
import com.imdbmovieapp.utils.viewExtensions.show
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
        viewModel.getGenreMovies(requireContext())
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
            genreMoviesUI.genres = resource.genreList
        }
    }
}