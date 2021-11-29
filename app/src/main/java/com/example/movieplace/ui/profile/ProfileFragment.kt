package com.example.movieplace.ui.profile

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import androidx.fragment.app.FragmentTransaction
import com.example.movieplace.R
import com.example.movieplace.data.Result
import com.example.movieplace.databinding.FragmentProfileBinding
import de.hdodenhof.circleimageview.CircleImageView


class ProfileFragment : Fragment() {

    /*companion object {
        fun newInstance() = ProfileFragment()
    }*/
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private lateinit var profileViewModel: ProfileViewModel

    private lateinit var editTextUsername: EditText
    private lateinit var editTextFullName: EditText
    private lateinit var editTextEmail: EditText
    private lateinit var imageViewProfilePic: CircleImageView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        profileViewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)

        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root

        imageViewProfilePic = root.findViewById(R.id.circleImageView)
        editTextUsername = root.findViewById(R.id.editTextUsername)
        editTextFullName = root.findViewById(R.id.editTextFullName)
        editTextEmail = root.findViewById(R.id.editTextEmail)

        // Per ara només funciona manualment, i per l'usuari admin.
        val usr = "admin"


        profileViewModel.getInfoUser(usr)
        profileViewModel.getInfoUser(usr).observe(
            viewLifecycleOwner,
            {
                if (it is Result.Success) {
                    editTextUsername.setText(it.data.username)
                    editTextFullName.setText(it.data.full_name)
                    editTextEmail.setText(it.data.email)
                }
            }
        )

        val bdUsername : String = editTextUsername.toString()
        val bdFullName : String = editTextFullName.toString()
        val bdEmail : String = editTextEmail.toString()

        //Change Username
        val buttonUsername = root.findViewById<ImageButton>(R.id.button_change_Username2)
        buttonUsername.setOnClickListener() {
            /*if (editTextUsername.text.toString() != bdUsername) {
                profileViewModel.setUsername(usr, editTextUsername.text.toString())
            }*/
            val nuevoFragmento: Fragment = ChangePersInfoFragment()
            val transaction: FragmentTransaction = requireFragmentManager().beginTransaction()
            transaction.replace(R.id.fragment_profile, nuevoFragmento)
            transaction.addToBackStack(null)
            transaction.commit()
        }

        //Change Full Name
        val buttonFullName = root.findViewById<ImageButton>(R.id.button_change_FullName2)
        buttonUsername.setOnClickListener() {

        }

        //Change Email
        val buttonEmail = root.findViewById<ImageButton>(R.id.button_change_Email2)
        buttonUsername.setOnClickListener() {

        }


        //Change Personal Information
        val button1 = root.findViewById<ImageButton>(R.id.button_change_PersInfo)
        button1.setOnClickListener() {
            val nuevoFragmento: Fragment = ChangePersInfoFragment()
            val transaction: FragmentTransaction = requireFragmentManager().beginTransaction()
            transaction.replace(R.id.fragment_profile, nuevoFragmento)
            transaction.addToBackStack(null)
            transaction.commit()
        }

        //Change Password
        val button2 = root.findViewById<ImageButton>(R.id.button_change_password)
        button2.setOnClickListener() {
            val nuevoFragmento: Fragment = ChangePasswordFragment()
            val transaction: FragmentTransaction = requireFragmentManager().beginTransaction()
            transaction.replace(R.id.fragment_profile, nuevoFragmento)
            transaction.addToBackStack(null)
            transaction.commit()
        }

        //Change My Likes
        val button3 = root.findViewById<ImageButton>(R.id.button_change_MyLikes)
        button3.setOnClickListener() {
            // Crea el nuevo fragmento y la transacción.
            val nuevoFragmento: Fragment = ChangeMyLikesFragment()
            val transaction: FragmentTransaction = requireFragmentManager().beginTransaction()
            transaction.replace(R.id.fragment_profile, nuevoFragmento)
            transaction.addToBackStack(null)

            // Commit a la transacción
            transaction.commit()
        }


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}