package com.example.movieplace.ui.profile

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton;
import com.example.movieplace.R
import com.example.movieplace.databinding.ChangePersInfoFragmentBinding

class ChangePersInfoFragment : Fragment() {

    private var _binding: ChangePersInfoFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: ChangePersInfoViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ChangePersInfoFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root


        fun onRadioButtonClicked (view: View) {
            if (view is RadioButton) {
                val checked = view.isChecked
                when (view.getId()) {
                    R.id.ManSelect ->
                        if (checked) {}
                    R.id.WomanSelect ->
                        if (checked) {}
                    R.id.OtherSelect ->
                        if (checked) {}
                }
            }
        }

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ChangePersInfoViewModel::class.java)
        // TODO: Use the ViewModel
    }

}