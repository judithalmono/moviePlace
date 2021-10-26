package com.example.movieplace.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.viewpager2.widget.ViewPager2
import com.example.movieplace.R
import com.example.movieplace.databinding.FragmentHomeBinding
import com.example.movieplace.ui.home.tabFragments.ViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    lateinit var navController: NavController
    lateinit var toolbar: Toolbar
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val TAB_TITLES = arrayOf(
        "Top",
        "Recommend"
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        homeViewModel.text.observe(viewLifecycleOwner, Observer {
        })

        toolbar = root.findViewById(R.id.toolbar)

        val tabLayout = root.findViewById<TabLayout>(R.id.tab_layout)
        val viewPager = root.findViewById<ViewPager2>(R.id.pager)

        viewPager.adapter = ViewPagerAdapter(activity as AppCompatActivity)

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = TAB_TITLES[position]
        }.attach()

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}