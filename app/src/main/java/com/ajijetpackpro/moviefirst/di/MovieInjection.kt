package com.ajijetpackpro.moviefirst.di

import com.ajijetpackpro.moviefirst.data.remote.RemoteDataSource
import com.ajijetpackpro.moviefirst.data.repository.MovieRepository

object MovieInjection {

    fun provideMovieRepository(): MovieRepository {
        val remoteDataSource = RemoteDataSource.getInstance()
        return MovieRepository.getInstance(remoteDataSource)
    }
}