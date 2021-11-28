package com.example.movieplace.ui.profile

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.movieplace.databinding.FavComposersFragmentBinding

class FavComposersFragment : Fragment() {

    companion object {
        fun newInstance() = FavComposersFragment()
    }

    private var _binding: FavComposersFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: FavComposersViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FavComposersFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FavComposersViewModel::class.java)
        // TODO: Use the ViewModel
    }

}