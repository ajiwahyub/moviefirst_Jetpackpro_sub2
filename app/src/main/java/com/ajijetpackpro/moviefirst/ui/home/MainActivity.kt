package com.ajijetpackpro.moviefirst.ui.home

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.toBitmap
import com.ajijetpackpro.moviefirst.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    var titleapp = "Movie First"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        pagerConfig()
        setActionBar(titleapp)

    }
    private fun pagerConfig() {
        val pager = SectionsAdapter(this, supportFragmentManager)
        viewPager.adapter = pager
        tabs.setupWithViewPager(viewPager)

        supportActionBar?.elevation = 0f
    }
    private fun setActionBar(titleapp: String) {
        val icon = ResourcesCompat.getDrawable(resources, R.drawable.ic_tmdb, null)?.toBitmap()
        val fixedIcon = BitmapDrawable(resources,
            icon.let { Bitmap.createScaledBitmap(it!!, 60, 60, true) })
        supportActionBar?.setHomeAsUpIndicator(fixedIcon)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        if (supportActionBar != null) {
            (supportActionBar as ActionBar).title = titleapp
        }
    }

}