package com.example.moviesapplication.overview.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.moviesapplication.R
import com.example.moviesapplication.overview.MoviesData

@Preview
@Composable
fun MovieCardPreview(){
    MovieCard(
        moviesData = MoviesData(
            id = 2,
            movieName = "Dune - Part II",
            rating = 8.7,
            image = "images/dune.jpg",
            imageUrl = "",
        )
    )
}

@Composable
fun MovieCard(
    moviesData: MoviesData
){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
          modifier = Modifier
              .fillMaxWidth()
              .background(Color.White)
              .padding(16.dp)
        ) {
            AsyncImage(
                modifier = Modifier.wrapContentHeight(),
                model = ImageRequest
                    .Builder(LocalContext.current)
                    .data(moviesData.imageUrl)
                    .diskCacheKey("documentImage")
                    .placeholder(R.drawable.ic_img_placeholder)
                    .build(),
                contentDescription = "image",
                contentScale = ContentScale.FillBounds,
            )
            Spacer(modifier = Modifier.padding(8.dp))
            Column(
                modifier = Modifier.weight(6f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = moviesData.movieName ?: "-"
                )
                Text(
                    text = "${moviesData.rating}"
                )
                Text(
                    text = "${moviesData.image}"
                )
            }
        }
    }
}