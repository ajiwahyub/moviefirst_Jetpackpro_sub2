package com.ajijetpackpro.moviefirst.data.remote

import com.ajijetpackpro.moviefirst.data.network.ApiConfig
import com.ajijetpackpro.moviefirst.data.response.MovieItem
import com.ajijetpackpro.moviefirst.data.response.MovieResponse
import com.ajijetpackpro.moviefirst.data.response.TvshowItem
import com.ajijetpackpro.moviefirst.data.response.TvshowResponse
import com.ajijetpackpro.moviefirst.utils.IdlingResource
import retrofit2.await

class RemoteDataSource {

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource()
            }
    }
    suspend fun getPopularMovies(
        callback: LoadPopularMoviesCallback
    ) {
        IdlingResource.increment()
        ApiConfig.getApiService().getPopularMovies().await().result?.let { listMovie ->
            callback.onPopularMoviesReceived(
                listMovie
            )
            IdlingResource.decrement()
        }
    }

    suspend fun getMovieDetail(movieId: Int, callback: LoadMovieDetailCallback) {
        IdlingResource.increment()
        ApiConfig.getApiService().getDetailMovie(movieId).await().let { movie ->
            callback.onMovieDetailReceived(
                movie
            )
            IdlingResource.decrement()
        }
    }

    suspend fun getPopularTvshows(callback: LoadPopularTvshowsCallback) {
        IdlingResource.increment()
        ApiConfig.getApiService().getPopularTvshows().await().result?.let { listTvShow ->
            callback.onPopularTvshowsReceived(
                listTvShow
            )
            IdlingResource.decrement()
        }
    }

    suspend fun getTvshowDetail(tvShowId: Int, callback: LoadTvshowDetailCallback) {
        IdlingResource.increment()
        ApiConfig.getApiService().getDetailTvshow(tvShowId).await().let { tvShow ->
            callback.onTvshowDetailReceived(
                tvShow
            )
            IdlingResource.decrement()
        }
    }

    interface LoadPopularMoviesCallback {
        fun onPopularMoviesReceived(movieResponse: List<MovieItem>)
    }

    interface LoadMovieDetailCallback {
        fun onMovieDetailReceived(movieResponse: MovieResponse)
    }

    interface LoadPopularTvshowsCallback {
        fun onPopularTvshowsReceived(tvshowResponse: List<TvshowItem>)
    }

    interface LoadTvshowDetailCallback {
        fun onTvshowDetailReceived(tvshowResponse: TvshowResponse)
    }

}