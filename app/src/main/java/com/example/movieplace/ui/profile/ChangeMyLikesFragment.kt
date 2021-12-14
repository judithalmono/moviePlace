package com.example.movieplace.ui.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.FragmentTransaction
import com.example.movieplace.R
import com.example.movieplace.databinding.ChangeMyLikesFragmentBinding

class ChangeMyLikesFragment : Fragment() {

    private var _binding: ChangeMyLikesFragmentBinding? ? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: ChangeMyLikesViewModel

    /*companion object {
        fun newInstance() = ChangeMyLikesFragment()
    }*/

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ChangeMyLikesFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root

        //Change Favourite Genres
        val button1 = root.findViewById<ImageButton>(R.id.button_change_FavGen)
        button1.setOnClickListener() {
            val nuevoFragmento: Fragment = FavGenres()
            val transaction: FragmentTransaction = requireFragmentManager().beginTransaction()
            transaction.replace(R.id.changeMyLikes, nuevoFragmento)
            transaction.addToBackStack(null)
            transaction.commit()
        }

        //Change Favourite Directors, Actors and Composer
        val button2 = root.findViewById<ImageButton>(R.id.button_change_FavDirActComp)
        button2.setOnClickListener() {
            val nuevoFragmento: Fragment = FavDirActCompFragment()
            val transaction: FragmentTransaction = requireFragmentManager().beginTransaction()
            transaction.replace(R.id.changeMyLikes, nuevoFragmento)
            transaction.addToBackStack(null)
            transaction.commit()
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}