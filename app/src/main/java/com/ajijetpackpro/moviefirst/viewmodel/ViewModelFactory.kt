package com.ajijetpackpro.moviefirst.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ajijetpackpro.moviefirst.data.repository.MovieRepository
import com.ajijetpackpro.moviefirst.di.MovieInjection
import com.ajijetpackpro.moviefirst.ui.detail.DetailViewModel
import com.ajijetpackpro.moviefirst.ui.home.ListviewViewModel

class ViewModelFactory private constructor(private val mMovieRepository: MovieRepository): ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(MovieInjection.provideMovieRepository())
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(ListviewViewModel::class.java) -> {
                ListviewViewModel(mMovieRepository) as T
            }
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                DetailViewModel(mMovieRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }

    }
}