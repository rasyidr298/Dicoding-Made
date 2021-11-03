package id.rrdev.core.data.source.remote.network

import id.rrdev.core.data.source.remote.response.ListMovieResponse
import id.rrdev.core.data.source.remote.response.ListTvShowResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("discover/movie")
    suspend fun getDiscoverMovie(
        @Query("page") page: Int,
        @Query("api_key") apiKey: String,
    ): ListMovieResponse

    @GET("discover/tv")
    suspend fun getTvShows(
        @Query("page") page: Int,
        @Query("api_key") apiKey: String
    ): ListTvShowResponse

    @GET("movie/popular")
    suspend fun getPopularMovie(
        @Query("page") page: Int,
        @Query("api_key") apiKey: String
    ): ListMovieResponse

    @GET("movie/top_rated")
    suspend fun getTopRatedMovie(
        @Query("page") page: Int,
        @Query("api_key") apiKey: String
    ): ListMovieResponse

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovie(
        @Query("page") page: Int,
        @Query("api_key") apiKey: String
    ): ListMovieResponse

    @GET("search/movie")
    suspend fun searchMovie(
        @Query("query") query: String,
        @Query("page") page: Int,
        @Query("api_key") apiKey: String
    ): ListMovieResponse
}