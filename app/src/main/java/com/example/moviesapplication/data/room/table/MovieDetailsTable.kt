package com.example.moviesapplication.data.room.table

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Movies")
data class MoviesTableModel(
    @PrimaryKey
    val movieName: String,

    @ColumnInfo
    var rating: Double?,

    @ColumnInfo
    var image: String?,

    @ColumnInfo
    var imdbUrl: String?,
)