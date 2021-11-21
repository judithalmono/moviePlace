package com.example.movieplace.ui.scenes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieplace.R
import com.example.movieplace.data.Result
import com.example.movieplace.data.model.Movie
import com.example.movieplace.ui.home.MovieViewModel
import com.example.movieplace.ui.home.MyMoviesRecyclesViewAdapter
import com.google.gson.GsonBuilder
import android.content.Intent
import android.content.Intent.getIntent

class ScenesList: Fragment() {

    private lateinit var scenesViewModel: SceneViewModel
    private lateinit var scenesListAdapter: MyScenesRecyclesViewAdapter
    private lateinit var movie: Movie

    companion object {
        fun newInstance(movie: Movie) =
            ScenesList()
    }

    /**
     * Called to initialize the fragment and has the observers, returns the view inflated
     * @param inflater is the Layout inflater to inflate the view
     * @param container is the part which contains the view
     * @param savedInstanceState is the last saved instance of the view
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_listscenes, container, false)
    }

    /**
     * Called once the view is inflated and here is where we display the information and we initizalize other views
     * @param view is the view initialized by the onCreateView function
     * @param savedInstanceState is the last saved instance of the view
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        scenesViewModel = ViewModelProvider(this).get(SceneViewModel::class.java)
        scenesListAdapter = MyScenesRecyclesViewAdapter(context as ScenesActivity)

        val layout = view.findViewById<RecyclerView>(R.id.listActivities)
        layout.layoutManager = LinearLayoutManager(context)
        layout.adapter = scenesListAdapter

        movie = GsonBuilder().create().fromJson(arguments?.getString("movie").toString(), Movie::class.java)

        setHasOptionsMenu(true)

        scenesViewModel.getScenesByID((movie as Movie).id).observe(
            viewLifecycleOwner,
            {
                if (it is Result.Success) {
                    movies = it.data
                    movieAdapter.setData(movies)
                    progressBar.visibility = View.GONE
                }
            }
        )

    }

}