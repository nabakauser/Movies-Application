package com.example.moviesapplication.overview.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.moviesapplication.overview.MoviesData

@Preview
@Composable
fun OverviewPreview(){
    MoviesOverviewScreen(
        isLoading = false,
        moviesList = listOf(
            MoviesData(
                id = 1,
                movieName = "Dune - Part I",
                rating = 9.3,
                image = "images/dune.jpg",
                imageUrl = "",
            ),
            MoviesData(
                id = 2,
                movieName = "Dune - Part II",
                rating = 8.7,
                image = "images/dune2.jpg",
                imageUrl = "",
            ),
            MoviesData(
                id = 3,
                movieName = "Dune - Part III",
                rating = 9.0,
                image = "images/dune3.jpg",
                imageUrl = "",
            ),
        )
    )
}

@Composable
fun MoviesOverviewScreen(
    isLoading: Boolean,
    moviesList: List<MoviesData>,
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        if(!isLoading){
            if(moviesList.isNotEmpty()) {
                LazyColumn{
                    items(moviesList) {
                        MovieCard(moviesData = it)
                    }
                }
            } else {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(text = "No movies found!")
                }
            }
        } else {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                CircularProgressIndicator()
            }
        }
    }
}