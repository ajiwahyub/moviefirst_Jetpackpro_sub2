package com.ajijetpackpro.moviefirst.data.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieDetail(

    var id: Int = 0,
    var title: String? = null,
    var rDate: String? = null,
    var uScore: Double? = null,
    var overview: String? = null,
    var genre: ArrayList<String>? = null,
    var poster: String? = null,
    var background: String? = null
) : Parcelable

