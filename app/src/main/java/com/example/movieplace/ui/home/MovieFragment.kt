package com.example.movieplace.ui.home

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
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

        val view = inflater.inflate(R.layout.fragment_movie, container, false)
        val root: View = binding.root

        movieAdapter = MyMoviesRecyclesViewAdapter(context)
        toolbar = root.findViewById(R.id.toolbar)
        val tabLayout = root.findViewById<TabLayout>(R.id.tab_layout)

        if(view is RecyclerView) {
            with(view) {
                adapter = movieAdapter
            }
        }

        tabLayout.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {

                movieAdapter.setData(ArrayList())

                if (tab?.text == TAB_TITLES[0]) {
                    movieViewModel.getTopMovies()
                } else {
                    movieViewModel.getRecommendMovies()
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
                if (it is Result.Success) {
                    movies = it.data
                    movieAdapter.setData(movies)
                }
            }
        )

//        val viewPager = root.findViewById<ViewPager2>(R.id.pager)

//        viewPager.adapter = ViewPagerAdapter(activity as AppCompatActivity)

//        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
//            tab.text = TAB_TITLES[position]
//        }.attach()

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

