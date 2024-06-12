package com.example.moviesapplication.compose

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.moviesapplication.view.overview.MoviesData
import com.example.moviesapplication.view.overview.compose.MoviesOverviewScreen
import org.junit.Rule
import org.junit.Test

class MoviesOverviewScreenKtTest {

    @get: Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testLoadingState() {
        composeTestRule.setContent {
            MoviesOverviewScreen(
                isLoading = true,
                moviesList = null,
                onIconClicked = { _, _, _ -> },
                navigateToDetails = {}
            )
        }

        composeTestRule
            .onNodeWithTag("moviesColumn")
            .assertExists()
            .onChildren()
            .assertCountEquals(1)

        composeTestRule
            .onNodeWithTag("loadingIndicator")
            .assertExists()
    }


    @Test
    fun testEmptyMoviesList() {
        composeTestRule.setContent {
            MoviesOverviewScreen(
                isLoading = false,
                moviesList = emptyList(),
                onIconClicked = { _, _, _ -> },
                navigateToDetails = {}
            )
        }

        composeTestRule
            .onNodeWithTag("moviesColumn")
            .assertExists()
            .onChildren()
            .assertCountEquals(1)

        composeTestRule
            .onNodeWithTag("loadingIndicator")
            .assertDoesNotExist()

        composeTestRule
            .onNodeWithText("No movies found!")
            .assertExists()
    }

    @Test
    fun testNonEmptyMoviesList() {
        val sampleMovies = listOf(
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
        )

        composeTestRule.setContent {
            MoviesOverviewScreen(
                isLoading = false,
                moviesList = sampleMovies,
                onIconClicked = { _, _, _ -> },
                navigateToDetails = {}
            )
        }

        composeTestRule
            .onNodeWithTag("moviesColumn")
            .assertExists()

        composeTestRule
            .onAllNodesWithTag("MovieCard")
            .assertCountEquals(sampleMovies.size)

        composeTestRule
            .onNodeWithText("Dune - Part I")
            .assertExists()

        composeTestRule
            .onNodeWithText("Movie 2")
            .assertDoesNotExist()

        composeTestRule
            .onNodeWithTag("favourite_1")
            .performClick()

        composeTestRule
            .onNodeWithTag("watchList_1")
            .performClick()

    }
}