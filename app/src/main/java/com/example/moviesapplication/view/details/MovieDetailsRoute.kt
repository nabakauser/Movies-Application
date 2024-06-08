package com.example.moviesapplication.view.details

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.moviesapplication.view.details.compose.MovieDetailsScreen

@Composable
fun MovieDetailsRoute(
    viewModel: MovieDetailsViewModel
) {
    val uiState by viewModel.uiState.collectAsState()

    MovieDetailsScreen(movie = uiState.moviesData)
}