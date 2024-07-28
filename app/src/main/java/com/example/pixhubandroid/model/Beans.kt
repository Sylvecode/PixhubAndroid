package com.example.pixhubandroid.model

import com.google.gson.annotations.SerializedName
import java.net.ProtocolFamily


fun main () {

}


data class AccountBean(
    var id: Long? = null,
    var username: String = "",
    var familyName: String = "",
    var name: String = "",
    var email: String = "",
    var password: String = ""
)

class MediaBean {
    var adult = false

    @SerializedName("backdrop_path")
    var backdropPath: String? = null

    @SerializedName("genre_ids")
    var genreIds: List<Int>? = null
    var id = 0

    @SerializedName("original_language")
    var originalLanguage: String? = null

    @SerializedName("original_title")
    var originalTitle: String? = null
    var overview: String? = null
    var popularity = 0.0

    @SerializedName("poster_path")
    var posterPath: String? = null

    @SerializedName("release_date")
    var releaseDate: String? = null
    var title: String? = null
    var video = false

    @SerializedName("vote_average")
    var voteAverage = 0.0

    @SerializedName("vote_count")
    var voteCount = 0
}

class ArtistBean {
    @SerializedName("adult")
    var adult: Boolean = false

    @SerializedName("gender")
    var gender: Int = 0

    @SerializedName("id")
    var id: Int = 0

    @SerializedName("known_for_department")
    var knownForDepartment: String? = null

    @SerializedName("name")
    var name: String? = null

    @SerializedName("original_name")
    var originalName: String? = null

    var birthday: String? = null

    var biography: String? = null

    @SerializedName("popularity")
    var popularity: Double = 0.0

    @SerializedName("profile_path")
    var profilePath: String? = null

    @SerializedName("cast_id")
    var castId: Int = 0

    @SerializedName("character")
    var character: String? = null

    @SerializedName("credit_id")
    var creditId: String? = null

    @SerializedName("order")
    var order: Int = 0
}

