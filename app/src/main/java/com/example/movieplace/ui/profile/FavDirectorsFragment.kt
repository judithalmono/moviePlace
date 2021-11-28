package com.example.movieplace.ui.profile

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.movieplace.databinding.FavDirectorsFragmentBinding

class FavDirectorsFragment : Fragment() {

    companion object {
        fun newInstance() = FavDirectorsFragment()
    }

    private var _binding: FavDirectorsFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: FavDirectorsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FavDirectorsFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FavDirectorsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}