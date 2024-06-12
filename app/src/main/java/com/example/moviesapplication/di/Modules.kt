package com.example.moviesapplication.di

import androidx.room.Room
import com.example.moviesapplication.data.repository.MoviesRepository
import com.example.moviesapplication.data.room.db.MoviesDatabase
import com.example.moviesapplication.data.service.RestHelper
import com.example.moviesapplication.view.details.MovieDetailsViewModel
import com.example.moviesapplication.view.overview.DefaultImageUrlFetcher
import com.example.moviesapplication.view.overview.ImageUrlFetcher
import com.example.moviesapplication.view.overview.MoviesOverviewViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object Modules {
    fun modules() = commonModule + repositoryModule + viewModelModule
}

val repositoryModule  = module {
    single { MoviesRepository(get(), get()) }
}

val viewModelModule = module {
    viewModel { MoviesOverviewViewModel(get(), get(), get()) }
    viewModel { MovieDetailsViewModel() }
}

val commonModule = module {
    single<CoroutineDispatcher> { Dispatchers.IO }
    single<ImageUrlFetcher> { DefaultImageUrlFetcher() }
    single { RestHelper.client }
    single { get<MoviesDatabase>().moviesDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            MoviesDatabase::class.java,
            "moviesDB"
        ).fallbackToDestructiveMigration().build()
    }
}