package com.example.moviesapplication.data.service

import com.example.moviesapplication.data.model.MoviesResponseModel
import retrofit2.Response
import retrofit2.http.GET

interface MoviesService {
    @GET("movies")
    suspend fun getMoviesList(): Response<List<MoviesResponseModel?>?>?
}