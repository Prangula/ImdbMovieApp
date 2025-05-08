package com.imdbmovieapp.presentation.screen.detail_movie_fragment.ui

import androidx.navigation.fragment.navArgs
import com.imdbmovieapp.databinding.FragmentDetailMovieBinding
import com.imdbmovieapp.presentation.base.BaseFragment
import com.imdbmovieapp.presentation.screen.detail_movie_fragment.vm.DetailViewModel
import com.imdbmovieapp.utils.view_extensions.getPosterUrl
import com.imdbmovieapp.utils.view_extensions.setImage
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
            detailArrowBackImageView.setOnClickListener {
                viewModel.navigateBack()
            }
        }
    }
}