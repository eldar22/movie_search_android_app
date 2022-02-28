package com.example.androidwebapi.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Movies::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDAO(): MovieDAO
}