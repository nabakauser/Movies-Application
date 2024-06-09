package com.example.moviesapplication.view.overview.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.moviesapplication.view.overview.MoviesData

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
                imdbUrl = "",
            ),
            MoviesData(
                id = 2,
                movieName = "Dune - Part II",
                rating = 8.7,
                image = "images/dune2.jpg",
                imdbUrl = "",
            ),
            MoviesData(
                id = 3,
                movieName = "Dune - Part III",
                rating = 9.0,
                image = "images/dune3.jpg",
                imdbUrl = "",
            )
        ),
        navigateToDetails = {},
        onIconClicked = { _, _, _ -> },
    )
}

@Composable
fun MoviesOverviewScreen(
    isLoading: Boolean,
    moviesList: List<MoviesData?>?,
    onIconClicked:(String, String, Boolean) -> Unit,
    navigateToDetails: (MoviesData) -> Unit,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            OverviewTopBar()
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .background(Color.Black)
        ) {
            if(!isLoading){
                if(moviesList?.isNotEmpty() == true) {
                    LazyColumn(
                        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 24.dp)
                    ){
                        items(moviesList) {
                            it?.let {
                                MovieCard(
                                    moviesData = it,
                                    onIconClicked = { type, isSelected ->
                                        onIconClicked(it.movieName ?: "", type, isSelected)
                                    },
                                    navigateToDetails = navigateToDetails
                                )
                            }
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
}