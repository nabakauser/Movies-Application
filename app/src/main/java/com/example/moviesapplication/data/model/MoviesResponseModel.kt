package com.example.moviesapplication.data.model

import com.google.gson.annotations.SerializedName

data class MoviesResponseModel(
    val id: Int? = null,
    val movie: String? = null,
    val rating: Double? = null,
    val image: String? = null,
    @SerializedName("imdb_url")
    val imdbUrl: String? = null,
)