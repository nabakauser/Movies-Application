package com.example.moviesapplication.overview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class MoviesOverviewViewModel() : ViewModel() {

    private val viewModelState = MutableStateFlow(MoviesOverviewViewModelState(isLoading = true))

    private val uiState = viewModelState
        .map { it.toUiState() }
        .stateIn(viewModelScope, SharingStarted.Eagerly, viewModelState.value.toUiState())
}

data class MoviesOverviewViewModelState(
    val isLoading: Boolean = false,
    val lastUpdated: Long = System.currentTimeMillis(),
    val moviesList: MutableList<MoviesData?>? = mutableListOf(),
) {
    fun toUiState(): MoviesOverviewUiState {
        return MoviesOverviewUiState(
            isLoading = isLoading,
            lastUpdated = lastUpdated,
            moviesList = moviesList,
        )
    }
}

data class MoviesOverviewUiState(
    val isLoading: Boolean,
    val lastUpdated: Long,
    val moviesList: MutableList<MoviesData?>?,
)

data class MoviesData(
    val id: Int? = 0,
    val movieName: String? = "",
    val rating: Double? = 0.0,
    val image: String? = "",
    val imageUrl: String? = ""
)