package id.rrdev.androidmade.detail

import androidx.lifecycle.ViewModel
import id.rrdev.core.domain.IMovieAppUseCase
import id.rrdev.core.domain.Movie

class DetailViewModel(private val movieAppUseCase: IMovieAppUseCase) : ViewModel() {

    fun setFavoriteMovie(movie: Movie, newStatus: Boolean) {
        movieAppUseCase.setMovieFavorite(movie, newStatus)
    }
}
