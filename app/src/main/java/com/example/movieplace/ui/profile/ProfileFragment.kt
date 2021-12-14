package com.example.movieplace.ui.profile

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.FragmentTransaction
import com.bumptech.glide.Glide
import com.example.movieplace.R
import com.example.movieplace.common.Constants
import com.example.movieplace.common.SharedPreferenceManager
import com.example.movieplace.data.Result
import com.example.movieplace.data.model.ProfilePhoto
import com.example.movieplace.databinding.FragmentProfileBinding
import com.example.movieplace.ui.login.Login
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import de.hdodenhof.circleimageview.CircleImageView
import java.io.File
import android.content.DialogInterface

import android.content.DialogInterface.OnShowListener
import android.widget.*


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
    private lateinit var changeProfilePicButton: ImageButton
    private lateinit var intent: Intent
    private val CODIGO_GALLERY = 10
    private var imageUri : Uri? = null
    private lateinit var buttonLogOut: Button
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var customDialog: Dialog

    // Per ara només funciona manualment, i per l'usuari admin.
    private val username = "admin"

    @SuppressLint("IntentReset")
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
        changeProfilePicButton = root.findViewById(R.id.button_change_ProfilePhoto)
        buttonLogOut = root.findViewById(R.id.buttonLogOut2)

        profileViewModel.getInfoUser(username)
        profileViewModel.getInfoUser(username).observe(
            viewLifecycleOwner,
            {
                if (it is Result.Success) {
                    editTextUsername.setText(it.data.username)
                    editTextFullName.setText(it.data.full_name)
                    editTextEmail.setText(it.data.email)
                    val File = File(context?.filesDir, username)
                    if (File.exists()) imageViewProfilePic.setImageURI(Uri.fromFile(File))
                    else imageViewProfilePic.setImageURI(Uri.parse("android.resource://com.example.movieplace/drawable/userwhite"))
                }
            }
        )

        firebaseAuth = Firebase.auth

        //Escollir imatge de la galeria
        imageViewProfilePic.setOnClickListener {
            intent = Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            intent.type = "image/*"
            startActivityForResult(intent, CODIGO_GALLERY)
        }

        //Guardar la imatge
        changeProfilePicButton.setOnClickListener() {
            imageUri?.let {
                val File = File(context?.filesDir, username)
                val bytes = context?.contentResolver?.openInputStream(imageUri!!)?.readBytes()!!
                File.writeBytes(bytes)
                Toast.makeText(context, "Profile Picture Saved ", Toast.LENGTH_SHORT).show()
            }
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
        val button2 = root.findViewById<ImageButton>(R.id.button_change_Suggestions)
        button2.setOnClickListener() {
            val nuevoFragmento: Fragment = SuggestionsFragment()
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

        customDialog = Dialog(requireActivity())
        customDialog.setContentView(R.layout.logout_dialog)

        val cancelBtn = customDialog.findViewById<TextView>(R.id.cancelBtn)
        val okBtn = customDialog.findViewById<TextView>(R.id.okBtn)

        buttonLogOut.setOnClickListener() {
            okBtn.setOnClickListener {
                firebaseAuth.signOut()
                SharedPreferenceManager.deleteData()
                SharedPreferenceManager.setBooleanValue(
                    Constants().PREF_IS_NOT_FIRST_TIME_OPENING_APP,
                    true
                )
                customDialog.dismiss()
                requireActivity().run {
                    startActivity(Intent(this, Login::class.java))
                    finish()
                }
            }
            cancelBtn.setOnClickListener {
                customDialog.dismiss()
            }

            customDialog.show()
        }

        return root
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when {
            requestCode == CODIGO_GALLERY && resultCode == Activity.RESULT_OK -> {
                imageUri = data!!.data
                imageViewProfilePic.setImageURI(imageUri)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}