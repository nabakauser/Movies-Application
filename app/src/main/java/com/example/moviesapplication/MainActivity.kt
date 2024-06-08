package com.example.moviesapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.moviesapplication.ui.theme.MoviesApplicationTheme
import com.example.moviesapplication.view.overview.MoviesOverviewRoute

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MoviesApplicationTheme {
                MoviesOverviewRoute()
            }
        }
    }
}