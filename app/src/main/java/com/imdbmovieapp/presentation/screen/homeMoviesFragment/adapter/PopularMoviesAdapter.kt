package com.imdbmovieapp.presentation.screen.homeMoviesFragment.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.imdbmovieapp.databinding.MovieItemBinding
import com.imdbmovieapp.presentation.model.MoviesResultsUI
import com.imdbmovieapp.utils.genreMapper

class PopularMoviesAdapter
    (
) : ListAdapter<MoviesResultsUI, PopularMoviesAdapter.ViewHolder>(DiffUtil()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, holder.itemView.context)

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

    class ViewHolder(val binding: MovieItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MoviesResultsUI, context: Context) {
            with(binding) {
                Glide
                    .with(context)
                    .load("https://image.tmdb.org/t/p/w500" + item.posterPath)
                    .into(movieItemImageView)
                movieItemGenre.text = genreMapper[item.genreIds[0]]
                movieItemTitle.text = item.title
                movieItemYear.text = item.releaseDate.take(4)
            }
        }
    }
}