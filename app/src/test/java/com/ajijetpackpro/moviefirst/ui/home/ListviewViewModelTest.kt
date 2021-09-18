@file:Suppress("DEPRECATION")

package com.ajijetpackpro.moviefirst.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.ajijetpackpro.moviefirst.data.dummydata.MovieDummy
import com.ajijetpackpro.moviefirst.data.entity.MovieList
import com.nhaarman.mockitokotlin2.verify
import junit.framework.Assert
import org.junit.Test
import org.junit.Before
import org.junit.Rule
import org.mockito.Mock
import org.mockito.Mockito
import com.ajijetpackpro.moviefirst.data.repository.MovieRepository
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ListviewViewModelTest {
    private val movieDummyData = MovieDummy.getDummyMovie()
    private val tvshowDummyData = MovieDummy.getDummyTvshow()

    private lateinit var viewModel: ListviewViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepos: MovieRepository

    @Mock
    private lateinit var observer: Observer<List<MovieList>>

    @Before
    fun setUp() {
        viewModel = ListviewViewModel(movieRepos)
    }

    @Test
    fun getMovies() {
        val movie = MutableLiveData<List<MovieList>>()
        movie.value = movieDummyData

        Mockito.`when`(movieRepos.getPopularMovies()).thenReturn(movie)

        val listMovie = viewModel.getMovies().value

        verify(movieRepos).getPopularMovies()
        Assert.assertNotNull(listMovie)
        Assert.assertEquals(10, listMovie?.size)

        viewModel.getMovies().observeForever(observer)
        verify(observer).onChanged(movieDummyData)
    }

    @Test
    fun getTvShows() {
        val tvshow = MutableLiveData<List<MovieList>>()
        tvshow.value = tvshowDummyData

        Mockito.`when`(movieRepos.getPopularTvshows()).thenReturn(tvshow)

        val listTvshow = viewModel.getTvShows().value

        verify(movieRepos).getPopularTvshows()
        Assert.assertNotNull(listTvshow)
        Assert.assertEquals(10, listTvshow?.size)

        viewModel.getTvShows().observeForever(observer)
        verify(observer).onChanged(tvshowDummyData)
    }
}