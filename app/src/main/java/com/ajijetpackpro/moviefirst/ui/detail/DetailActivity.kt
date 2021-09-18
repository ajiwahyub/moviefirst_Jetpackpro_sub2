package com.ajijetpackpro.moviefirst.ui.detail

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ajijetpackpro.moviefirst.R
import com.ajijetpackpro.moviefirst.data.entity.MovieDetail
import com.ajijetpackpro.moviefirst.utils.Helper.TYPE_MOVIE
import com.ajijetpackpro.moviefirst.viewmodel.ViewModelFactory
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    private lateinit var detailViewModel: DetailViewModel
    private var titleApp = "Movie First"


    companion object {
        const  val PUT_EXTRA = "put_extra"
        const val TYPE_EXTRA = "type_extra"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setActionBar(titleApp)

        val factory = ViewModelFactory.getInstance()
        val viewModel = ViewModelProvider(
            this@DetailActivity,
            factory
        )[DetailViewModel::class.java]

        detailViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            DetailViewModel::class.java)
        val id = intent.getIntExtra(PUT_EXTRA, 0)
        val type = intent.getStringExtra(TYPE_EXTRA)

        if (type.equals(TYPE_MOVIE, ignoreCase = true)) {
            viewModel.getDetailMovieById(id).observe(this, Observer { showDetail(it) })
        }
        else {
            viewModel.getDetailTvShowById(id).observe(this, Observer { it?.let{
                showDetail(it)
            } })
        }

    }

    private fun showDetail(detail : MovieDetail) {
        tv_detail_title.text = detail.title
        tv_detail_overview.text = detail.overview
        tv_detail_rdate.text = detail.rDate
        tv_detail_userscore.text = detail.uScore.toString()
        Glide.with(this).clear(iv_detail_poster)
        Glide.with(this).load("https://image.tmdb.org/t/p/w200"+ detail.poster).into(iv_detail_poster)
        Glide.with(this).clear(iv_detail_bg)
        Glide.with(this).load("https://image.tmdb.org/t/p/w780"+ detail.background).into(iv_detail_bg)
        tv_detail_genre.text = detail.genre!!.joinToString()
        tv_detail_overview.movementMethod = ScrollingMovementMethod()
    }

    private fun setActionBar(titleApp: String) {
        val icon = ResourcesCompat.getDrawable(resources, R.drawable.ic_tmdb, null)?.toBitmap()
        val fixedIcon = BitmapDrawable(resources,
            icon.let { Bitmap.createScaledBitmap(it!!, 60, 60, true) })
        supportActionBar?.setHomeAsUpIndicator(fixedIcon)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        if (supportActionBar != null) {
            (supportActionBar as ActionBar).title = titleApp
        }
    }

}