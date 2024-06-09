package com.example.moviesapplication.data.room.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.moviesapplication.data.room.table.MoviesTableModel
import kotlinx.coroutines.flow.Flow

@Dao
interface MoviesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMoviesList(movieData: List<MoviesTableModel>)

    @Query("SELECT * FROM MoviesTable")
    fun getMovieList(): Flow<List<MoviesTableModel?>?>?

    @Query("UPDATE MoviesTable SET isFavourite = :isFavourite WHERE (movieName = :movieId)")
    suspend fun updateFavouriteMovie(movieId: String, isFavourite: Boolean)

    @Query("UPDATE MoviesTable SET isWatched = :isWatched WHERE (movieName = :movieId)")
    suspend fun updateWatchList(movieId: String, isWatched: Boolean)

}