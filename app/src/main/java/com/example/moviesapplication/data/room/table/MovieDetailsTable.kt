package com.example.moviesapplication.data.room.table

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "MoviesTable")
data class MoviesTableModel(
    @PrimaryKey
    val movieName: String,

    @ColumnInfo
    val id: Int?,

    @ColumnInfo
    val rating: Double?,

    @ColumnInfo
    val image: String?,

    @ColumnInfo
    val imdbUrl: String?,

    @ColumnInfo
    val isFavourite: Boolean?,

    @ColumnInfo
    val isWatched: Boolean?,
)