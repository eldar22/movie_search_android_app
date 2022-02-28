package com.example.androidwebapi.screens.details

import android.app.Application
import android.view.Menu
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.example.androidwebapi.database.AppDatabase
import com.example.androidwebapi.network.Movies

class DetailsViewModel( movie: Movies.Result, app: Application) : AndroidViewModel(app) {
    var db : AppDatabase

    private val _selectedMovie = MutableLiveData<Movies.Result>()
    val selectedMovie: LiveData<Movies.Result>
        get() = _selectedMovie


    init {
        _selectedMovie.value = movie

        db = Room.databaseBuilder(
            getApplication(),
            AppDatabase::class.java, "db-movies"
        ).allowMainThreadQueries().build()
    }

    fun saveOrUpdate(movie : com.example.androidwebapi.database.Movies){//jer ako proslijedim film sa postojecim PK-om da ga unese pada aplikacija
                                                                        //com.example.androidwebapi.database.Movies mora ici da bi znao iz kojeg paketa uzima tip podatka
        val list = db.movieDAO().getAll()
        var counter = 0

        for (i in list.indices){
            if (list[i].title != movie.title) {
                counter++
            }
        }

        if (counter == list.size){
            db.movieDAO().insertMovie(movie)
        } else{
            db.movieDAO().updateMovie(movie)
        }
    }

}