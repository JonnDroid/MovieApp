package com.example.movieapplication.util

import androidx.recyclerview.widget.DiffUtil
import com.example.movieapplication.data.model.Movie

class MovieListUtil(private val oldList: List<Movie>, private val newList: List<Movie>) :
    DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].title == newList[newItemPosition].title
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when {
            oldList[oldItemPosition].title != newList[newItemPosition].title -> {
                false
            }

            oldList[oldItemPosition].overview != newList[newItemPosition].overview -> {
                false
            }

            oldList[oldItemPosition].voteAverage != newList[newItemPosition].voteAverage -> {
                false
            }

            oldList[oldItemPosition].posterPath != newList[newItemPosition].posterPath -> {
                false
            }

            else -> true
        }
    }
}