package com.example.movieplace.ui.home

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.movieplace.R
import com.example.movieplace.databinding.FragmentHomeBinding
import com.google.android.material.tabs.TabLayout
import com.example.movieplace.data.model.Movie
import com.example.movieplace.data.Result
import com.google.android.material.tabs.TabItem
import com.google.android.material.tabs.TabLayoutMediator


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
        toolbar = root.findViewById(R.id.toolbar)
        val tabLayout = root.findViewById<TabLayout>(R.id.tab_layout)

        binding.listMovies.adapter = movieAdapter

        tabLayout.addTab(tabLayout.newTab().setText(TAB_TITLES[0]), 0, true)
        tabLayout.addTab(tabLayout.newTab().setText(TAB_TITLES[1]), 1, false)

        tabLayout.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {

                if (tab?.text == TAB_TITLES[0]) {
                    movieViewModel.getTopMovies()
                    Log.d("LEGEN", movieViewModel.movies.value.toString())
                } else {
                    movieViewModel.getRecommendMovies()
                    Log.d("DARIO", movieViewModel.movies.value.toString())
                }
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })

        movieViewModel.movies.observe(
            viewLifecycleOwner,
            {
                Log.d("DARIO", movieViewModel.movies.value.toString())
                if (it is Result.Success) {
                    movies = it.data
                    movieAdapter.setData(movies)
                }
            }
        )

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

