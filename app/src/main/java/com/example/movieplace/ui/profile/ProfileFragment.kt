package com.example.movieplace.ui.profile

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import com.example.movieplace.ChangePassword
import com.example.movieplace.R
import com.example.movieplace.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    /*companion object {
        fun newInstance() = ProfileFragment()
    }*/
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //return inflater.inflate(R.layout.fragment_profile, container, false)
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val button = root.findViewById<ImageButton>(R.id.button_change_password)
        button.setOnClickListener() {
            val intent = Intent(context, ChangePassword::class.java)
            context?.startActivity(intent)
        }
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        // TODO: Use the ViewModel
    }

}