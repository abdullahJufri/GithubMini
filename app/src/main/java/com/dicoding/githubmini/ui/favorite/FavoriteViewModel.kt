package com.dicoding.githubmini.ui.favorite

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.dicoding.githubmini.database.Favorite
import com.dicoding.githubmini.database.FavoriteDao
import com.dicoding.githubmini.database.FavoriteDatabase

class FavoriteViewModel(application: Application) : AndroidViewModel(application) {

    private var userDao: FavoriteDao?
    private var userDb: FavoriteDatabase?

    init {
        userDb = FavoriteDatabase.getDatabase(application)
        userDao = userDb?.favoriteDao()
    }

    fun getFavoriteUser(): LiveData<List<Favorite>>? {
        return userDao?.getFavorite()
    }

}