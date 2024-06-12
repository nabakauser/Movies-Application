package com.example.moviesapplication.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.moviesapplication.data.model.MoviesResponseModel
import com.example.moviesapplication.data.repository.MoviesRepository
import com.example.moviesapplication.data.room.db.MoviesDao
import com.example.moviesapplication.data.service.MoviesService
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response

class MoviesRepositoryTest {

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var moviesService: MoviesService

    @Mock
    lateinit var moviesDao: MoviesDao

    private lateinit var moviesRepository: MoviesRepository

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        moviesRepository = MoviesRepository(moviesService, moviesDao)
    }

    @Test
    fun getEmptyResponseTest() = runTest {
        val movieResponse = listOf<MoviesResponseModel?>()
        val expectedResponse = Response.success(movieResponse)

        Mockito.`when`(moviesService.getMoviesList())
            .thenReturn(expectedResponse)

        val actualResponse = moviesRepository.getMoviesList()
        Assert.assertEquals(true, actualResponse.data?.isEmpty())
    }

    @Test
    fun testGetMoviesListSuccess() = runTest {
        val movieResponse = listOf<MoviesResponseModel?>(
            MoviesResponseModel(
                id = 1,
                movie = "Dune",
                rating = 9.3,
                image = "image_dune",
                imdbUrl = ""
            ),
            MoviesResponseModel(
                id = 2,
                movie = "Interstellar",
                rating = 8.6,
                image = "image_stellar",
                imdbUrl = null
            )
        )
        val expectedResponse = Response.success(movieResponse)

        Mockito.`when`(moviesService.getMoviesList())
            .thenReturn(expectedResponse)

        val actualResponse = moviesRepository.getMoviesList()
        Assert.assertEquals(2, actualResponse.data?.size)
    }
}