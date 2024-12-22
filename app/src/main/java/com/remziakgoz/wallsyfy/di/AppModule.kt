package com.remziakgoz.wallsyfy.di

import com.remziakgoz.wallsyfy.data.remote.RemoteDataSource
import com.remziakgoz.wallsyfy.data.remote.WallpaperService
import com.remziakgoz.wallsyfy.data.repository.WallpaperRepositoryImpl
import com.remziakgoz.wallsyfy.domain.repository.WallpaperRepository
import com.remziakgoz.wallsyfy.domain.use_cases.GetWallpapersUseCase
import com.remziakgoz.wallsyfy.domain.use_cases.SearchWallpapersUseCase
import com.remziakgoz.wallsyfy.presentation.wallpaper_detail.DetailViewModel
import com.remziakgoz.wallsyfy.presentation.wallpapers.WallpapersViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    factory { RemoteDataSource(get()) }
    factory { WallpaperService() }

    factory { GetWallpapersUseCase() }
    factory { SearchWallpapersUseCase() }

    single<WallpaperRepository> { WallpaperRepositoryImpl(get()) }

    viewModel { WallpapersViewModel(get(), get()) }
    viewModel { DetailViewModel(get()) }
}



