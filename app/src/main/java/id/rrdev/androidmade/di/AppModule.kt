package id.rrdev.androidmade.di

import id.rrdev.androidmade.detail.DetailViewModel
import id.rrdev.androidmade.fragment.movies.MoviesViewModel
import id.rrdev.androidmade.fragment.search.SearchViewModel
import id.rrdev.core.domain.MovieAppInteractor
import id.rrdev.core.domain.IMovieAppUseCase
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
//    viewModel { TvShowViewModel(get()) }
    viewModel { DetailViewModel(get()) }
    viewModel { SearchViewModel(get()) }
}