package com.example.moviesapplication.view.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapplication.view.overview.MoviesData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class MovieDetailsViewModel(): ViewModel() {
    private val viewModelState = MutableStateFlow(MovieDetailsViewModelState(isLoading = true))

    val uiState = viewModelState
        .map { it.toUiState() }
        .stateIn(viewModelScope, SharingStarted.Eagerly, viewModelState.value.toUiState())
}

data class MovieDetailsViewModelState(
    val isLoading: Boolean = false,
    val lastUpdated: Long = System.currentTimeMillis(),
    val moviesData: MoviesData? = null,
){
    fun toUiState(): MovieDetailsUiState {
        return MovieDetailsUiState(
            isLoading = isLoading,
            lastUpdated = lastUpdated,
            moviesData = moviesData,
        )
    }
}

data class MovieDetailsUiState(
    val isLoading: Boolean,
    val lastUpdated: Long,
    val moviesData: MoviesData?,
)