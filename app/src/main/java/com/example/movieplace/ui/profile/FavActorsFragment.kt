package com.example.movieplace.ui.profile

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.example.movieplace.R
import com.example.movieplace.data.Result
import com.example.movieplace.databinding.FavActorsFragmentBinding

class FavActorsFragment : Fragment() {

    companion object {
        fun newInstance() = FavActorsFragment()
    }

    private var _binding: FavActorsFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var favActorsviewModel: FavActorsViewModel

    private lateinit var editTextActor1: EditText
    private lateinit var editTextActor2: EditText
    private lateinit var editTextActor3: EditText
    private lateinit var editTextActor4: EditText
    private lateinit var editTextActor5: EditText
    private lateinit var editTextActor6: EditText
    private lateinit var editTextActor7: EditText
    private lateinit var editTextActor8: EditText
    private lateinit var buttonSubmit : Button

    // Per ara només funciona manualment, i per l'usuari admin.
    private val usr = "admin"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        favActorsviewModel = ViewModelProvider(this).get(FavActorsViewModel::class.java)

        _binding = FavActorsFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root

        editTextActor1 = root.findViewById(R.id.editActor1)
        editTextActor2 = root.findViewById(R.id.editActor2)
        editTextActor3 = root.findViewById(R.id.editActor3)
        editTextActor4 = root.findViewById(R.id.editActor4)
        editTextActor5 = root.findViewById(R.id.editActor5)
        editTextActor6 = root.findViewById(R.id.editActor6)
        editTextActor7 = root.findViewById(R.id.editActor7)
        editTextActor8 = root.findViewById(R.id.editActor8)
        buttonSubmit = root.findViewById(R.id.buttonSubmitActors)

        favActorsviewModel.getInfoUser(usr)
        favActorsviewModel.getInfoUser(usr).observe(
            viewLifecycleOwner,
            {
                if (it is Result.Success) {
                    for (actor in it.data.actors_pref) {
                        editTextActor1.setText(actor)
                        editTextActor2.setText(actor)
                        editTextActor3.setText(actor)
                        editTextActor4.setText(actor)
                        editTextActor5.setText(actor)
                        editTextActor6.setText(actor)
                        editTextActor7.setText(actor)
                        editTextActor8.setText(actor)
                    }
                }
            }
        )


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}