package com.example.moviesapplication.asset

import androidx.test.core.app.ApplicationProvider
import com.example.moviesapplication.z_testcase.AssetManager
import com.google.gson.JsonSyntaxException
import org.junit.Test
import java.io.FileNotFoundException
import org.junit.Assert.*

class MoviesInstrumentedTest {

    @Test(expected = FileNotFoundException::class)
    fun getEmptyMoviesFileFromAsset(){
        val assetManager = AssetManager()
        val context = ApplicationProvider.getApplicationContext<android.content.Context>()
        assetManager.populateMoviesFromAsset(context,"")
    }

    @Test(expected = JsonSyntaxException::class)
    fun getInvalidJsonFileFromAsset(){
        val assetManager = AssetManager()
        val context = ApplicationProvider.getApplicationContext<android.content.Context>()
        assetManager.populateMoviesFromAsset(context,"malformed.json")
    }

    @Test
    fun getMoviesFromAsset(){
        val assetManager = AssetManager()
        val context = ApplicationProvider.getApplicationContext<android.content.Context>()
        assetManager.populateMoviesFromAsset(context,"movies.json")
        assertEquals(10, assetManager.movieList.size)
    }
}