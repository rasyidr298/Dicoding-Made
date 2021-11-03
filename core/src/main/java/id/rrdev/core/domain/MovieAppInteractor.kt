package id.rrdev.core.domain.usecase

import id.rrdev.core.data.Resource
import id.rrdev.core.domain.Movie
import id.rrdev.core.domain.repository.IMovieAppRepository
import kotlinx.coroutines.flow.Flow


interface IMovieAppUseCase {
    fun getDiscoverMovie(page: Int, sort: String): Flow<Resource<List<Movie>>>
    fun getTvShows(page: Int, sort: String): Flow<Resource<List<Movie>>>
    fun getPopularMovies(page: Int, sort: String): Flow<Resource<List<Movie>>>
    fun getTopRatedMovies(page: Int, sort: String): Flow<Resource<List<Movie>>>
    fun getNowPlayingMovies(page: Int, sort: String): Flow<Resource<List<Movie>>>
    fun getSearchMovies(query: String, page: Int, sort: String): Flow<Resource<List<Movie>>>

    fun getFavoriteMovies(sort: String): Flow<List<Movie>>
    fun getFavoriteTvShows(sort: String): Flow<List<Movie>>

    fun getSearchMovies(search: String): Flow<List<Movie>>
    fun getSearchTvShows(search: String): Flow<List<Movie>>

    fun setMovieFavorite(movie: Movie, state: Boolean)
}

class MovieAppInteractor(private val iMovieAppRepository: IMovieAppRepository) : IMovieAppUseCase {

    override fun getDiscoverMovie(page:Int, sort: String): Flow<Resource<List<Movie>>> =
        iMovieAppRepository.getDiscoverMovies(page, sort)

    override fun getTvShows(page:Int, sort: String): Flow<Resource<List<Movie>>> =
        iMovieAppRepository.getTvShows(page, sort)

    override fun getPopularMovies(page: Int, sort: String): Flow<Resource<List<Movie>>> =
        iMovieAppRepository.getPopularMovies(page, sort)

    override fun getTopRatedMovies(page: Int, sort: String): Flow<Resource<List<Movie>>> =
        iMovieAppRepository.getTopRatedMovies(page, sort)

    override fun getNowPlayingMovies(page: Int, sort: String): Flow<Resource<List<Movie>>> =
        iMovieAppRepository.getNowPlayingMovies(page, sort)

    override fun getSearchMovies(query: String, page: Int,sort: String,): Flow<Resource<List<Movie>>> =
        iMovieAppRepository.getSearchMovies(query, page, sort)

    override fun getFavoriteMovies(sort: String): Flow<List<Movie>> =
        iMovieAppRepository.getFavoriteMovies(sort)

    override fun getSearchMovies(search: String): Flow<List<Movie>> =
        iMovieAppRepository.getSearchMovies(search)

    override fun getSearchTvShows(search: String): Flow<List<Movie>> =
        iMovieAppRepository.getSearchTvShows(search)

    override fun getFavoriteTvShows(sort: String): Flow<List<Movie>> =
        iMovieAppRepository.getFavoriteTvShows(sort)

    override fun setMovieFavorite(movie: Movie, state: Boolean) =
        iMovieAppRepository.setMovieFavorite(movie, state)

}