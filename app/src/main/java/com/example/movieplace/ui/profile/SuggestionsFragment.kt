package com.example.movieplace.ui.profile

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.movieplace.R
import com.example.movieplace.databinding.SuggestionsFragmentBinding
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar

class SuggestionsFragment : Fragment() {

    private var _binding: SuggestionsFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var suggestionviewModel: SuggestionsViewModel

    private lateinit var editTextName: EditText
    private lateinit var editTextComment: EditText
    private lateinit var buttonSubmit : Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        suggestionviewModel = ViewModelProvider(this).get(SuggestionsViewModel::class.java)

        _binding = SuggestionsFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root

        editTextName = root.findViewById(R.id.edit_your_name)
        editTextComment = root.findViewById(R.id.edit_your_comment)
        buttonSubmit = root.findViewById(R.id.buttonSubmitSuggerations)

        buttonSubmit.setOnClickListener() {
            Toast.makeText(context, "Comment sent successfully", Toast.LENGTH_SHORT).show()

        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}