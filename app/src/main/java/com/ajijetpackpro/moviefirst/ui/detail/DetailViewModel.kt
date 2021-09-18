package com.ajijetpackpro.moviefirst.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.ajijetpackpro.moviefirst.data.entity.MovieDetail
import com.ajijetpackpro.moviefirst.data.repository.MovieRepository


class DetailViewModel(private val movieRepository: MovieRepository): ViewModel() {

    fun getDetailMovieById(movieId: Int): LiveData<MovieDetail> = movieRepository.getMovieDetail(movieId)

    fun getDetailTvShowById(tvshowId: Int): LiveData<MovieDetail> = movieRepository.getTvshowDetail(tvshowId)


}




