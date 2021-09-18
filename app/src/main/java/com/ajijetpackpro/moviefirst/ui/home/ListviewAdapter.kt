package com.ajijetpackpro.moviefirst.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ajijetpackpro.moviefirst.R
import com.ajijetpackpro.moviefirst.data.entity.MovieList
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_row_movie.view.*

class ListviewAdapter ( private val listMovie: ArrayList<MovieList>) :
    RecyclerView.Adapter<ListviewAdapter.RecyclerViewHolder>(){

        private lateinit var  onItemClickDetail: OnItemClickCallback


        fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
            this.onItemClickDetail = onItemClickCallback
        }
        fun setData(items: ArrayList<MovieList>) {
            listMovie.clear()
            listMovie.addAll(items)
            notifyDataSetChanged()
        }


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
            val view: View = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_row_movie, parent, false)
            return RecyclerViewHolder(view)
        }
        override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) =
            holder.bind(listMovie[position])

        override fun getItemCount(): Int = listMovie.size

        inner class RecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            fun bind(list: MovieList) {
                with(itemView) {
                    tv_item_title.text = list.title
                    tv_item_userscore.text = list.uScore.toString()
                    tv_item_rdate.text = list.rDate
                    Glide.with(this).load("https://image.tmdb.org/t/p/w200"+list.poster).into(iv_item_poster)
                    Glide.with(this).load("https://image.tmdb.org/t/p/w780"+list.background).into(iv_item_bg)

                    itemView.setOnClickListener { onItemClickDetail.onItemClicked(list) }
                }
            }
        }

        interface OnItemClickCallback {
            fun onItemClicked(data: MovieList)
        }
    }