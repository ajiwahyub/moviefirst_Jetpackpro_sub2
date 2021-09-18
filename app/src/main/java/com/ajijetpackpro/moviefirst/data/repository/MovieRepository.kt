package com.ajijetpackpro.moviefirst.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ajijetpackpro.moviefirst.data.entity.MovieDetail
import com.ajijetpackpro.moviefirst.data.entity.MovieList
import com.ajijetpackpro.moviefirst.data.remote.RemoteDataSource
import com.ajijetpackpro.moviefirst.data.response.MovieItem
import com.ajijetpackpro.moviefirst.data.response.MovieResponse
import com.ajijetpackpro.moviefirst.data.response.TvshowItem
import com.ajijetpackpro.moviefirst.data.response.TvshowResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import java.util.ArrayList

class MovieRepository private constructor(private val remoteDataSource: RemoteDataSource) : MovieDataSource {

    companion object {
        @Volatile
        private var instance: MovieRepository? = null

        fun getInstance(remoteData: RemoteDataSource): MovieRepository =
            instance ?: synchronized(this) {
                instance ?: MovieRepository(remoteData)
            }
    }

    override fun getPopularMovies(): LiveData<List<MovieList>> {
        val popularMoviesResults = MutableLiveData<List<MovieList>>()
        CoroutineScope(IO).launch {
            remoteDataSource.getPopularMovies(object : RemoteDataSource.LoadPopularMoviesCallback {
                override fun onPopularMoviesReceived(movieResponse: List<MovieItem>) {
                    val movieList = ArrayList<MovieList>()
                    for (response in movieResponse) {
                        val movie = MovieList(
                            response.id,
                            response.title,
                            response.rDate,
                            response.uScore,
                            response.poster,
                            response.background
                        )
                        movieList.add(movie)
                    }
                    popularMoviesResults.postValue(movieList)
                }
            })
        }

        return popularMoviesResults
    }

    override fun getPopularTvshows(): LiveData<List<MovieList>> {
        val popularTvshowsResults = MutableLiveData<List<MovieList>>()
        CoroutineScope(IO).launch {
            remoteDataSource.getPopularTvshows(object :
                RemoteDataSource.LoadPopularTvshowsCallback {
                override fun onPopularTvshowsReceived(tvshowResponse: List<TvshowItem>) {
                    val tvshowsList = ArrayList<MovieList>()
                    for (response in tvshowResponse) {
                        val tvshow = MovieList(
                            response.id,
                            response.title,
                            response.rDate,
                            response.uScore,
                            response.poster,
                            response.background
                        )
                        tvshowsList.add(tvshow)
                    }
                    popularTvshowsResults.postValue(tvshowsList)
                }
            })
        }

        return popularTvshowsResults
    }

    override fun getMovieDetail(movieId: Int): LiveData<MovieDetail> {
        val movieDetailResult = MutableLiveData<MovieDetail>()
        CoroutineScope(IO).launch {
            remoteDataSource.getMovieDetail(
                movieId,
                object : RemoteDataSource.LoadMovieDetailCallback {
                    override fun onMovieDetailReceived(movieResponse: MovieResponse) {
                        val listOfGenre = ArrayList<String>()

                        for (genre in (movieResponse.genres)!!){
                            listOfGenre.add(genre!!.name!!)
                        }

                        val movieItem = MovieDetail(
                            movieResponse.id,
                            movieResponse.title,
                            movieResponse.rDate,
                            movieResponse.uScore,
                            movieResponse.overview,
                            listOfGenre,
                            movieResponse.poster,
                            movieResponse.background
                        )
                        movieDetailResult.postValue(movieItem)
                    }
                })
        }

        return movieDetailResult
    }

    override fun getTvshowDetail(tvshowId: Int): LiveData<MovieDetail> {
        val tvshowDetailResult = MutableLiveData<MovieDetail>()
        CoroutineScope(IO).launch {
            remoteDataSource.getTvshowDetail(
                tvshowId,
                object : RemoteDataSource.LoadTvshowDetailCallback {
                    override fun onTvshowDetailReceived(tvshowResponse: TvshowResponse) {
                        val listOfGenre = ArrayList<String>()

                        for (genre in (tvshowResponse.genres)!!){
                            listOfGenre.add(genre!!.name!!)
                        }
                        val tvshowItem = MovieDetail(
                            tvshowResponse.id,
                            tvshowResponse.title,
                            tvshowResponse.rDate,
                            tvshowResponse.uScore,
                            tvshowResponse.overview,
                            listOfGenre,
                            tvshowResponse.poster,
                            tvshowResponse.background
                        )
                        tvshowDetailResult.postValue(tvshowItem)
                    }
                })
        }

        return tvshowDetailResult
    }
}