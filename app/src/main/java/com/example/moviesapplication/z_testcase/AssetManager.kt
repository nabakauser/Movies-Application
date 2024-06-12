package com.example.moviesapplication.z_testcase

import android.content.Context
import com.example.moviesapplication.data.model.MoviesResponseModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class AssetManager {
    var movieList = emptyArray<MoviesResponseModel>()

    fun populateMoviesFromAsset(
        context: Context,
        fileName: String
    ) {
        val jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
        movieList = Gson().fromJson(jsonString, object : TypeToken<Array<MoviesResponseModel>>() {}.type)
    }
}