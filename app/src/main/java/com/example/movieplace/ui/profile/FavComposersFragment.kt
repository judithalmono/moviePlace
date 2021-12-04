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
import com.example.movieplace.databinding.FavComposersFragmentBinding

class FavComposersFragment : Fragment() {

    companion object {
        fun newInstance() = FavComposersFragment()
    }

    private var _binding: FavComposersFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var favComposersviewModel: FavComposersViewModel

    private lateinit var editTextComposer1: EditText
    private lateinit var editTextComposer2: EditText
    private lateinit var editTextComposer3: EditText
    private lateinit var editTextComposer4: EditText
    private lateinit var editTextComposer5: EditText
    private lateinit var editTextComposer6: EditText
    private lateinit var editTextComposer7: EditText
    private lateinit var editTextComposer8: EditText
    private lateinit var buttonSubmit : Button

    // Per ara només funciona manualment, i per l'usuari admin.
    private val usr = "admin"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        favComposersviewModel = ViewModelProvider(this).get(FavComposersViewModel::class.java)

        _binding = FavComposersFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root

        editTextComposer1 = root.findViewById(R.id.editComposer1)
        editTextComposer2 = root.findViewById(R.id.editComposer2)
        editTextComposer3 = root.findViewById(R.id.editComposer3)
        editTextComposer4 = root.findViewById(R.id.editComposer4)
        editTextComposer5 = root.findViewById(R.id.editComposer5)
        editTextComposer6 = root.findViewById(R.id.editComposer6)
        editTextComposer7 = root.findViewById(R.id.editComposer7)
        editTextComposer8 = root.findViewById(R.id.editComposer8)
        buttonSubmit = root.findViewById(R.id.buttonSubmitComposers)

        favComposersviewModel.getInfoUser(usr)
        favComposersviewModel.getInfoUser(usr).observe(
            viewLifecycleOwner,
            {
                if (it is Result.Success) {
                    editTextComposer1.setText(it.data.compositors_pref)
                }
            }
        )

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        favComposersviewModel = ViewModelProvider(this).get(FavComposersViewModel::class.java)
        // TODO: Use the ViewModel
    }

}