package com.example.moviesapplication.view.overview

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapplication.data.model.MoviesResponseModel
import com.example.moviesapplication.data.repository.MoviesRepository
import com.example.moviesapplication.data.room.table.MoviesTableModel
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.jsoup.Jsoup

class MoviesOverviewViewModel(
    private val repository: MoviesRepository
) : ViewModel() {

    private val viewModelState = MutableStateFlow(MoviesOverviewViewModelState(isLoading = true))

    val uiState = viewModelState
        .map { it.toUiState() }
        .stateIn(viewModelScope, SharingStarted.Eagerly, viewModelState.value.toUiState())

    init {
        checkDatabaseAndFetchMovies()
    }

    private fun checkDatabaseAndFetchMovies(){
        viewModelScope.launch {
            viewModelState.update { it.copy(isLoading = true) }
            repository.getMoviesFromDb()?.collect{ movies ->
                Log.d("moviesLog","r - $movies")
                if(movies?.isEmpty() == true) {
                    getMoviesList()
                } else {
                    viewModelState.update {
                        it.copy(
                            moviesList = mapMoviesListFromDb(movies),
                            isLoading = false,
                        )
                    }
                }
            }
        }
    }

    private fun getMoviesList() {
        viewModelScope.launch {
            viewModelState.update { it.copy(isLoading = true) }
            val response = repository.getMoviesList()
            if (response.data != null) {
                viewModelState.update {
                    it.copy(
                        moviesList = mapMoviesList(response.data.take(25)),
                        isLoading = false,
                    )
                }
                repository.saveMoviesToDb(mapMoviesTableModel(viewModelState.value.moviesList))
            }
        }
    }

    private suspend fun mapMoviesList(movies: List<MoviesResponseModel?>?): MutableList<MoviesData?>? {
        return coroutineScope {
            movies?.map { movie ->
                async {
                    val imageUrl = movie?.imdbUrl?.let { fetchImageUrlFromImdb(it).await() }
                    MoviesData(
                        id = movie?.id,
                        movieName = movie?.movie,
                        rating = movie?.rating,
                        image = imageUrl,
                        imdbUrl = movie?.imdbUrl,
                    )
                }
            }?.awaitAll()?.toMutableList()
        }
    }

    private fun mapMoviesListFromDb(movies: List<MoviesTableModel?>?): MutableList<MoviesData?> {
        return movies?.map { movie ->
            MoviesData(
                id = movie?.id,
                movieName = movie?.movieName,
                rating = movie?.rating,
                image = movie?.image,
                imdbUrl = movie?.imdbUrl,
                isFavourite = movie?.isFavourite ?: false,
                isWatched = movie?.isWatched ?: false
            )
        }?.toMutableList() ?: mutableListOf()
    }
    private fun mapMoviesTableModel(
        moviesList: MutableList<MoviesData?>?
    ): List<MoviesTableModel> {
        return moviesList?.map { movie ->
            MoviesTableModel(
                id = movie?.id,
                movieName = movie?.movieName ?: "",
                rating = movie?.rating,
                image = movie?.image,
                imdbUrl = movie?.imdbUrl,
                isFavourite = movie?.isFavourite,
                isWatched = movie?.isWatched
            )
        } ?: listOf()
    }

    private fun fetchImageUrlFromImdb(imdbUrl: String): Deferred<String?> {
        return viewModelScope.async(Dispatchers.IO) {
            var url: String? = null
            try {
                val document = Jsoup.connect(imdbUrl).get()
                val element = document.select("meta[property=og:image]").first()
                url = element?.attr("content")
            } catch (e: Exception) {
                e.printStackTrace()
            }
            url
        }
    }

    fun onIconClicked(
        movieId: String,
        type: String,
        isSelected: Boolean
    ) {
        viewModelScope.launch {
            viewModelState.value.moviesList?.forEach { movie ->
                if (movie?.movieName == movieId) {
                    when (type) {
                        "favourite" -> {
                            movie.isFavourite = !isSelected
                            repository.updateFavourites(movieId, !isSelected)
                        }
                        "watchlist" -> {
                            movie.isWatched = !isSelected
                            repository.updateWatchList(movieId, !isSelected)
                        }
                    }
                }
            }
        }
        viewModelState.update {
            it.copy(
                moviesList = viewModelState.value.moviesList,
                lastUpdated = System.currentTimeMillis(),
            )
        }
    }
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
    val imdbUrl: String? = "",
    var isFavourite: Boolean = false,
    var isWatched: Boolean = false,
)