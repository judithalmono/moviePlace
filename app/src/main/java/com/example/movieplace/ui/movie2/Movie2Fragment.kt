package com.example.movieplace.ui.movie2

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movieplace.R
import com.example.movieplace.data.Result
import com.example.movieplace.data.model.Movie
import com.example.movieplace.databinding.FragmentHome2Binding
import com.example.movieplace.ui.movie.MyMoviesRecyclesViewAdapter
import com.google.android.material.tabs.TabLayout

class Movie2Fragment : Fragment() {

    private lateinit var movie2ViewModel: Movie2ViewModel
    private lateinit var movie2Adapter: MyMovie2RecyclesViewAdapter
    private var _binding: FragmentHome2Binding? = null
    private var movies: List<Movie> = ArrayList()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        movie2ViewModel =
            ViewModelProvider(this)[Movie2ViewModel::class.java]

        _binding = FragmentHome2Binding.inflate(inflater, container, false)
        val root: View = binding.root

        movie2Adapter = MyMovie2RecyclesViewAdapter(context)
        val progressBar = root.findViewById<ProgressBar>(R.id.progressBar)
        val filter = root.findViewById<Button>(R.id.filterBtn)

        binding.listMovies.layoutManager = GridLayoutManager(context, 2)
        binding.listMovies.adapter = movie2Adapter

        filter.setOnClickListener {
            pickOption()
        }

//        movie2ViewModel.getMovies().observe(
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

    private fun pickOption() {
        val customDialog = Dialog(requireActivity())
        customDialog.setContentView(R.layout.alert_dialog)
        val yesBtn = customDialog.findViewById<Button>(R.id.actorBtn)
//        val noBtn = customDialog.findViewById(R.id.no_opt) as TextView
        yesBtn.setOnClickListener {
            //Do something here
            customDialog.dismiss()
        }/*
        noBtn.setOnClickListener {
            customDialog.dismiss()
        }*/
        customDialog.show()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}