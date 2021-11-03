package id.rrdev.core.data.source.remote

import id.rrdev.core.data.source.remote.network.ApiResponse
import id.rrdev.core.data.source.remote.network.ApiService
import id.rrdev.core.data.source.remote.response.MovieResponse
import id.rrdev.core.data.source.remote.response.TvShowResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

class RemoteDataSource(private val apiService: ApiService) {

    private val apiKey = "967ad60bd20b9b2102526183323e3c3b"

    suspend fun getDisoverMovies(page: Int): Flow<ApiResponse<List<MovieResponse>>> {
        return flow {
            try {
                val response = apiService.getDiscoverMovie(page, apiKey)
                val movieList = response.results
                if (movieList.isNotEmpty()) {
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getTvShows(page: Int): Flow<ApiResponse<List<TvShowResponse>>> {
        return flow {
            try {
                val response = apiService.getTvShows(page, apiKey)
                val tvShowList = response.results
                if (tvShowList.isNotEmpty()) {
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getPopularMovie(page: Int): Flow<ApiResponse<List<MovieResponse>>> {
        return flow {
            try {
                val response = apiService.getPopularMovie(page, apiKey)
                val popularList = response.results
                if (popularList.isEmpty()) {
                    emit(ApiResponse.Success(response.results))
                }else {
                    emit(ApiResponse.Empty)
                }
            }catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getTopRatedMovie(page: Int): Flow<ApiResponse<List<MovieResponse>>> {
        return flow {
            try {
                val response = apiService.getTopRatedMovie(page, apiKey)
                val popularList = response.results
                if (popularList.isEmpty()) {
                    emit(ApiResponse.Success(response.results))
                }else {
                    emit(ApiResponse.Empty)
                }
            }catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getNowPlayingMovie(page: Int): Flow<ApiResponse<List<MovieResponse>>> {
        return flow {
            try {
                val response = apiService.getNowPlayingMovie(page, apiKey)
                val popularList = response.results
                if (popularList.isEmpty()) {
                    emit(ApiResponse.Success(response.results))
                }else {
                    emit(ApiResponse.Empty)
                }
            }catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getSearchMovie(query: String, page: Int): Flow<ApiResponse<List<MovieResponse>>> {
        return flow {
            try {
                val response = apiService.searchMovie(query, page, apiKey)
                val popularList = response.results
                if (popularList.isEmpty()) {
                    emit(ApiResponse.Success(response.results))
                }else {
                    emit(ApiResponse.Empty)
                }
            }catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

}
