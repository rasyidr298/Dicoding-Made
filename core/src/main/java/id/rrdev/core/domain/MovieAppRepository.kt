package id.rrdev.core.domain.repository

import id.rrdev.core.data.NetworkBoundResource
import id.rrdev.core.data.Resource
import id.rrdev.core.data.source.local.LocalDataSource
import id.rrdev.core.data.source.remote.RemoteDataSource
import id.rrdev.core.data.source.remote.network.ApiResponse
import id.rrdev.core.data.source.remote.response.MovieResponse
import id.rrdev.core.data.source.remote.response.TvShowResponse
import id.rrdev.core.domain.Movie
import id.rrdev.core.utils.AppExecutors
import id.rrdev.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


interface IMovieAppRepository {
    fun getDiscoverMovies(page: Int, sort: String): Flow<Resource<List<Movie>>>
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

class MovieAppRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IMovieAppRepository {

    override fun getDiscoverMovies(page: Int, sort: String): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<MovieResponse>>() {
            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.getAllMovies(sort).map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean {
                return data == null || data.isEmpty()
            }

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> {
                return remoteDataSource.getDisoverMovies(page)
            }

            override suspend fun saveCallResult(data: List<MovieResponse>) {
                val movieList = DataMapper.mapMovieResponsesToEntities(data)
                localDataSource.insertMovies(movieList)
            }
        }.asFlow()

    override fun getTvShows(page: Int, sort: String): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<TvShowResponse>>() {
            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.getAllTvShows(sort).map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean {
                return data == null || data.isEmpty()
            }

            override suspend fun createCall(): Flow<ApiResponse<List<TvShowResponse>>> {
                return remoteDataSource.getTvShows(page)
            }

            override suspend fun saveCallResult(data: List<TvShowResponse>) {
                val tvShowList = DataMapper.mapTvShowResponsesToEntities(data)
                localDataSource.insertMovies(tvShowList)
            }
        }.asFlow()

    override fun getPopularMovies(page: Int, sort: String): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<MovieResponse>>() {
            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.getAllMovies(sort).map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean {
                return data == null || data.isEmpty()
            }

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> {
                return remoteDataSource.getPopularMovie(page)
            }

            override suspend fun saveCallResult(data: List<MovieResponse>) {
                val movieList = DataMapper.mapMovieResponsesToEntities(data)
                localDataSource.insertMovies(movieList)
            }
        }.asFlow()

    override fun getTopRatedMovies(page: Int, sort: String): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<MovieResponse>>() {
            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.getAllMovies(sort).map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean {
                return data == null || data.isEmpty()
            }

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> {
                return remoteDataSource.getTopRatedMovie(page)
            }

            override suspend fun saveCallResult(data: List<MovieResponse>) {
                val movieList = DataMapper.mapMovieResponsesToEntities(data)
                localDataSource.insertMovies(movieList)
            }
        }.asFlow()

    override fun getNowPlayingMovies(page: Int, sort: String): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<MovieResponse>>() {
            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.getAllMovies(sort).map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean {
                return data == null || data.isEmpty()
            }

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> {
                return remoteDataSource.getNowPlayingMovie(page)
            }

            override suspend fun saveCallResult(data: List<MovieResponse>) {
                val movieList = DataMapper.mapMovieResponsesToEntities(data)
                localDataSource.insertMovies(movieList)
            }
        }.asFlow()

    override fun getSearchMovies(query: String, page: Int, sort: String): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<MovieResponse>>() {
            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.getAllMovies(sort).map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean {
                return data == null || data.isEmpty()
            }

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> {
                return remoteDataSource.getSearchMovie(query, page)
            }

            override suspend fun saveCallResult(data: List<MovieResponse>) {
                val movieList = DataMapper.mapMovieResponsesToEntities(data)
                localDataSource.insertMovies(movieList)
            }
        }.asFlow()

    override fun getSearchMovies(search: String): Flow<List<Movie>> {
        return localDataSource.getMovieSearch(search).map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun getSearchTvShows(search: String): Flow<List<Movie>> {
        return localDataSource.getTvShowSearch(search).map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun getFavoriteMovies(sort: String): Flow<List<Movie>> {
        return localDataSource.getAllFavoriteMovies(sort).map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun getFavoriteTvShows(sort: String): Flow<List<Movie>> {
        return localDataSource.getAllFavoriteTvShows(sort).map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setMovieFavorite(movie: Movie, state: Boolean) {
        val movieEntity = DataMapper.mapDomainToEntity(movie)
        appExecutors.diskIO().execute { localDataSource.setMovieFavorite(movieEntity, state) }
    }
}