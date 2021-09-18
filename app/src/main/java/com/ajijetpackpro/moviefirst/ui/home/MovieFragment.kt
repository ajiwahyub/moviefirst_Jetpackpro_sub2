package com.ajijetpackpro.moviefirst.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ajijetpackpro.moviefirst.R
import com.ajijetpackpro.moviefirst.data.entity.MovieList
import com.ajijetpackpro.moviefirst.ui.detail.DetailActivity
import com.ajijetpackpro.moviefirst.utils.Helper.TYPE_MOVIE
import com.ajijetpackpro.moviefirst.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_movie.*


class MovieFragment : Fragment() {

    private var movies: ArrayList<MovieList> = ArrayList()
    private lateinit var adapter: ListviewAdapter
    private lateinit var mainViewModel: ListviewViewModel


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView()
        getMovielist()
    }

    private fun setRecyclerView() {
        adapter = ListviewAdapter(movies)
        adapter.notifyDataSetChanged()
        recycleViewMovie.layoutManager = LinearLayoutManager(activity)
        recycleViewMovie.adapter = adapter
    }
    private fun getMovielist() {

        val factory = ViewModelFactory.getInstance()
        activity?.let {
            mainViewModel =  ViewModelProvider(it,factory)[ListviewViewModel::class.java]
        }
        mainViewModel.getMovies().observe(viewLifecycleOwner, Observer { listMovie ->
            recycleViewMovie.adapter?.let { adapter ->
                when (adapter) {
                    is ListviewAdapter -> adapter.setData(listMovie as ArrayList<MovieList>)
                }
            }
        })
        adapter.setOnItemClickCallback(object : ListviewAdapter.OnItemClickCallback{
            override fun onItemClicked(data: MovieList) = setSelectedMovie(data)
        })
    }
    private fun setSelectedMovie(movie: MovieList) {

        val move = Intent(context, DetailActivity::class.java)
        move.putExtra(DetailActivity.PUT_EXTRA, movie.id)
        move.putExtra(DetailActivity.TYPE_EXTRA, TYPE_MOVIE)
        startActivity(move)
    }


}