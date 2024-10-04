package com.imdbmovieapp.presentation.screen.detailMovieFragment.ui

import androidx.navigation.fragment.navArgs
import com.imdbmovieapp.R
import com.imdbmovieapp.databinding.FragmentDetailMovieBinding
import com.imdbmovieapp.presentation.base.BaseFragment
import com.imdbmovieapp.presentation.screen.detailMovieFragment.vm.DetailViewModel
import com.imdbmovieapp.utils.viewExtensions.getPosterUrl
import com.imdbmovieapp.utils.viewExtensions.setImage
import kotlin.reflect.KClass

class DetailMovieFragment : BaseFragment<FragmentDetailMovieBinding, DetailViewModel>(
    FragmentDetailMovieBinding::inflate
) {
    override val viewModelClass: KClass<DetailViewModel> get() = DetailViewModel::class
    private val args: DetailMovieFragmentArgs by navArgs()

    override fun onBind() {
        setUpUI()

    }

    private fun setUpUI() {
        val movieDetails = args.detailResults
        val genreDetails = args.genreResults
        with(binding) {
            detailImageView.setImage(movieDetails.getPosterUrl())
            detailTitleTextView.text = movieDetails.title
            genreChip.text =
                genreDetails.genres.find { it.id == movieDetails.genreIds.firstOrNull() }?.name
            ratingChip.text = movieDetails.voteAverage.toString().take(3)
            releasedChip.text = movieDetails.releaseDate.take(4)
            detailDescriptionTextview.text = movieDetails.overview
            detailHeart.setBackgroundResource(movieDetails.heartColor)

            detailHeart.setOnClickListener {
                if (movieDetails.heartColor == R.drawable.ic_colored_heart) {
                    movieDetails.isFavorite = false
                    movieDetails.heartColor = R.drawable.ic_uncolored_heart
                    viewModel.deleteMovie(movieDetails)
                } else {
                    movieDetails.isFavorite = true
                    movieDetails.heartColor = R.drawable.ic_colored_heart
                    viewModel.insert(movieDetails)
                }
                detailHeart.setBackgroundResource(movieDetails.heartColor)
            }
            detailArrowBackImageView.setOnClickListener {
                viewModel.navigateBack()
            }
        }
    }
}