package com.example.moviesapplication.data.repository

import com.example.moviesapplication.data.mapper.moviesMapper
import com.example.moviesapplication.data.model.MoviesResponseModel
import com.example.moviesapplication.data.room.db.MoviesDao
import com.example.moviesapplication.data.room.table.MoviesTableModel
import com.example.moviesapplication.data.service.MoviesService
import com.example.moviesapplication.utils.Response
import kotlinx.coroutines.flow.Flow

class MoviesRepository(
    private val moviesService: MoviesService,
    private val roomDao: MoviesDao,
) {
    suspend fun getMoviesList(): Response<List<MoviesResponseModel?>?> =
        moviesMapper(moviesService.getMoviesList())

    suspend fun saveMoviesToDb(movies: List<MoviesTableModel>){
        roomDao.saveMoviesList(movies)
    }

    fun getMoviesFromDb(): Flow<List<MoviesTableModel?>?>? {
        return roomDao.getMovieList()
    }

    suspend fun updateFavourites(movieId: String, isFavourite: Boolean){
        roomDao.updateFavouriteMovie(movieId,isFavourite)
    }

    suspend fun updateWatchList(movieId: String, isWatched: Boolean){
        roomDao.updateWatchList(movieId,isWatched)
    }
}