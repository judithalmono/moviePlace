package com.example.movieplace.ui.scenes

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieplace.R
import com.example.movieplace.data.Result
import com.example.movieplace.data.model.Scene
import com.example.movieplace.databinding.FragmentSceneBinding
import com.google.android.material.tabs.TabLayout

class SceneFragment : Fragment() {
    private lateinit var sceneViewModel: SceneViewModel
    private lateinit var sceneAdapter: MyScenesRecyclesViewAdapter
    lateinit var toolbar: Toolbar
    private var _binding: FragmentSceneBinding? = null
    private var scenes: List<Scene> = ArrayList()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        sceneViewModel =
            ViewModelProvider(this).get(SceneViewModel::class.java)

        _binding = FragmentSceneBinding.inflate(inflater, container, false)
        val root: View = binding.root

        sceneAdapter = MyScenesRecyclesViewAdapter(context)
        val listScenes = root.findViewById<RecyclerView>(R.id.listScenes)

//        binding.listScenes.layoutManager = GridLayoutManager(context, 2)
//        binding.listScenes.adapter = sceneAdapter
//
//        sceneViewModel.getTopMovies().observe(
//            viewLifecycleOwner,
//            {
//                if (it is Result.Success) {
//                    movies = it.data
//                    movieAdapter.setData(movies)
//                }
//            }
//        )
//
//        tabLayout.addTab(tabLayout.newTab().setText(TAB_TITLES[0]), 0, true)
//        tabLayout.addTab(tabLayout.newTab().setText(TAB_TITLES[1]), 1, false)
//
//        tabLayout.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
//            @SuppressLint("ResourceAsColor")
//            override fun onTabSelected(tab: TabLayout.Tab?) {
//
//                if (tab?.text == TAB_TITLES[0]) {
//                    movieViewModel.getTopMovies().observe(
//                        viewLifecycleOwner,
//                        {
//                            if (it is Result.Success) {
//                                movies = it.data
//                                movieAdapter.setData(movies)
//                            }
//                        }
//                    )
//                } else {
//                    movieViewModel.getRecommendMovies().observe(
//                        viewLifecycleOwner,
//                        {
//                            if (it is Result.Success) {
//                                movies = it.data
//                                movieAdapter.setData(movies)
//                            }
//                        }
//                    )
//                }
//            }
//            override fun onTabUnselected(tab: TabLayout.Tab?) {
//            }
//            override fun onTabReselected(tab: TabLayout.Tab?) {
//            }
//        })

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
