package com.example.movieplace.ui.scenes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieplace.R
import com.example.movieplace.data.Result
import android.util.Log
import androidx.core.os.bundleOf
import com.example.movieplace.data.model.Scene

class ScenesList: Fragment() {

    private lateinit var scenesViewModel: SceneViewModel
    private lateinit var scenesListAdapter: MyScenesRecyclesViewAdapter
    private var idmovie: Int = 0
    private var scenes: List<Scene> = ArrayList()

    companion object {
        fun newInstance(id_movie: Int) =
            ScenesList().apply {
                arguments = bundleOf("movie_id" to id_movie)
            }
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

        val layout = view.findViewById<RecyclerView>(R.id.listScenes)
        layout.layoutManager = LinearLayoutManager(context)
        layout.adapter = scenesListAdapter

        idmovie = (requireArguments().get("movie_id") as Int?)!!
        Log.d("MOVIE", idmovie.toString())
        setHasOptionsMenu(true)

        val scene = Scene("4558",
            arrayListOf("Christian Bale"), "4558", "Calle Provenza, 185, 08036 Barcelona", ArrayList(), "")
//                    scenes = it.data
        scenes = arrayListOf(scene)
        scenesListAdapter.setData(scenes)

//        scenesViewModel.getScenesByID(idmovie).observe(
//            viewLifecycleOwner,
//            {
//                if (it is Result.Success) {
//
////                    progressBar.visibility = View.GONE
//                }
//            }
//        )

    }



}