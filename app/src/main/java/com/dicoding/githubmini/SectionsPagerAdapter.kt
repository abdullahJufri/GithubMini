package com.dicoding.githubmini

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class SectionsPagerAdapter(activity: AppCompatActivity,private val username: String ):
    FragmentStateAdapter(activity) {

//    private var fragmentBundle = data
//
//    init {
//        fragmentBundle = data
//    }

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = FollowerFragment.newInstance(position + 1, username)
//            1 -> fragment = FollowingFragment.newInstance(position + 1, username)
//            0 -> fragment = FollowerFragment()
            1 -> fragment = FollowingFragment()
        }

//        fragment?.arguments = this.fragmentBundle
        return fragment as Fragment
    }

}