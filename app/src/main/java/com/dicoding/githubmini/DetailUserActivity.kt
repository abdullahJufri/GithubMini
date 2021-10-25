package com.dicoding.githubmini

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.dicoding.githubmini.databinding.ActivityDetailUserBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import okhttp3.internal.userAgent

class DetailUserActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDetailUserBinding
    private lateinit var  viewModel: DetailUserViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(DetailUserViewModel::class.java)


        val username : String = intent.getStringExtra(EXTRA_USERNAME).toString()
        val bundle = Bundle()
        bundle.putString(EXTRA_USERNAME, username)

        if (username != null){
            viewModel.setUserDetail(username)
        } else{
            Log.d(ContentValues.TAG, "onFailure")
        }

        viewModel.getUserDetail().observe(this, {
            if (it != null){
                binding.apply {
                    tvDetailName.text = it.name
                    tvDetailUsername.text = it.login
                    tvDetailCompany.text = it.company
                    tvDetailLocation.text = it.location
                    tvDetailFollower.text = it.followers.toString()
                    tvDetailFollowing.text = it.following.toString()
                    tvDetailRepo.text = it.publicRepos.toString()
                    Glide.with(this@DetailUserActivity)
                        .load(it.avatarUrl)
                        .transform(CircleCrop())
                        .into(imgAvatar)
                }
            }
        })
        val sectionsPagerAdapter = SectionsPagerAdapter(this@DetailUserActivity, username)
        val viewPager: ViewPager2 = binding.viewPager
        viewPager.adapter = sectionsPagerAdapter

        val tabs: TabLayout = binding.tabs
        TabLayoutMediator(tabs, viewPager){tab , position ->
            tab.text = resources.getString(TITLE_TAB[position])
        }.attach()

        viewModel.isLoading.observe(this, {
            showLoading(it)
        })
    }


    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }



    companion object{
        const val EXTRA_USERNAME = "extra_username"
        @StringRes
        private val TITLE_TAB = intArrayOf(R.string.follower, R.string.following)
    }
}