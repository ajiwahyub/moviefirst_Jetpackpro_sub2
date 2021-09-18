package com.ajijetpackpro.moviefirst.data.network

import com.ajijetpackpro.moviefirst.data.response.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("movie/popular")
    fun getPopularMovies(
        @Query("api_key") apiKey: String = "644c266fd3acf79b6b231c0e7ba3f02e"
    ) : Call<ListMovieResponse>

    @GET("movie/{movie_id}")
    fun getDetailMovie(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = "644c266fd3acf79b6b231c0e7ba3f02e"
    ) : Call<MovieResponse>

    @GET("tv/popular")
    fun getPopularTvshows(
        @Query("api_key") apiKey: String = "644c266fd3acf79b6b231c0e7ba3f02e"
    ) : Call<ListTvshowResponse>

    @GET("tv/{tv_id}")
    fun getDetailTvshow(
        @Path("tv_id") tvShowId: Int,
        @Query("api_key") apiKey: String = "644c266fd3acf79b6b231c0e7ba3f02e"
    ) : Call<TvshowResponse>

}