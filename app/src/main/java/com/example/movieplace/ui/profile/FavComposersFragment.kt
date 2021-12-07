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
import com.example.movieplace.data.model.NewCompositor
import com.example.movieplace.data.model.NewDirector
import com.example.movieplace.databinding.FavComposersFragmentBinding
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar

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

    private lateinit var llista_comp : List<String>

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
                    llista_comp = it.data.compositors_pref
                    var num = 0
                    val iterator = llista_comp.iterator()
                    while (iterator.hasNext()) {
                        when (num) {
                            0 -> editTextComposer1.setText(iterator.next())
                            1 -> editTextComposer2.setText(iterator.next())
                            2 -> editTextComposer3.setText(iterator.next())
                            3 -> editTextComposer4.setText(iterator.next())
                            4 -> editTextComposer5.setText(iterator.next())
                            5 -> editTextComposer6.setText(iterator.next())
                            6 -> editTextComposer7.setText(iterator.next())
                            7 -> editTextComposer8.setText(iterator.next())
                        }
                        ++num
                    }
                }
            }
        )

        //If click the Submitted Button
        buttonSubmit.setOnClickListener {
            var user = NewCompositor(usr, editTextComposer1.text.toString())
            favComposersviewModel.setCompositor(user)
            user = NewCompositor(usr, editTextComposer2.text.toString())
            favComposersviewModel.setCompositor(user)
            user = NewCompositor(usr, editTextComposer3.text.toString())
            favComposersviewModel.setCompositor(user)
            user = NewCompositor(usr, editTextComposer4.text.toString())
            favComposersviewModel.setCompositor(user)
            user = NewCompositor(usr, editTextComposer5.text.toString())
            favComposersviewModel.setCompositor(user)
            user = NewCompositor(usr, editTextComposer6.text.toString())
            favComposersviewModel.setCompositor(user)
            user = NewCompositor(usr, editTextComposer7.text.toString())
            favComposersviewModel.setCompositor(user)
            user = NewCompositor(usr, editTextComposer8.text.toString())
            favComposersviewModel.setCompositor(user)

            Snackbar.make(
                root.findViewById(R.id.FavComposers),
                "Update successfully",
                BaseTransientBottomBar.LENGTH_SHORT
            ).show()
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}