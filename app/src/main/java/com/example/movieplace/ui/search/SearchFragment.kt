package com.example.movieplace.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.SearchView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movieplace.R
import com.example.movieplace.data.Result
import com.example.movieplace.data.model.Movie
import com.example.movieplace.databinding.FragmentHomeBinding
import com.example.movieplace.databinding.FragmentSearchBinding
import com.example.movieplace.ui.home.MovieViewModel
import com.example.movieplace.ui.home.MyMoviesRecyclesViewAdapter
import com.google.android.material.tabs.TabLayout

class SearchFragment : Fragment() {

    private lateinit var searchViewModel: SearchViewModel
    private lateinit var movieAdapter: MyMoviesRecyclesViewAdapter
    private var _binding: FragmentSearchBinding? = null
    private var movies: List<Movie> = ArrayList()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        searchViewModel =
            ViewModelProvider(this)[SearchViewModel::class.java]

        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        val root: View = binding.root

        movieAdapter = MyMoviesRecyclesViewAdapter(context)
        val progressBar = root.findViewById<ProgressBar>(R.id.progressBar)
        val searchView = root.findViewById<SearchView>(R.id.search)

        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                movieAdapter.performFiltering(newText)
                return false
            }
        })

//        binding.search.layoutManager = GridLayoutManager(context, 2)
//        binding.search.adapter = movieAdapter

//        searchViewModel.getTopMovies().observe(
//            viewLifecycleOwner,
//            {
//                if (it is Result.Success) {
//                    movies = it.data
//                    movieAdapter.setData(movies)
//                    progressBar.visibility = View.GONE
//                }
//            }
//        )
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}