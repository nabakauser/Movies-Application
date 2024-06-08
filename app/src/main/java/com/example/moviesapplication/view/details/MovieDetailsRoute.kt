package com.example.moviesapplication.view.details

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.moviesapplication.view.details.compose.MovieDetailsScreen
import com.example.moviesapplication.view.overview.MoviesData
import com.google.gson.Gson
import org.koin.androidx.compose.koinViewModel

@Composable
fun MovieDetailsRoute(
    movieItem: String?
) {
    val viewModel: MovieDetailsViewModel = koinViewModel()
    val uiState by viewModel.uiState.collectAsState()

    Log.d("moviesLog","r - $movieItem")
    val movie = Gson().fromJson(movieItem, MoviesData::class.java)
    MovieDetailsScreen(
        movie = movie,
    )
}