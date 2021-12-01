package com.example.movieplace.ui.profile

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.movieplace.R
import com.example.movieplace.databinding.ChangeMyLikesFragmentBinding
import com.example.movieplace.databinding.FavGenresFragmentBinding

class FavGenres : Fragment() {

    companion object {
        fun newInstance() = FavGenres()
    }

    private var _binding: FavGenresFragmentBinding? ? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: FavGenresViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FavGenresFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FavGenresViewModel::class.java)
        // TODO: Use the ViewModel
    }

}