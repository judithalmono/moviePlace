package com.example.movieplace.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.movieplace.R
import com.example.movieplace.databinding.FragmentHomeBinding
import com.google.android.material.tabs.TabLayout
import com.example.movieplace.data.model.Movie
import com.example.movieplace.data.Result





class MovieFragment : Fragment() {

    private lateinit var movieViewModel: MovieViewModel
    private lateinit var movieAdapter: MyMoviesRecyclesViewAdapter
    lateinit var toolbar: Toolbar
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
            ViewModelProvider(this).get(MovieViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        movieAdapter = MyMoviesRecyclesViewAdapter(context)
        val tabLayout = root.findViewById<TabLayout>(R.id.tab_layout)
        val listMovies = root.findViewById<RecyclerView>(R.id.listMovies)

        binding.listMovies.layoutManager = GridLayoutManager(context, 2)
        binding.listMovies.adapter = movieAdapter

        movieViewModel.getTopMovies().observe(
            viewLifecycleOwner,
            {
                if (it is Result.Success) {
                    movies = it.data
                    movieAdapter.setData(movies)
                }
            }
        )

        tabLayout.addTab(tabLayout.newTab().setText(TAB_TITLES[0]), 0, true)
        tabLayout.addTab(tabLayout.newTab().setText(TAB_TITLES[1]), 1, false)

        tabLayout.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
            @SuppressLint("ResourceAsColor")
            override fun onTabSelected(tab: TabLayout.Tab?) {

                if (tab?.text == TAB_TITLES[0]) {
                    movieViewModel.getTopMovies().observe(
                        viewLifecycleOwner,
                        {
                            if (it is Result.Success) {
                                movies = it.data
                                movieAdapter.setData(movies)
                            }
                        }
                    )
                } else {
                    movieViewModel.getRecommendMovies().observe(
                        viewLifecycleOwner,
                        {
                            if (it is Result.Success) {
                                movies = it.data
                                movieAdapter.setData(movies)
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

