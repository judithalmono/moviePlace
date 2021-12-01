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
import android.widget.RadioButton
import com.example.movieplace.R
import com.example.movieplace.data.Result
import com.example.movieplace.data.model.*
import com.example.movieplace.databinding.ChangePersInfoFragmentBinding

class ChangePersInfoFragment : Fragment() {

    private var _binding: ChangePersInfoFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var changePersInfoViewModel : ChangePersInfoViewModel
    private lateinit var editTextUsername: EditText
    private lateinit var editTextFullName: EditText
    private lateinit var editTextEmail: EditText
    private lateinit var editTextPhone: EditText
    private lateinit var editTextDateBirth: EditText
    private lateinit var editTextAddress: EditText
    private lateinit var rman : RadioButton
    private lateinit var rwoman : RadioButton
    private lateinit var rother: RadioButton
    private lateinit var buttonSubmit : Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        changePersInfoViewModel = ViewModelProvider(this).get(ChangePersInfoViewModel::class.java)

        _binding = ChangePersInfoFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root

        editTextUsername = root.findViewById(R.id.editUsername1)
        editTextFullName = root.findViewById(R.id.editComplName1)
        editTextEmail = root.findViewById(R.id.editEmail1)
        editTextPhone = root.findViewById(R.id.editPhone)
        editTextDateBirth = root.findViewById(R.id.editBirthday)
        editTextAddress = root.findViewById(R.id.editAddress1)
        rman = root.findViewById(R.id.ManSelect)
        rwoman = root.findViewById(R.id.WomanSelect)
        rother = root.findViewById(R.id.OtherSelect)
        buttonSubmit = root.findViewById(R.id.buttonSubmit1)

        // Per ara només funciona manualment, i per l'usuari admin.
        val usr = "admin"

        changePersInfoViewModel.getInfoUser(usr)
        changePersInfoViewModel.getInfoUser(usr).observe(
            viewLifecycleOwner,
            {
                if (it is Result.Success) {
                    editTextUsername.setText(it.data.username)
                    editTextFullName.setText(it.data.full_name)
                    editTextEmail.setText(it.data.email)
                    editTextPhone.setText(it.data.phone)
                    editTextDateBirth.setText(it.data.date_birth)
                    editTextAddress.setText(it.data.personal_address)
                    when (it.data.sex) {
                        "man" -> rman.isChecked = true
                        "woman" -> rwoman.isChecked = true
                        "nobinari" -> rother.isChecked = true
                    }
                }
            }
        )

        //If click the Submitted Button
        buttonSubmit.setOnClickListener {
            Log.d("MainActivity", "Button clicked")
            val user = Username(usr, editTextUsername.text.toString())
            changePersInfoViewModel.setUsername(user)
            val f_n = FullName(usr, editTextFullName.text.toString())
            changePersInfoViewModel.setFullName(f_n)
            val email = Email(usr, editTextEmail.text.toString())
            changePersInfoViewModel.setEmail(email)
            val telf = Phone(usr, editTextPhone.text.toString())
            changePersInfoViewModel.setTelefon(telf)
            val birth = Birth(usr, editTextPhone.text.toString())
            changePersInfoViewModel.setBirth(birth)
            val add = Address(usr, editTextAddress.text.toString())
            changePersInfoViewModel.setAddress(add)
            var c_sex = ""
            if (rman.isChecked) c_sex = "man"
            if (rwoman.isChecked) c_sex = "woman"
            if (rother.isChecked) c_sex = "other"
            val sex = Sex(usr, c_sex)
            changePersInfoViewModel.setSex(sex)
            Log.d("MainActivity", "Update")
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}