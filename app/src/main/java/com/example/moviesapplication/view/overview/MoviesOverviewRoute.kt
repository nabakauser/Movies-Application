package com.example.moviesapplication.view.overview

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import com.example.moviesapplication.view.overview.compose.MoviesOverviewScreen
import com.google.gson.Gson
import org.koin.androidx.compose.koinViewModel

@Composable
fun MoviesOverviewRoute(
    navController: NavHostController
) {
    val viewModel: MoviesOverviewViewModel = koinViewModel()
    val uiState by viewModel.uiState.collectAsState()
    MoviesOverviewScreen(
        isLoading = uiState.isLoading,
        moviesList = uiState.moviesList?.toList() ?: listOf(),
        onIconClicked = { movieId, type, isSelected ->
            viewModel.onIconClicked(movieId, type, isSelected)
        },
        navigateToDetails = {
            val movie = Gson().toJson(it)
            navController.navigate("Details?movie=$movie")
        }
    )
}