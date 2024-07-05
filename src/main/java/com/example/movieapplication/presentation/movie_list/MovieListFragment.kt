package com.example.movieapplication.presentation.movie_list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapplication.data.model.Movie
import com.example.movieapplication.data.repository.MovieRepository
import com.example.movieapplication.data.repository.PrefRepository
import com.example.movieapplication.databinding.FragmentMovieListBinding

class MovieListFragment : Fragment(), MovieListAdapter.MovieListClicked {
    private val binding: FragmentMovieListBinding by lazy {
        FragmentMovieListBinding.inflate(layoutInflater)
    }
    private val movieListAdapter: MovieListAdapter by lazy {
        MovieListAdapter(this)
    }
    private val viewModel: MovieListViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[MovieListViewModel::class.java]
    }
    private val viewModelFactory: MovieListViewModelFactory by lazy {
        MovieListViewModelFactory(movieRepo, prefRepository)
    }
    private val movieRepo: MovieRepository by lazy {
        MovieRepository()
    }
    private val prefRepository by lazy {
        PrefRepository(requireContext())
    }
    private lateinit var movieData: List<Movie>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setupMovieList()

        viewModel.hasError.observe(viewLifecycleOwner) {error ->
            if (error) {
                binding.movieListContainer.visibility = View.GONE
                binding.errorContainer.visibility = View.VISIBLE
                binding.errorContainer.bringToFront()
                binding.linearProgressBar.isIndeterminate = false
            }
            else {
                binding.movieListContainer.visibility = View.VISIBLE
                binding.errorContainer.visibility = View.GONE
                binding.movieListContainer.bringToFront()
            }
        }

        viewModel.movieList.observe(viewLifecycleOwner) { movieList ->
            movieListAdapter.setData(movieList)
            movieData = movieList
        }

        viewModel.isLoading.observe(viewLifecycleOwner){
            binding.swipeRefreshLayout.isRefreshing = it
        }

        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.getMovieData()
        }

        return binding.root
    }

    private fun setupMovieList() {
        binding.movieListContainer.adapter = movieListAdapter
        binding.movieListContainer.layoutManager = LinearLayoutManager(context)
    }

    override fun onItemClick(position: Int) {
        val moviePosition = movieData[position]
        val movieDetails = Movie(
            moviePosition.title,
            moviePosition.overview,
            moviePosition.voteAverage,
            moviePosition.posterPath
        )
        val action = MovieListFragmentDirections.navigateToMovieDetails(movieDetails)
        findNavController().navigate(action)
    }


}