package com.example.moviesapplication.view.overview

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.moviesapplication.view.overview.compose.MoviesOverviewScreen
import org.koin.androidx.compose.koinViewModel

@Composable
fun MoviesOverviewRoute(){
    val viewModel: MoviesOverviewViewModel = koinViewModel()
    val uiState by viewModel.uiState.collectAsState()
    MoviesOverviewScreen(
        isLoading = uiState.isLoading,
        moviesList = uiState.moviesList?.toList() ?: listOf()
    )
}