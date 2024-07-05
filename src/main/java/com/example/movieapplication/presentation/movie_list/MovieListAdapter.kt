package com.example.movieapplication.presentation.movie_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapplication.R
import com.example.movieapplication.data.model.Movie
import com.example.movieapplication.databinding.ItemMovieCardBinding
import com.example.movieapplication.util.GlideUtil.loadImage
import com.example.movieapplication.util.MovieListUtil

class MovieListAdapter(private var listener: MovieListClicked) :
    RecyclerView.Adapter<MovieListAdapter.MovieViewHolder>() {
    private var movieList = emptyList<Movie>()

    interface MovieListClicked {
        fun onItemClick(position: Int)
    }

    inner class MovieViewHolder(val binding: ItemMovieCardBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        init {
            binding.root.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            ItemMovieCardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.binding.movieListTitleTxt.text = movieList[position].title
        holder.binding.movieListRatingsTxt.text = holder.itemView.context.getString(
            R.string.movie_rating,
            movieList[position].voteAverage.toString()
        )
        loadImage(
            holder.itemView.context,
            movieList[position].posterPath,
            holder.binding.movieListPosterImage
        )
    }

    fun setData(newMovieList: List<Movie>) {
        val diffUtil = MovieListUtil(movieList, newMovieList)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        movieList = newMovieList
        diffResult.dispatchUpdatesTo(this)
    }
}