package com.imdbmovieapp.presentation.screen.favorite_movies_fragment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.imdbmovieapp.R
import com.imdbmovieapp.databinding.MovieItemBinding
import com.imdbmovieapp.presentation.model.GenreMoviesUI
import com.imdbmovieapp.presentation.model.MoviesResultsUI
import com.imdbmovieapp.utils.view_extensions.getPosterUrl
import com.imdbmovieapp.utils.view_extensions.setImage

class FavAndSearchMoviesAdapter(
    private val genreMoviesUI: GenreMoviesUI,
    private val onViewClick: (item: MoviesResultsUI) -> Unit,
    private val insertOnClick: (item: MoviesResultsUI) -> Unit,
    private val deleteOnClick: (item: MoviesResultsUI) -> Unit
) : ListAdapter<MoviesResultsUI, FavAndSearchMoviesAdapter.ViewHolder>(DiffUtilCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(
            item!!,
            genreMoviesUI,
            insertOnClick,
            deleteOnClick
        )
        holder.itemView.setOnClickListener {
            onViewClick.invoke(item)
        }
    }

    class DiffUtilCallBack : DiffUtil.ItemCallback<MoviesResultsUI>() {
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
        fun bind(
            item: MoviesResultsUI,
            genreMoviesUI: GenreMoviesUI,
            insertOnClick: (item: MoviesResultsUI) -> Unit,
            deleteOnClick: (item: MoviesResultsUI) -> Unit
        ) {
            with(binding) {
                movieItemImageView.setImage(item.getPosterUrl())
                movieItemGenre.text =
                    genreMoviesUI.genres.find { it.id == item.genreIds.firstOrNull() }?.name
                        ?: "Unknown Genre"
                movieItemTitle.text = item.title
                movieItemYear.text = item.releaseDate.take(4)

                item.heartColor =
                    if (item.isFavorite) R.drawable.ic_colored_heart else R.drawable.ic_uncolored_heart
                movieItemHeart.setBackgroundResource(item.heartColor)
                movieItemHeart.setOnClickListener {
                    if (!item.isFavorite) {
                        item.heartColor = R.drawable.ic_colored_heart
                        insertOnClick(item)
                    } else {
                        item.heartColor = R.drawable.ic_uncolored_heart
                        deleteOnClick(item)
                    }
                    item.isFavorite = !item.isFavorite
                    movieItemHeart.setBackgroundResource(item.heartColor)
                }
            }
        }
    }
}
