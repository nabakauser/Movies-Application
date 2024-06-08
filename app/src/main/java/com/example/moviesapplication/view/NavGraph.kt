package com.example.moviesapplication.view

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.moviesapplication.view.details.MovieDetailsRoute
import com.example.moviesapplication.view.overview.MoviesOverviewRoute

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "Overview"){
        composable("Overview"){
            MoviesOverviewRoute(
                navController = navController
            )
        }
        composable(
            route = "Details?movie={movie}",
            arguments = listOf(
                navArgument(name = "movie") {
                    type = NavType.StringType
                    nullable = true
                }
            )
        ){
            MovieDetailsRoute(
                movieItem = it.arguments?.getString("movie")
            )
        }
    }
}