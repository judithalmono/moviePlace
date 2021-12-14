package com.example.movieplace.ui.movie2

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieplace.R
import com.example.movieplace.data.Result
import com.example.movieplace.data.model.Movie
import com.example.movieplace.databinding.FragmentHome2Binding
import com.example.movieplace.ui.movie.MyMoviesRecyclesViewAdapter
import java.util.*
import kotlin.collections.ArrayList





class Movie2Fragment : Fragment() {

    private lateinit var movie2ViewModel: Movie2ViewModel
    private lateinit var movie2Adapter: MyMoviesRecyclesViewAdapter
    private var _binding: FragmentHome2Binding? = null
    private var movies: List<Movie> = ArrayList()
    private lateinit var loading: ProgressBar
    private lateinit var editText: EditText
    private lateinit var noFoundText: TextView
    private lateinit var listMoviesFilter: RecyclerView
    private lateinit var done: Button
    private var bGenre = true
    private var bDirector = false
    private var bActor = false
    private var bDate = false
    private lateinit var customDialog: Dialog


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

        movie2Adapter = MyMoviesRecyclesViewAdapter(context)
        loading = root.findViewById<ProgressBar>(R.id.progressBar)
        editText = root.findViewById(R.id.editText)
        listMoviesFilter = root.findViewById<RecyclerView>(R.id.listMoviesFilter)
        done = root.findViewById(R.id.done)
        noFoundText = root.findViewById<TextView>(R.id.noFoundText)
        val filter = root.findViewById<Button>(R.id.filterBtn)

        binding.listMoviesFilter.layoutManager = GridLayoutManager(context, 2)
        binding.listMoviesFilter.adapter = movie2Adapter

        customDialog = Dialog(requireActivity())
        customDialog.setContentView(R.layout.alert_dialog)

        listMoviesFilter.setOnClickListener {
            Log.d("ENTRO", "Aqui")
            if (activity?.currentFocus != null){
                val inputMethodManager = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(requireActivity().currentFocus?.windowToken, 0)
            }
            root.clearFocus()
        }

        filter.setOnClickListener {
            pickOption()
        }
        done.setOnClickListener {
            noFoundText.visibility = GONE
            loading.visibility = VISIBLE
            var text = ""
            if (editText.text.toString().isNotEmpty())
                text = capitaliseOnlyFirstLetter(editText.text.toString())
            Log.d("TEXT", text)
            if (bGenre) {
                Log.d("SOY", "genre")
                movie2ViewModel.getMoviesByGenre(text).observe(
                    viewLifecycleOwner, {
                        if (it is Result.Success) {
                            movies = it.data
                            movie2Adapter.setData(movies)
                            loading.visibility = View.GONE
                            if (movies.isEmpty()) {
                                Log.d("ENTRO", "movies Empty")
                                noFoundText.visibility = VISIBLE

                            }
                        }
                    }
                )
            } else if (bDirector) {
                Log.d("SOY", "director")
                movie2ViewModel.getMoviesByDirector(text).observe(
                    viewLifecycleOwner, {
                        if (it is Result.Success) {
                            movies = it.data
                            movie2Adapter.setData(movies)
                            loading.visibility = View.GONE
                            if (movies.isEmpty()) {
                                Log.d("ENTRO", "movies Empty")
                                noFoundText.visibility = VISIBLE

                            }
                        }
                    }
                )

            } else if (bActor) {
                Log.d("SOY", "actor")
                movie2ViewModel.getMoviesByActor(text).observe(
                    viewLifecycleOwner, {
                        if (it is Result.Success) {
                            movies = it.data
                            movie2Adapter.setData(movies)
                            loading.visibility = View.GONE
                            if (movies.isEmpty()) {
                                Log.d("ENTRO", "movies Empty")
                                noFoundText.visibility = VISIBLE

                            }
                        }
                    }
                )
            } else if (bDate) {
                Log.d("SOY", "composer")
                movie2ViewModel.getMoviesByDate(text).observe(
                    viewLifecycleOwner, {
                        if (it is Result.Success) {
                            movies = it.data
                            movie2Adapter.setData(movies)
                            loading.visibility = View.GONE
                            if (movies.isEmpty()) {
                                Log.d("ENTRO", "movies Empty")
                                noFoundText.visibility = VISIBLE

                            }
                        }
                    }
                )
            }

        }

        return root
    }

    private fun pickOption() {

        val genreBtn = customDialog.findViewById<TextView>(R.id.genreBtn)
        val directorBtn = customDialog.findViewById<TextView>(R.id.directorBtn)
        val actorBtn = customDialog.findViewById<TextView>(R.id.actorBtn)
        val dateBtn = customDialog.findViewById<TextView>(R.id.dateBtn)

        genreBtn.setOnClickListener {

            genreBtn.setBackgroundResource(R.color.red)
            directorBtn.setBackgroundResource(R.color.white)
            actorBtn.setBackgroundResource(R.color.white)
            dateBtn.setBackgroundResource(R.color.white)
            customDialog.dismiss()

            bGenre = true
            bDirector = false
            bActor = false
            bDate = false

        }
        directorBtn.setOnClickListener {

            genreBtn.setBackgroundResource(R.color.white)
            directorBtn.setBackgroundResource(R.color.red)
            actorBtn.setBackgroundResource(R.color.white)
            dateBtn.setBackgroundResource(R.color.white)
            customDialog.dismiss()

            bGenre = false
            bDirector = true
            bActor = false
            bDate = false
        }
        actorBtn.setOnClickListener {

            genreBtn.setBackgroundResource(R.color.white)
            directorBtn.setBackgroundResource(R.color.white)
            actorBtn.setBackgroundResource(R.color.red)
            dateBtn.setBackgroundResource(R.color.white)
            customDialog.dismiss()

            bGenre = false
            bDirector = false
            bActor = true
            bDate = false
        }
        dateBtn.setOnClickListener {

            genreBtn.setBackgroundResource(R.color.white)
            directorBtn.setBackgroundResource(R.color.white)
            actorBtn.setBackgroundResource(R.color.white)
            dateBtn.setBackgroundResource(R.color.red)
            customDialog.dismiss()

            bGenre = false
            bDirector = false
            bActor = false
            bDate = true
        }

        customDialog.show()

    }

    private fun capitaliseOnlyFirstLetter(data: String): String {
        return data.substring(0, 1).uppercase(Locale.getDefault()) + data.substring(1)
    }
}