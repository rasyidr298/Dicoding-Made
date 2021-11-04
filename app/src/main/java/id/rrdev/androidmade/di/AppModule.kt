package id.rrdev.androidmade.di

import id.rrdev.androidmade.detail.DetailViewModel
import id.rrdev.androidmade.movies.MoviesViewModel
import id.rrdev.core.domain.IMovieAppUseCase
import id.rrdev.core.domain.MovieAppInteractor
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module


val useCaseModule = module {
    factory<IMovieAppUseCase> { MovieAppInteractor(get()) }
}

@ExperimentalCoroutinesApi
@FlowPreview
val viewModelModule = module {
    viewModel { MoviesViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}