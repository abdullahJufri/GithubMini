package com.dicoding.githubmini

import android.annotation.SuppressLint
import android.app.SearchManager
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.githubmini.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: UsersAdapter
    private val viewModel by viewModels<MainViewModel>()

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        adapter = UsersAdapter()
        adapter.notifyDataSetChanged()

        adapter.setOnItemClickCallback(object : UsersAdapter.OnItemClickCallback {

            override fun onItemClicked(data: User) {
                Intent(this@MainActivity, DetailUserActivity::class.java).also {
                    it.putExtra(DetailUserActivity.EXTRA_USERNAME, data.login)
                    startActivity(it)
                }
            }
        })

        binding.rvUsers.layoutManager = LinearLayoutManager(this)
        binding.rvUsers.adapter = adapter
        binding.rvUsers.setHasFixedSize(true)


        viewModel.getListUsers().observe(this, {
            if (it != null) {
                adapter.setList(it)
            }
        })

        viewModel.isLoading.observe(this, {
            showLoading(it)
        })

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.option_menu, menu)

        val searchManager = getSystemService(SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.search).actionView as SearchView

        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.queryHint = resources.getString(R.string.search_hint)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            /*
            Gunakan method ini ketika search selesai atau OK
             */
            override fun onQueryTextSubmit(query: String): Boolean {
                viewModel.findUsers(query)
                return true
            }

            /*
            Gunakan method ini untuk merespon tiap perubahan huruf pada searchView
             */
            override fun onQueryTextChange(query: String): Boolean {
                viewModel.findUsers(query)
                return false
            }
        })
        return true
    }


    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}