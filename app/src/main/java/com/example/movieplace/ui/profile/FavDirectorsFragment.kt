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
import com.example.movieplace.databinding.FavDirectorsFragmentBinding

class FavDirectorsFragment : Fragment() {

    companion object {
        fun newInstance() = FavDirectorsFragment()
    }

    private var _binding: FavDirectorsFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var favDirectorsviewModel: FavDirectorsViewModel

    private lateinit var editTextDirector1: EditText
    private lateinit var editTextDirector2: EditText
    private lateinit var editTextDirector3: EditText
    private lateinit var editTextDirector4: EditText
    private lateinit var editTextDirector5: EditText
    private lateinit var editTextDirector6: EditText
    private lateinit var editTextDirector7: EditText
    private lateinit var editTextDirector8: EditText
    private lateinit var buttonSubmit : Button

    // Per ara només funciona manualment, i per l'usuari admin.
    private val usr = "admin"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        favDirectorsviewModel = ViewModelProvider(this).get(FavDirectorsViewModel::class.java)

        _binding = FavDirectorsFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root

        editTextDirector1 = root.findViewById(R.id.editDirector1)
        editTextDirector2 = root.findViewById(R.id.editDirector2)
        editTextDirector3 = root.findViewById(R.id.editDirector3)
        editTextDirector4 = root.findViewById(R.id.editDirector4)
        editTextDirector5 = root.findViewById(R.id.editDirector5)
        editTextDirector6 = root.findViewById(R.id.editDirector6)
        editTextDirector7 = root.findViewById(R.id.editDirector7)
        editTextDirector8 = root.findViewById(R.id.editDirector8)
        buttonSubmit = root.findViewById(R.id.buttonSubmitDirectors)

        favDirectorsviewModel.getInfoUser(usr)
        favDirectorsviewModel.getInfoUser(usr).observe(
            viewLifecycleOwner,
            {
                if (it is Result.Success) {
                    editTextDirector1.setText(it.data.directors_pref)
                }
            }
        )


        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        favDirectorsviewModel = ViewModelProvider(this).get(FavDirectorsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}