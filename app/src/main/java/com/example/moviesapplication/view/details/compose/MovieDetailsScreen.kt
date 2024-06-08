package com.example.moviesapplication.view.details.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.moviesapplication.view.overview.MoviesData
import com.example.moviesapplication.view.overview.compose.MovieCard

@Preview
@Composable
fun DetailsScreenPreview(){
    MovieDetailsScreen(
        movie = MoviesData(
            id = 3,
            movieName = "Dune - Part III",
            rating = 9.0,
            image = "images/dune3.jpg",
            imdbUrl = "",
        ),
    )
}

@Composable
fun MovieDetailsScreen(
    movie: MoviesData?
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = movie?.movieName ?: "",
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
        )
        if(movie != null) {
            MovieCard(moviesData = movie)
        }
    }
}