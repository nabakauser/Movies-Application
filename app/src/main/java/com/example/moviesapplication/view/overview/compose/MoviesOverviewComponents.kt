package com.example.moviesapplication.view.overview.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.moviesapplication.R
import com.example.moviesapplication.view.overview.MoviesData

@Preview
@Composable
fun MovieCardPreview() {
    MovieCard(
        moviesData = MoviesData(
            id = 2,
            movieName = "Dune - Part II",
            rating = 8.7,
            image = "images/dune.jpg",
            imdbUrl = "",
        ),
        navigateToDetails = {},
    )
}

@Composable
fun MovieCard(
    moviesData: MoviesData,
    navigateToDetails: (MoviesData) -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(bounded = true)
            ) {
                navigateToDetails(moviesData)
            },
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),
        shape = RoundedCornerShape(4.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            contentAlignment = Alignment.BottomStart
        ) {
            AsyncImage(
                modifier = Modifier
                    .matchParentSize()
                    .clip(RoundedCornerShape(4.dp)),
                model = ImageRequest
                    .Builder(LocalContext.current)
                    .data(moviesData.image)
                    .placeholder(R.drawable.ic_img_placeholder)
                    .build(),
                contentDescription = "image",
                contentScale = ContentScale.Crop,
            )
            Box(
              modifier = Modifier
                  .matchParentSize()
                  .background(
                      brush = Brush.radialGradient(
                          colors = listOf(Color.Black, Color.Transparent),
                          center = Offset(0f, 1f),
                          radius = 1500f
                      )
                  )
            ){}
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .align(Alignment.TopStart)
            ) {
                Text(
                    modifier = Modifier
                        .padding(16.dp),
                    text = moviesData.movieName ?: "",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    textAlign = TextAlign.Start
                )
            }
        }
    }
    Spacer(modifier = Modifier.padding(2.dp))
}

@Preview(showBackground = true)
@Composable
fun OverviewTopBar() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Black)
            .padding(top = 24.dp)
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Black)
                .padding(16.dp),
            text = "===movies===",
            textAlign = TextAlign.Center,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic,
            style = TextStyle(
                letterSpacing = 8.sp,
                brush = Brush.linearGradient(
                    listOf(
                        Color.DarkGray,
                        Color.White,
                    )
                )
            )
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(2.dp)
                .background(
                brush = Brush.linearGradient(
                    listOf(
                        Color.DarkGray,
                        Color.White,
                    )
                )
            )
        )
    }
}