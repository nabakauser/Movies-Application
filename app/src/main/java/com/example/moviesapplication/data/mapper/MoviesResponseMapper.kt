package com.example.moviesapplication.data.mapper

import com.example.moviesapplication.data.model.MoviesResponseModel
import com.example.moviesapplication.utils.Response

fun moviesMapper(movies : retrofit2.Response<List<MoviesResponseModel?>?>?) : Response<List<MoviesResponseModel?>?> {
    return if (movies?.isSuccessful == true) Response.success(movies.body())
    else Response.error(movies?.message() ?: "error")
}