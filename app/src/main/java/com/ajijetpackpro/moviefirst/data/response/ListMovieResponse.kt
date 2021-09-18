package com.ajijetpackpro.moviefirst.data.response

import com.google.gson.annotations.SerializedName

data class ListMovieResponse(
    @SerializedName("status_message")
    val statusMessage: String? = null,
    @SerializedName("status_code")
    val statusCode: Int? = null,
    @SerializedName("results")
    val result: List<MovieItem>? = null
)

data class MovieItem(
    @SerializedName("id")
    var id: Int = 0,
    @SerializedName("title")
    var title: String? = null,
    @SerializedName("vote_average")
    var uScore: Double? = null,
    @SerializedName("release_date")
    var rDate: String? = null,
    @SerializedName("genre_ids")
    var genreIds: List<Int>? = null,
    @SerializedName("overview")
    var overview: String? = null,
    @SerializedName("poster_path")
    var poster: String? = null,
    @SerializedName("backdrop_path")
    var background: String? = null
)

