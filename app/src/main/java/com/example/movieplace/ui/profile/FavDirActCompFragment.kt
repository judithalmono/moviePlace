package com.example.movieplace.ui.profile

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.example.movieplace.R
import com.example.movieplace.data.Result
import com.example.movieplace.data.model.Delete
import com.example.movieplace.data.model.NewActor
import com.example.movieplace.data.model.NewCompositor
import com.example.movieplace.data.model.NewDirector
import com.example.movieplace.databinding.FavDirActCompFragmentBinding
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar

class FavDirActCompFragment : Fragment() {

    private var _binding: FavDirActCompFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var favDirActCompviewModel: FavDirActCompViewModel
    private lateinit var editTextDirector1: EditText
    private lateinit var editTextDirector2: EditText
    private lateinit var editTextActor1: EditText
    private lateinit var editTextActor2: EditText
    private lateinit var editTextComposer1: EditText
    private lateinit var editTextComposer2: EditText
    private lateinit var buttonSubmit : Button

    // Per ara només funciona manualment, i per l'usuari admin.
    private val username = "admin"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        favDirActCompviewModel = ViewModelProvider(this).get(FavDirActCompViewModel::class.java)

        _binding = FavDirActCompFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root

        editTextDirector1 = root.findViewById(R.id.editDirector1)
        editTextDirector2 = root.findViewById(R.id.editDirector2)
        editTextActor1 = root.findViewById(R.id.editActor1)
        editTextActor2 = root.findViewById(R.id.editActor2)
        editTextComposer1 = root.findViewById(R.id.editComposer1)
        editTextComposer2 = root.findViewById(R.id.editComposer2)
        buttonSubmit = root.findViewById(R.id.buttonSubmitDirActComp)

        favDirActCompviewModel.getInfoUser(username)
        favDirActCompviewModel.getInfoUser(username).observe(
            viewLifecycleOwner,
            {
                if (it is Result.Success) {
                    editTextDirector1.setText(it.data.director_pref)
                    editTextDirector2.setText(it.data.director_2)
                    editTextActor1.setText(it.data.actor_pref)
                    editTextActor2.setText(it.data.actor_2)
                    editTextComposer1.setText(it.data.compositor_pref)
                    editTextComposer2.setText(it.data.compositor_2)
                }
            }
        )

        //If click the Submitted Button
        buttonSubmit.setOnClickListener {
            Log.d("Dir1", editTextDirector1.text.toString())
            val del = Delete(username)

            if (editTextDirector1.text.toString() != "") {
                val user = NewDirector(username, editTextDirector1.text.toString())
                favDirActCompviewModel.setDirector1(user)
            }
            else favDirActCompviewModel.deleteDirector1(del)
            if (editTextDirector2.text.toString() != "") {
                val user = NewDirector(username, editTextDirector2.text.toString())
                favDirActCompviewModel.setDirector2(user)
            }
            else favDirActCompviewModel.deleteDirector2(del)
            if (editTextActor1.text.toString() != "") {
                val user = NewActor(username, editTextActor1.text.toString())
                favDirActCompviewModel.setActor1(user)
            }
            else favDirActCompviewModel.deleteActor1(del)
            if (editTextActor2.text.toString() != "") {
                val user = NewActor(username, editTextActor2.text.toString())
                favDirActCompviewModel.setActor2(user)
            }
            else favDirActCompviewModel.deleteActor2(del)
            if (editTextComposer1.text.toString() != "") {
                val user = NewCompositor(username, editTextComposer1.text.toString())
                favDirActCompviewModel.setCompositor1(user)
            }
            else favDirActCompviewModel.deleteComposer1(del)
            if (editTextComposer2.text.toString() != "") {
                val user = NewCompositor(username, editTextComposer2.text.toString())
                favDirActCompviewModel.setCompositor2(user)
            }
            else favDirActCompviewModel.deleteComposer2(del)

            Snackbar.make(
                root.findViewById(R.id.FavDirActComp),
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