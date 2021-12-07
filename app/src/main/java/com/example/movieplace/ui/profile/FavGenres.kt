package com.example.movieplace.ui.profile

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.ToggleButton
import com.example.movieplace.R
import com.example.movieplace.data.Result
import com.example.movieplace.data.model.Movie
import com.example.movieplace.data.model.NewDirector
import com.example.movieplace.data.model.NewGenre
import com.example.movieplace.data.model.Sex
import com.example.movieplace.databinding.ChangeMyLikesFragmentBinding
import com.example.movieplace.databinding.FavGenresFragmentBinding
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar

class FavGenres : Fragment() {

    companion object {
        fun newInstance() = FavGenres()
    }

    private var _binding: FavGenresFragmentBinding? ? = null
    private val binding get() = _binding!!

    private lateinit var favGenresviewModel: FavGenresViewModel

    private lateinit var raction : ToggleButton
    private lateinit var radventure : ToggleButton
    private lateinit var rcomedy: ToggleButton
    private lateinit var rcrime_mysery: ToggleButton
    private lateinit var rdrama: ToggleButton
    private lateinit var rfantasy: ToggleButton
    private lateinit var rhistorical: ToggleButton
    private lateinit var rhorror: ToggleButton
    private lateinit var rromance: ToggleButton
    private lateinit var rscfiction: ToggleButton
    private lateinit var rthriller: ToggleButton
    private lateinit var rwestern: ToggleButton
    private lateinit var buttonSubmit : Button

    private val usr = "admin"
    //private var genres: List<String> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        favGenresviewModel = ViewModelProvider(this).get(FavGenresViewModel::class.java)

        _binding = FavGenresFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root

        raction = root.findViewById(R.id.ActionSelect)
        radventure = root.findViewById(R.id.AdvSelect)
        rcomedy = root.findViewById(R.id.ComedySelect)
        rcrime_mysery = root.findViewById(R.id.CrimMistSelect)
        rdrama = root.findViewById(R.id.DramaSelect)
        rfantasy = root.findViewById(R.id.FantasySelect)
        rhistorical = root.findViewById(R.id.HistoSelect)
        rhorror = root.findViewById(R.id.HorrorSelect)
        rromance = root.findViewById(R.id.RomanceSelect)
        rscfiction = root.findViewById(R.id.SFicSelect)
        rthriller = root.findViewById(R.id.ThrillerSelect)
        rwestern = root.findViewById(R.id.WesternSelect)
        buttonSubmit = root.findViewById(R.id.buttonSubmitGenres)

        favGenresviewModel.getInfoUser(usr)
        favGenresviewModel.getInfoUser(usr).observe(
            viewLifecycleOwner,
            {
                if (it is Result.Success) {
                    for (g in it.data.user_genres) {
                        when (g) {
                            "accio" -> raction.isChecked = true
                            "adventure" -> radventure.isChecked = true
                            "comedy" -> rcomedy.isChecked = true
                            "crime_mystery" -> rcrime_mysery.isChecked = true
                            "drama" -> rdrama.isChecked = true
                            "fantasy" -> rfantasy.isChecked = true
                            "historical" -> rhistorical.isChecked = true
                            "horror" -> rhorror.isChecked = true
                            "romance" -> rromance.isChecked = true
                            "fiction" -> rscfiction.isChecked = true
                            "thriller" -> rthriller.isChecked = true
                            "western" -> rwestern.isChecked = true
                        }
                    }
                }
            }
        )

        //If click the Submitted Button
        buttonSubmit.setOnClickListener {
            if (raction.isChecked) {
                val act = NewGenre(usr, "accio")
                favGenresviewModel.setGenre(act)
            }
            if (radventure.isChecked) {
                val act = NewGenre(usr, "adventure")
                favGenresviewModel.setGenre(act)
            }
            if (rcomedy.isChecked) {
                val act = NewGenre(usr, "comedy")
                favGenresviewModel.setGenre(act)
            }
            if (rcrime_mysery.isChecked) {
                val act = NewGenre(usr, "crime_mystery")
                favGenresviewModel.setGenre(act)
            }
            if (rdrama.isChecked) {
                val act = NewGenre(usr, "drama")
                favGenresviewModel.setGenre(act)
            }
            if (rfantasy.isChecked) {
                val act = NewGenre(usr, "fantasy")
                favGenresviewModel.setGenre(act)
            }
            if (rhistorical.isChecked) {
                val act = NewGenre(usr, "historical")
                favGenresviewModel.setGenre(act)
            }
            if (rhorror.isChecked) {
                val act = NewGenre(usr, "horror")
                favGenresviewModel.setGenre(act)
            }
            if (rromance.isChecked) {
                val act = NewGenre(usr, "romance")
                favGenresviewModel.setGenre(act)
            }
            if (rscfiction.isChecked) {
                val act = NewGenre(usr, "fiction")
                favGenresviewModel.setGenre(act)
            }
            if (rthriller.isChecked) {
                val act = NewGenre(usr, "thriller")
                favGenresviewModel.setGenre(act)
            }
            if (rwestern.isChecked) {
                val act = NewGenre(usr, "western")
                favGenresviewModel.setGenre(act)
            }
            Snackbar.make(
                root.findViewById(R.id.act_fav_genres),
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