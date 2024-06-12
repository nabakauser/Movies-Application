package com.example.moviesapplication.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.moviesapplication.data.model.MoviesResponseModel
import com.example.moviesapplication.data.repository.MoviesRepository
import com.example.moviesapplication.data.room.db.MoviesDao
import com.example.moviesapplication.data.room.table.MoviesTableModel
import com.example.moviesapplication.utils.Response
import com.example.moviesapplication.view.overview.ImageUrlFetcher
import com.example.moviesapplication.view.overview.MoviesData
import com.example.moviesapplication.view.overview.MoviesOverviewViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations


class MoviesOverviewViewModelTest {

    @get: Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var repository: MoviesRepository

    @Mock
    lateinit var moviesDao: MoviesDao

    @Mock
    lateinit var imageUrlFetcher: ImageUrlFetcher

    private val testDispatcher = StandardTestDispatcher()

    private lateinit var viewModel: MoviesOverviewViewModel

    @Before
    fun setUp(){
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
        viewModel = MoviesOverviewViewModel(testDispatcher, repository, imageUrlFetcher)
    }

    @Test
    fun getEmptyMoviesList() = runTest {
        Mockito.`when`(repository.getMoviesList()).thenReturn(Response.success(emptyList()))
        val expectedMoviesData = listOf<MoviesResponseModel>()
        viewModel.getMoviesList()
        advanceUntilIdle()
        val uiState = viewModel.uiState.value
        Assert.assertEquals(expectedMoviesData, uiState.moviesList)
        Assert.assertFalse(uiState.isLoading)
    }

    @Test
    fun getMoviesList() = runTest {
        val moviesResponse = listOf(
            MoviesResponseModel(id = 1, movie = "Movie 1", rating = 8.5, imdbUrl = "http://example.com/movie1"),
            MoviesResponseModel(id = 2, movie = "Movie 2", rating = 7.9, imdbUrl = "http://example.com/movie2")
        )
        val expectedMoviesData = listOf(
            MoviesData(id = 1, movieName = "Movie 1", rating = 8.5, image = "http://example.com/image1", imdbUrl = "http://example.com/movie1"),
            MoviesData(id = 2, movieName = "Movie 2", rating = 7.9, image = "http://example.com/image2", imdbUrl = "http://example.com/movie2")
        )

        Mockito.`when`(repository.getMoviesList()).thenReturn(Response.success(moviesResponse))
        Mockito.`when`(imageUrlFetcher.fetchImageUrlFromImdb("http://example.com/movie1")).thenReturn("http://example.com/image1")
        Mockito.`when`(imageUrlFetcher.fetchImageUrlFromImdb("http://example.com/movie2")).thenReturn("http://example.com/image2")

        viewModel.getMoviesList()
        advanceUntilIdle()
        val uiState = viewModel.uiState.value
        Assert.assertEquals(expectedMoviesData, uiState.moviesList)
        Assert.assertFalse(uiState.isLoading)

        Mockito.verify(repository).saveMoviesToDb(expectedMoviesData.map {
            MoviesTableModel(
                id = it.id,
                movieName = it.movieName ?: "",
                rating = it.rating,
                image = it.image,
                imdbUrl = it.imdbUrl,
                isFavourite = false,
                isWatched = false
            )
        })
    }

    @After
    fun tearDown(){
        Dispatchers.resetMain()
    }
}