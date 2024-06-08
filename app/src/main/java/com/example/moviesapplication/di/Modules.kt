package com.example.moviesapplication.di

import com.example.moviesapplication.data.repository.MoviesRepository
import com.example.moviesapplication.data.service.RestHelper
import com.example.moviesapplication.view.overview.MoviesOverviewViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object Modules {
    fun modules() = commonModule + repositoryModule + viewModelModule
}

val repositoryModule  = module {
    single { MoviesRepository(get()) }
}

val viewModelModule = module {
    viewModel { MoviesOverviewViewModel(get()) }
}

val commonModule = module {
    single { RestHelper.client }
}