package com.example.movieapplication.presentation.movie_details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.movieapplication.R
import com.example.movieapplication.databinding.FragmentMovieDetailsBinding
import androidx.appcompat.app.AppCompatActivity
import com.example.movieapplication.util.GlideUtil.loadImage

class MovieDetailsFragment : Fragment() {
    private val args by navArgs<MovieDetailsFragmentArgs>()
    private val binding: FragmentMovieDetailsBinding by lazy {
        FragmentMovieDetailsBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding.movieTitleTxt.text = args.movieDetails.title
        binding.movieDescTxt.text = args.movieDetails.overview
        binding.movieRatingTxt.text =
            getString(R.string.movie_rating, args.movieDetails.voteAverage.toString())
        loadImage(requireContext(), args.movieDetails.posterPath, binding.movieImg)

        (activity as? AppCompatActivity)?.supportActionBar?.title = args.movieDetails.title

        return binding.root
    }


}