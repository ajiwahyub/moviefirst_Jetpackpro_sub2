package com.ajijetpackpro.moviefirst.data.repository

import androidx.lifecycle.LiveData
import com.ajijetpackpro.moviefirst.data.entity.MovieDetail
import com.ajijetpackpro.moviefirst.data.entity.MovieList

interface MovieDataSource {

    fun getPopularMovies(): LiveData<List<MovieList>>

    fun getPopularTvshows(): LiveData<List<MovieList>>

    fun getMovieDetail(movieId: Int): LiveData<MovieDetail>

    fun getTvshowDetail(tvshowId:Int): LiveData<MovieDetail>
}