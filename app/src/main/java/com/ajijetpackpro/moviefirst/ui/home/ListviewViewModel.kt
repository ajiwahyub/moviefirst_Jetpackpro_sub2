package com.ajijetpackpro.moviefirst.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.ajijetpackpro.moviefirst.data.entity.MovieList
import com.ajijetpackpro.moviefirst.data.repository.MovieRepository

class ListviewViewModel(private val movieRepository: MovieRepository): ViewModel() {

    fun getMovies(): LiveData<List<MovieList>> = movieRepository.getPopularMovies()
    fun getTvShows(): LiveData<List<MovieList>> = movieRepository.getPopularTvshows()

}
