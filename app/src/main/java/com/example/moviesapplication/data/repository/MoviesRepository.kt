package com.example.moviesapplication.data.repository

import com.example.moviesapplication.utils.Response
import com.example.moviesapplication.data.model.MoviesResponseModel
import com.example.moviesapplication.data.mapper.moviesMapper
import com.example.moviesapplication.data.service.MoviesService

class MoviesRepository(
    private val moviesService: MoviesService
) {
    suspend fun getMoviesList(): Response<List<MoviesResponseModel?>?> =
        moviesMapper(moviesService.getMoviesList())
}