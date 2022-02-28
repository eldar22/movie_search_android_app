package com.example.androidwebapi.database

import androidx.room.*

@Dao
interface MovieDAO {

    @Insert
    fun insertMovie(movie: Movies)

    @Update
    fun updateMovie(movie: Movies)

    @Delete
    fun deleteMovie(movie: Movies)

    @Query("SELECT * FROM movies")
    fun getAll(): List<Movies>

}