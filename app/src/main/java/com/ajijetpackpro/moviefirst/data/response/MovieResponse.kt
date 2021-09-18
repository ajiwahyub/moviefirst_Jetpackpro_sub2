package com.ajijetpackpro.moviefirst.data.response

import com.google.gson.annotations.SerializedName


data class MovieResponse(
    @SerializedName("id")
    var id: Int = 0,
    @SerializedName("title")
    var title: String? = null,
    @SerializedName("vote_average")
    var uScore: Double? = null,
    @SerializedName("release_date")
    var rDate: String? = null,
    @SerializedName("genres")
    var genres: List<GenresItemMv?>? = null,
    @SerializedName("overview")
    var overview: String? = null,
    @SerializedName("poster_path")
    var poster: String? = null,
    @SerializedName("backdrop_path")
    var background: String? = null
)
data class GenresItemMv(

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("id")
    val id: Int? = null
)
