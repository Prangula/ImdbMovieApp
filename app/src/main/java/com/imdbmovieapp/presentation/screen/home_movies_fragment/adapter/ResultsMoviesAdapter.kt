package com.imdbmovieapp.presentation.screen.home_movies_fragment.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.imdbmovieapp.R
import com.imdbmovieapp.databinding.MovieItemBinding
import com.imdbmovieapp.presentation.model.GenreMoviesUI
import com.imdbmovieapp.presentation.model.MoviesResultsUI
import com.imdbmovieapp.utils.view_extensions.getPosterUrl
import com.imdbmovieapp.utils.view_extensions.setImage

class ResultsMoviesAdapter
    (private val genreMoviesUI: GenreMoviesUI) :
    ListAdapter<MoviesResultsUI, ResultsMoviesAdapter.ViewHolder>(DiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, genreMoviesUI)

        /*
        holder.itemView.setOnClickListener {
            onViewClick.invoke(item)
        }
         */
    }

    class DiffUtil : androidx.recyclerview.widget.DiffUtil.ItemCallback<MoviesResultsUI>() {
        override fun areItemsTheSame(oldItem: MoviesResultsUI, newItem: MoviesResultsUI): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: MoviesResultsUI,
            newItem: MoviesResultsUI
        ): Boolean {
            return oldItem == newItem
        }
    }

    class ViewHolder(private val binding: MovieItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(item: MoviesResultsUI, genreMoviesUI: GenreMoviesUI) {
            with(binding) {
                if(item.getPosterUrl().isNotEmpty()){
                    movieItemImageView.setImage(item.getPosterUrl())?: R.drawable.ic_empty
                }
                movieItemGenre.text =
                    genreMoviesUI.genres.find { it.id == item.genreIds.firstOrNull() }?.name
                        ?: "Unknown Genre"
                movieItemTitle.text = item.title?: ""
                movieItemYear.text = item.releaseDate.take(4)?:""
            }
        }
    }
}