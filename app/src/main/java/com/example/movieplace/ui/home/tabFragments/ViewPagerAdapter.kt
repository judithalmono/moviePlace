package com.example.movieplace.ui.home.tabFragments

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        // Return a NEW fragment instance in createFragment(int)
        return when(position) {
            0 -> Top.newInstance()
            1 -> ForYou.newInstance()
            else -> Top.newInstance()
        }
    }
}