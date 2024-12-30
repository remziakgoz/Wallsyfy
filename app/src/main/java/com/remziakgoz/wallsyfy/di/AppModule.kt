package com.remziakgoz.wallsyfy.di

import com.remziakgoz.wallsyfy.data.remote.RemoteDataSource
import com.remziakgoz.wallsyfy.data.remote.WallpaperService
import com.remziakgoz.wallsyfy.data.repository.WallpaperRepositoryImpl
import com.remziakgoz.wallsyfy.domain.repository.WallpaperRepository
import com.remziakgoz.wallsyfy.domain.use_cases.GetMostDownloadedUseCase
import com.remziakgoz.wallsyfy.domain.use_cases.GetMostFavoriteUseCase
import com.remziakgoz.wallsyfy.domain.use_cases.GetWallpapersUseCase
import com.remziakgoz.wallsyfy.domain.use_cases.SaveWallpaperUseCase
import com.remziakgoz.wallsyfy.domain.use_cases.SearchWallpapersUseCase
import com.remziakgoz.wallsyfy.domain.use_cases.SetWallpaperUseCase
import com.remziakgoz.wallsyfy.presentation.wallpaper_detail.DetailViewModel
import com.remziakgoz.wallsyfy.presentation.wallpapers.viewmodels.MostDownloadedScreenViewModel
import com.remziakgoz.wallsyfy.presentation.wallpapers.viewmodels.MostFavoriteScreenViewModel
import com.remziakgoz.wallsyfy.presentation.wallpapers.viewmodels.WallpapersViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    factory { RemoteDataSource(get()) }
    factory { WallpaperService() }

    factory { GetWallpapersUseCase() }
    factory { SearchWallpapersUseCase() }
    factory { SaveWallpaperUseCase() }
    factory { SetWallpaperUseCase() }
    factory { GetMostDownloadedUseCase() }
    factory { GetMostFavoriteUseCase() }

    single<WallpaperRepository> { WallpaperRepositoryImpl(get()) }

    viewModel { WallpapersViewModel(get(), get()) }
    viewModel { DetailViewModel(get(), get()) }
    viewModel { MostDownloadedScreenViewModel(get()) }
    viewModel { MostFavoriteScreenViewModel(get()) }
}



