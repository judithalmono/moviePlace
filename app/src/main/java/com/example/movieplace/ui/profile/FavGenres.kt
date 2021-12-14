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
import android.widget.Toast
import android.widget.ToggleButton
import com.example.movieplace.R
import com.example.movieplace.common.Constants
import com.example.movieplace.common.SharedPreferenceManager
import com.example.movieplace.data.Result
import com.example.movieplace.data.model.*
import com.example.movieplace.databinding.ChangeMyLikesFragmentBinding
import com.example.movieplace.databinding.FavGenresFragmentBinding
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar

class FavGenres : Fragment() {

    companion object {
        fun newInstance() = FavGenres()
    }

    private var _binding: FavGenresFragmentBinding? = null
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

    private val username = "admin"

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

        var genre1 = ""
        var genre2 = ""

        val username = SharedPreferenceManager.getStringValue(Constants().PREF_USERNAME)

        if (username != null) {
            favGenresviewModel.getInfoUser(username)
        }
        if (username != null) {
            favGenresviewModel.getInfoUser(username).observe(
                viewLifecycleOwner,
                {
                    if (it is Result.Success) {
                        genre1 = it.data.genre_pref
                        when (genre1) {
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

                        genre2 = it.data.genre_2
                        when (genre2) {
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
            )
        }

        var foundGenre1 = false
        var foundGenre2 = false
        var numGen = 0

        //If click the Submitted Button
        buttonSubmit.setOnClickListener {

            val del = username?.let { it1 -> Delete(it1) }

            if (raction.isChecked) {
                if (!foundGenre1 && numGen <= 2) {
                    val act = username?.let { it1 -> NewGenre(it1, "accio") }
                    if (act != null) {
                        favGenresviewModel.setGenre1(act)
                    }
                    foundGenre1 = true
                    ++numGen
                }
                else if (!foundGenre2 && numGen <= 2) {
                    val act = username?.let { it1 -> NewGenre(it1, "accio") }
                    if (act != null) {
                        favGenresviewModel.setGenre2(act)
                    }
                    foundGenre2 = true
                    ++numGen
                }

            }
            else {
                if (genre1 == "accio") del?.let { it1 -> favGenresviewModel.deleteGenre1(it1) }
                if (genre2 == "accio") del?.let { it1 -> favGenresviewModel.deleteGenre2(it1) }
            }
            if (radventure.isChecked) {
                if (!foundGenre1 && numGen <= 2) {
                    val act = username?.let { it1 -> NewGenre(it1, "adventure") }
                    if (act != null) {
                        favGenresviewModel.setGenre1(act)
                    }
                    foundGenre1 = true
                    ++numGen
                }
                else if (!foundGenre2 && numGen <= 2) {
                    Log.d("Genre2", foundGenre2.toString() )
                    val act = username?.let { it1 -> NewGenre(it1, "adventure") }
                    if (act != null) {
                        favGenresviewModel.setGenre2(act)
                    }
                    if (act != null) {
                        Log.d("Set", act.genre)
                    }
                    foundGenre2 = true
                    ++numGen
                    Log.d("Genre2", foundGenre2.toString() )
                }
            }
            else {
                if (genre1 == "adventure") del?.let { it1 -> favGenresviewModel.deleteGenre1(it1) }
                if (genre2 == "adventure") del?.let { it1 -> favGenresviewModel.deleteGenre2(it1) }
            }
            if (rcomedy.isChecked) {
                if (!foundGenre1 && numGen <= 2) {
                    val act = username?.let { it1 -> NewGenre(it1, "comedy") }
                    if (act != null) {
                        favGenresviewModel.setGenre1(act)
                    }
                    foundGenre1 = true
                    ++numGen
                }
                else if (!foundGenre2 && numGen <= 2) {
                    val act = username?.let { it1 -> NewGenre(it1, "comedy") }
                    if (act != null) {
                        favGenresviewModel.setGenre2(act)
                    }
                    foundGenre2 = true
                    ++numGen
                }
            }
            else {
                if (genre1 == "comedy") del?.let { it1 -> favGenresviewModel.deleteGenre1(it1) }
                if (genre2 == "comedy") del?.let { it1 -> favGenresviewModel.deleteGenre2(it1) }
            }
            if (rcrime_mysery.isChecked) {
                if (!foundGenre1 && numGen <= 2) {
                    val act = username?.let { it1 -> NewGenre(it1, "crime_mystery") }
                    if (act != null) {
                        favGenresviewModel.setGenre1(act)
                    }
                    foundGenre1 = true
                    ++numGen
                }
                else if (!foundGenre2 && numGen <= 2) {
                    val act = username?.let { it1 -> NewGenre(it1, "crime_mystery") }
                    if (act != null) {
                        favGenresviewModel.setGenre2(act)
                    }
                    foundGenre2 = true
                    ++numGen
                }
            }
            else {
                if (genre1 == "crime_mystery") del?.let { it1 -> favGenresviewModel.deleteGenre1(it1) }
                if (genre2 == "crime_mystery") del?.let { it1 -> favGenresviewModel.deleteGenre2(it1) }
            }
            if (rdrama.isChecked) {
                if (!foundGenre1 && numGen <= 2) {
                    val act = username?.let { it1 -> NewGenre(it1, "drama") }
                    if (act != null) {
                        favGenresviewModel.setGenre1(act)
                    }
                    foundGenre1 = true
                    ++numGen
                }
                else if (!foundGenre2 && numGen <= 2) {
                    val act = username?.let { it1 -> NewGenre(it1, "drama") }
                    if (act != null) {
                        favGenresviewModel.setGenre2(act)
                    }
                    foundGenre2 = true
                    ++numGen
                }
            }
            else {
                if (genre1 == "drama") del?.let { it1 -> favGenresviewModel.deleteGenre1(it1) }
                if (genre2 == "drama") del?.let { it1 -> favGenresviewModel.deleteGenre2(it1) }
            }
            if (rfantasy.isChecked) {
                if (!foundGenre1 && numGen <= 2) {
                    val act = username?.let { it1 -> NewGenre(it1, "fantasy") }
                    if (act != null) {
                        favGenresviewModel.setGenre1(act)
                    }
                    foundGenre1 = true
                    ++numGen
                }
                else if (!foundGenre2 && numGen <= 2) {
                    val act = username?.let { it1 -> NewGenre(it1, "fantasy") }
                    if (act != null) {
                        favGenresviewModel.setGenre2(act)
                    }
                    foundGenre2 = true
                    ++numGen
                }
            }
            else {
                if (genre1 == "fantasy") del?.let { it1 -> favGenresviewModel.deleteGenre1(it1) }
                if (genre2 == "fantasy") del?.let { it1 -> favGenresviewModel.deleteGenre2(it1) }
            }
            if (rhistorical.isChecked) {
                if (!foundGenre1 && numGen <= 2) {
                    val act = username?.let { it1 -> NewGenre(it1, "historical") }
                    if (act != null) {
                        favGenresviewModel.setGenre1(act)
                    }
                    foundGenre1 = true
                    ++numGen
                }
                else if (!foundGenre2 && numGen <= 2) {
                    val act = username?.let { it1 -> NewGenre(it1, "historical") }
                    if (act != null) {
                        favGenresviewModel.setGenre2(act)
                    }
                    foundGenre2 = true
                    ++numGen
                }
            }
            else {
                if (genre1 == "historical") del?.let { it1 -> favGenresviewModel.deleteGenre1(it1) }
                if (genre2 == "historical") del?.let { it1 -> favGenresviewModel.deleteGenre2(it1) }
            }
            if (rhorror.isChecked) {
                if (!foundGenre1 && numGen <= 2) {
                    val act = username?.let { it1 -> NewGenre(it1, "horror") }
                    if (act != null) {
                        favGenresviewModel.setGenre1(act)
                    }
                    foundGenre1 = true
                    ++numGen
                }
                else if (!foundGenre2 && numGen <= 2) {
                    val act = username?.let { it1 -> NewGenre(it1, "horror") }
                    if (act != null) {
                        favGenresviewModel.setGenre2(act)
                    }
                    foundGenre2 = true
                    ++numGen
                }
            }
            else {
                if (genre1 == "horror") del?.let { it1 -> favGenresviewModel.deleteGenre1(it1) }
                if (genre2 == "horror") del?.let { it1 -> favGenresviewModel.deleteGenre2(it1) }
            }
            if (rromance.isChecked) {
                if (!foundGenre1 && numGen <= 2) {
                    val act = username?.let { it1 -> NewGenre(it1, "romance") }
                    if (act != null) {
                        favGenresviewModel.setGenre1(act)
                    }
                    foundGenre1 = true
                    ++numGen
                }
                else if (!foundGenre2 && numGen <= 2) {
                    val act = username?.let { it1 -> NewGenre(it1, "romance") }
                    if (act != null) {
                        favGenresviewModel.setGenre2(act)
                    }
                    foundGenre2 = true
                    ++numGen
                }
            }
            else {
                if (genre1 == "romance") del?.let { it1 -> favGenresviewModel.deleteGenre1(it1) }
                if (genre2 == "romance") del?.let { it1 -> favGenresviewModel.deleteGenre2(it1) }
            }
            if (rscfiction.isChecked) {
                if (!foundGenre1 && numGen <= 2) {
                    val act = username?.let { it1 -> NewGenre(it1, "fiction") }
                    if (act != null) {
                        favGenresviewModel.setGenre1(act)
                    }
                    foundGenre1 = true
                    ++numGen
                }
                else if (!foundGenre2 && numGen <= 2) {
                    val act = username?.let { it1 -> NewGenre(it1, "fiction") }
                    if (act != null) {
                        favGenresviewModel.setGenre2(act)
                    }
                    foundGenre2 = true
                    ++numGen
                }
            }
            else {
                if (genre1 == "fiction") del?.let { it1 -> favGenresviewModel.deleteGenre1(it1) }
                if (genre2 == "fiction") del?.let { it1 -> favGenresviewModel.deleteGenre2(it1) }
            }
            if (rthriller.isChecked) {
                if (!foundGenre1 && numGen <= 2) {
                    val act = username?.let { it1 -> NewGenre(it1, "thriller") }
                    if (act != null) {
                        favGenresviewModel.setGenre1(act)
                    }
                    foundGenre1 = true
                    ++numGen
                }
                else if (!foundGenre2 && numGen <= 2) {
                    val act = username?.let { it1 -> NewGenre(it1, "thriller") }
                    if (act != null) {
                        favGenresviewModel.setGenre2(act)
                    }
                    foundGenre2 = true
                    ++numGen
                }
            }
            else {
                if (genre1 == "thriller") del?.let { it1 -> favGenresviewModel.deleteGenre1(it1) }
                if (genre2 == "thriller") del?.let { it1 -> favGenresviewModel.deleteGenre2(it1) }
            }
            if (rwestern.isChecked) {
                if (!foundGenre1 && numGen <= 2) {
                    val act = username?.let { it1 -> NewGenre(it1, "western") }
                    if (act != null) {
                        favGenresviewModel.setGenre1(act)
                    }
                    foundGenre1 = true
                    ++numGen
                }
                else if (!foundGenre2 && numGen <= 2) {
                    val act = username?.let { it1 -> NewGenre(it1, "western") }
                    if (act != null) {
                        favGenresviewModel.setGenre2(act)
                    }
                    foundGenre2 = true
                    ++numGen
                }
            }
            else {
                if (genre1 == "western") del?.let { it1 -> favGenresviewModel.deleteGenre1(it1) }
                if (genre2 == "western") del?.let { it1 -> favGenresviewModel.deleteGenre2(it1) }
            }

            Toast.makeText(context, "Update successfully", Toast.LENGTH_SHORT).show()
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}