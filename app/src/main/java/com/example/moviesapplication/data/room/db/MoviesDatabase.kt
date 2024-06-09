package com.example.moviesapplication.data.room.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.moviesapplication.data.room.table.MoviesTableModel

@Database(
    entities = [MoviesTableModel::class],
    version = 2,
    exportSchema = true
)

//@TypeConverters(Convertors::class)
abstract class MoviesDatabase: RoomDatabase() {
    abstract fun moviesDao(): MoviesDao
}