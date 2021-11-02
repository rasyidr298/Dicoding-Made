package id.rrdev.core.domain.repository

import id.rrdev.core.data.Resource
import id.rrdev.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface IMovieAppRepository {

    fun getAllMovies(sort: String): Flow<Resource<List<Movie>>>

    fun getAllTvShows(sort: String): Flow<Resource<List<Movie>>>

    fun getFavoriteMovies(sort: String): Flow<List<Movie>>

    fun getFavoriteTvShows(sort: String): Flow<List<Movie>>

    fun getSearchMovies(search: String): Flow<List<Movie>>

    fun getSearchTvShows(search: String): Flow<List<Movie>>

    fun setMovieFavorite(movie: Movie, state: Boolean)

}