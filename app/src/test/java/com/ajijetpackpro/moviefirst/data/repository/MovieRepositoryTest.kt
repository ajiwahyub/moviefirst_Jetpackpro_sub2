package com.ajijetpackpro.moviefirst.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ajijetpackpro.moviefirst.data.dummydata.MovieDummy
import com.ajijetpackpro.moviefirst.data.remote.RemoteDataSource
import com.ajijetpackpro.moviefirst.utils.LiveDataTestUtil
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.mockito.Mockito

class MovieRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = Mockito.mock(RemoteDataSource::class.java)
    private val movieRepository = FakeMovieRepository(remote)

    private val listMovieResponses = MovieDummy.getDummyMovieResponse()
    private val listTvShowResponses = MovieDummy.getDummyTvshowsResponse()

    private val movieId = listMovieResponses[0].id
    private val tvShowId = listTvShowResponses[0].id

    private val movieDetail = MovieDummy.getDummyDetailMovieResponse()[0]
    private val tvshowDetail = MovieDummy.getDummyDetailTvshowResponse()[0]

    @Test
    fun getPopularMovies() {
        runBlocking {
            doAnswer {invocation ->
                (invocation.arguments[0] as RemoteDataSource.LoadPopularMoviesCallback).onPopularMoviesReceived(listMovieResponses)
                null
            }.`when`(remote).getPopularMovies(any())
        }

        val dataListMovies = LiveDataTestUtil.getValue(movieRepository.getPopularMovies())

        runBlocking {
            verify(remote).getPopularMovies(any())
        }

        assertNotNull(dataListMovies)
        assertEquals(listMovieResponses.size.toLong(), dataListMovies.size.toLong())
    }

    @Test
    fun getPopularTvshows() {
        runBlocking {
            doAnswer {invocation ->
                (invocation.arguments[0] as RemoteDataSource.LoadPopularTvshowsCallback).onPopularTvshowsReceived(listTvShowResponses)
                null
            }.`when`(remote).getPopularTvshows(any())
        }

        val dataListTvshows = LiveDataTestUtil.getValue(movieRepository.getPopularTvshows())

        runBlocking {
            verify(remote).getPopularTvshows(any())
        }

        assertNotNull(dataListTvshows)
        assertEquals(listTvShowResponses.size.toLong(), dataListTvshows.size.toLong())
    }

    @Test
    fun getMovieDetail() {
        runBlocking {
            doAnswer {invocation ->
                (invocation.arguments[1] as RemoteDataSource.LoadMovieDetailCallback).onMovieDetailReceived(movieDetail)
                null
            }.`when`(remote).getMovieDetail(eq(movieId),any())
        }

        val detailMovie = LiveDataTestUtil.getValue(movieRepository.getMovieDetail(movieId))

        runBlocking {
            verify(remote).getMovieDetail(eq(movieId),any())
        }

        assertNotNull(detailMovie)
        assertEquals(movieDetail.id, detailMovie.id)
    }

    @Test
    fun getTvshowDetail() {
        runBlocking {
            doAnswer {invocation ->
                (invocation.arguments[1] as RemoteDataSource.LoadTvshowDetailCallback).onTvshowDetailReceived(tvshowDetail)
                null
            }.`when`(remote).getTvshowDetail(eq(tvShowId),any())
        }

        val detailTvshow = LiveDataTestUtil.getValue(movieRepository.getTvshowDetail(tvShowId))

        runBlocking {
            verify(remote).getTvshowDetail(eq(tvShowId),any())
        }

        assertNotNull(detailTvshow)
        assertEquals(tvshowDetail.id, detailTvshow.id)
    }
}