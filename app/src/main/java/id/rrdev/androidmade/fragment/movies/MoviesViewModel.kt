package id.rrdev.androidmade.fragment.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import id.rrdev.core.data.Resource
import id.rrdev.core.domain.Movie
import id.rrdev.core.domain.IMovieAppUseCase

class MoviesViewModel(private val movieAppUseCase: IMovieAppUseCase) : ViewModel() {

    fun getDiscoverMovie(page: Int, sort: String): LiveData<Resource<List<Movie>>> {
        return movieAppUseCase.getDiscoverMovie(page, sort).asLiveData()
    }

    fun getTvShowMovie(page: Int, sort: String): LiveData<Resource<List<Movie>>> {
        return movieAppUseCase.getTvShows(page, sort).asLiveData()
    }

    fun getPopularMovie(page: Int, sort: String): LiveData<Resource<List<Movie>>> {
        return movieAppUseCase.getPopularMovies(page, sort).asLiveData()
    }

    fun getTopRatedMovie(page: Int, sort: String): LiveData<Resource<List<Movie>>> {
        return movieAppUseCase.getTopRatedMovies(page, sort).asLiveData()
    }

    fun getNowPlayingMovie(page: Int, sort: String): LiveData<Resource<List<Movie>>> {
        return movieAppUseCase.getNowPlayingMovies(page, sort).asLiveData()
    }

    fun getSearchMovie(query: String, page: Int, sort: String): LiveData<Resource<List<Movie>>> {
        return movieAppUseCase.getSearchMovies(query, page, sort).asLiveData()
    }
}