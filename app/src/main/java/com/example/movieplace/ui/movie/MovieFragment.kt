package com.example.movieplace.ui.movie

import android.os.Bundle
import android.view.*
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movieplace.R
import com.example.movieplace.databinding.FragmentHomeBinding
import com.google.android.material.tabs.TabLayout
import com.example.movieplace.data.model.Movie
import com.example.movieplace.data.Result

class MovieFragment : Fragment() {

    private lateinit var movieViewModel: MovieViewModel
    private lateinit var movieAdapter: MyMoviesRecyclesViewAdapter
    private var _binding: FragmentHomeBinding? = null
    private var movies: List<Movie> = ArrayList()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val TAB_TITLES = arrayOf(
        "Top",
        "Recommend"
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        movieViewModel =
            ViewModelProvider(this)[MovieViewModel::class.java]

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        movieAdapter = MyMoviesRecyclesViewAdapter(context)
        val tabLayout = root.findViewById<TabLayout>(R.id.tab_layout)
        val progressBar = root.findViewById<ProgressBar>(R.id.progressBar)

        binding.listMovies.layoutManager = GridLayoutManager(context, 2)
        binding.listMovies.adapter = movieAdapter

        movieViewModel.getTopMovies().observe(
            viewLifecycleOwner,
            {
                if (it is Result.Success) {
                    movies = it.data
                    movieAdapter.setData(movies)
                    progressBar.visibility = GONE
                }
            }
        )


        tabLayout.addTab(tabLayout.newTab().setText(TAB_TITLES[0]), 0, true)
        tabLayout.addTab(tabLayout.newTab().setText(TAB_TITLES[1]), 1, false)

        tabLayout.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {

                if (tab?.text == TAB_TITLES[0]) {
                    movieAdapter.setData(ArrayList())
                    progressBar.visibility = VISIBLE
                    movieViewModel.getTopMovies().observe(
                        viewLifecycleOwner,
                        {
                            if (it is Result.Success) {
                                movies = it.data
                                movieAdapter.setData(movies)
                                progressBar.visibility = GONE
                            }
                        }
                    )
                } else {
                    movieAdapter.setData(ArrayList())
                    progressBar.visibility = VISIBLE
                    movieViewModel.getRecommendMovies().observe(
                        viewLifecycleOwner,
                        {
                            if (it is Result.Success) {
                                movies = it.data
                                movieAdapter.setData(movies)
                                progressBar.visibility = GONE
                            }
                        }
                    )
                }
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

