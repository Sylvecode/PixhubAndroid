package com.example.pixhubandroid.model

import com.google.gson.annotations.SerializedName


class MediaResponse {
    var dates: Dates? = null
    var page = 0
    var results: List<MediaBean>? = null
    var cast: List<MediaBean>? = null

    @SerializedName("total_pages")
    var totalPages = 0

    @SerializedName("total_results")
    var totalResults = 0

    class Dates {
        var maximum: String? = null
        var minimum: String? = null
    }

    class Movie {

    }
}

