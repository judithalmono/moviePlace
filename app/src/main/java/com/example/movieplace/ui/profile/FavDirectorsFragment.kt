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
import com.example.movieplace.data.model.NewDirector
import com.example.movieplace.data.model.Username
import com.example.movieplace.databinding.FavDirectorsFragmentBinding
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar

class FavDirectorsFragment : Fragment() {

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

    private lateinit var llista_dir : List<String>

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
                    llista_dir = it.data.directors_pref
                    var num = 0
                    val iterator = llista_dir.iterator()
                    while (iterator.hasNext()) {
                        when (num) {
                            0 -> editTextDirector1.setText(iterator.next())
                            1 -> editTextDirector2.setText(iterator.next())
                            2 -> editTextDirector3.setText(iterator.next())
                            3 -> editTextDirector4.setText(iterator.next())
                            4 -> editTextDirector5.setText(iterator.next())
                            5 -> editTextDirector6.setText(iterator.next())
                            6 -> editTextDirector7.setText(iterator.next())
                            7 -> editTextDirector8.setText(iterator.next())
                        }
                        ++num
                    }
                }
            }
        )

        //If click the Submitted Button
        buttonSubmit.setOnClickListener {
            var user = NewDirector(usr, editTextDirector1.text.toString())
            favDirectorsviewModel.setDirector(user)
            user = NewDirector(usr, editTextDirector2.text.toString())
            favDirectorsviewModel.setDirector(user)
            user = NewDirector(usr, editTextDirector3.text.toString())
            favDirectorsviewModel.setDirector(user)
            user = NewDirector(usr, editTextDirector4.text.toString())
            favDirectorsviewModel.setDirector(user)
            user = NewDirector(usr, editTextDirector5.text.toString())
            favDirectorsviewModel.setDirector(user)
            user = NewDirector(usr, editTextDirector6.text.toString())
            favDirectorsviewModel.setDirector(user)
            user = NewDirector(usr, editTextDirector7.text.toString())
            favDirectorsviewModel.setDirector(user)
            user = NewDirector(usr, editTextDirector8.text.toString())
            favDirectorsviewModel.setDirector(user)

            Snackbar.make(
                root.findViewById(R.id.FavDirectors),
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