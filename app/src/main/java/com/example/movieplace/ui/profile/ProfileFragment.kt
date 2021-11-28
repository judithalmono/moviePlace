package com.example.movieplace.ui.profile

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.FragmentTransaction
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

        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root

        //Change Personal Information
        val button1 = root.findViewById<ImageButton>(R.id.button_change_PersInfo)
        button1.setOnClickListener() {
            val nuevoFragmento: Fragment = ChangePersInfoFragment()
            val transaction: FragmentTransaction = requireFragmentManager().beginTransaction()
            transaction.replace(R.id.fragment_profile, nuevoFragmento)
            transaction.addToBackStack(null)
            transaction.commit()
        }

        //Change Password
        val button2 = root.findViewById<ImageButton>(R.id.button_change_password)
        button2.setOnClickListener() {
            val nuevoFragmento: Fragment = ChangePasswordFragment()
            val transaction: FragmentTransaction = requireFragmentManager().beginTransaction()
            transaction.replace(R.id.fragment_profile, nuevoFragmento)
            transaction.addToBackStack(null)
            transaction.commit()
        }

        //Change My Likes
        val button3 = root.findViewById<ImageButton>(R.id.button_change_MyLikes)
        button3.setOnClickListener() {
            // Crea el nuevo fragmento y la transacción.
            val nuevoFragmento: Fragment = ChangeMyLikesFragment()
            val transaction: FragmentTransaction = requireFragmentManager().beginTransaction()
            transaction.replace(R.id.fragment_profile, nuevoFragmento)
            transaction.addToBackStack(null)

            // Commit a la transacción
            transaction.commit()
        }


        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        // TODO: Use the ViewModel
    }

}