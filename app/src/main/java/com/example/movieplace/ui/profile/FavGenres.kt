package com.example.movieplace.ui.profile

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import com.example.movieplace.R
import com.example.movieplace.data.Result
import com.example.movieplace.data.model.Movie
import com.example.movieplace.databinding.ChangeMyLikesFragmentBinding
import com.example.movieplace.databinding.FavGenresFragmentBinding

class FavGenres : Fragment() {

    companion object {
        fun newInstance() = FavGenres()
    }

    private var _binding: FavGenresFragmentBinding? ? = null
    private val binding get() = _binding!!

    private lateinit var favGenresviewModel: FavGenresViewModel

    private lateinit var raction : RadioButton
    private lateinit var radventure : RadioButton
    private lateinit var rcomedy: RadioButton
    private lateinit var rcrime_mysery: RadioButton
    private lateinit var rdrama: RadioButton
    private lateinit var rfantasy: RadioButton
    private lateinit var rhistorical: RadioButton
    private lateinit var rhorror: RadioButton
    private lateinit var rromance: RadioButton
    private lateinit var rscfiction: RadioButton
    private lateinit var rthriller: RadioButton
    private lateinit var rwestern: RadioButton
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
                            "horror" -> rhistorical.isChecked = true
                            "romance" -> rromance.isChecked = true
                            "fiction" -> rscfiction.isChecked = true
                            "thriller" -> rthriller.isChecked = true
                            "western" -> rwestern.isChecked = true
                        }
                    }
                }
            }
        )

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}