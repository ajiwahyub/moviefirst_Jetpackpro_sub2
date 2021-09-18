package com.ajijetpackpro.moviefirst.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.ajijetpackpro.moviefirst.data.dummydata.MovieDummy
import com.ajijetpackpro.moviefirst.data.repository.MovieRepository
import org.junit.Test
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.mockito.Mock
import com.ajijetpackpro.moviefirst.data.entity.MovieDetail
import com.nhaarman.mockitokotlin2.verify
import org.junit.runner.RunWith
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {
    private val movieDummyDetail = MovieDummy.getDummyDetailMovie()[0]
    private val tvshowDummyDetail = MovieDummy.getDummyDetailTvshow()[0]
    private val movieId = movieDummyDetail.id
    private val tvshowId = tvshowDummyDetail.id
    private lateinit var viewModel: DetailViewModel

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var observer: Observer<MovieDetail>

    @Before
    fun setUp() {
        viewModel = DetailViewModel(movieRepository)
    }

    @Test
    fun getDetailMovieById() {
        val dummyMovie = MutableLiveData<MovieDetail>()
        dummyMovie.value = movieDummyDetail

        `when`(movieRepository.getMovieDetail(movieId)).thenReturn(dummyMovie)

        val detailMovie = viewModel.getDetailMovieById(movieId).value as MovieDetail

        verify(movieRepository).getMovieDetail(movieId)

        assertNotNull(detailMovie)
        assertEquals(movieDummyDetail.id, detailMovie.id)
        assertEquals(movieDummyDetail.title, detailMovie.title)
        assertEquals(movieDummyDetail.rDate, detailMovie.rDate)
        assertEquals(movieDummyDetail.uScore, detailMovie.uScore)
        assertEquals(movieDummyDetail.overview, detailMovie.overview)
        assertEquals(movieDummyDetail.genre, detailMovie.genre)
        assertEquals(movieDummyDetail.poster, detailMovie.poster)
        assertEquals(movieDummyDetail.background, detailMovie.background)

        viewModel.getDetailMovieById(movieId).observeForever(observer)
        verify(observer).onChanged(movieDummyDetail)
    }

    @Test
    fun getDetailTvShowById() {
        val dummyTvshow = MutableLiveData<MovieDetail>()
        dummyTvshow.value = tvshowDummyDetail
        `when`(movieRepository.getTvshowDetail(tvshowId)).thenReturn(dummyTvshow)

        val detailTvshow = viewModel.getDetailTvShowById(tvshowId).value as MovieDetail

        verify(movieRepository).getTvshowDetail(tvshowId)

        assertNotNull(detailTvshow)
        assertEquals(tvshowDummyDetail.id, detailTvshow.id)
        assertEquals(tvshowDummyDetail.title, detailTvshow.title)
        assertEquals(tvshowDummyDetail.rDate, detailTvshow.rDate)
        assertEquals(tvshowDummyDetail.uScore, detailTvshow.uScore)
        assertEquals(tvshowDummyDetail.overview, detailTvshow.overview)
        assertEquals(tvshowDummyDetail.genre, detailTvshow.genre)
        assertEquals(tvshowDummyDetail.poster, detailTvshow.poster)
        assertEquals(tvshowDummyDetail.background, detailTvshow.background)

        viewModel.getDetailTvShowById(tvshowId).observeForever(observer)
        verify(observer).onChanged(tvshowDummyDetail)
    }
}