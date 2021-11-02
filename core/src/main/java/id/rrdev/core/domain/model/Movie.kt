package id.rrdev.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    var overview: String,
    var originalLanguage: String,
    var releaseDate: String,
    var popularity: Double,
    var voteAverage: Double,
    var id: Int,
    var title: String,
    var voteCount: Int,
    var posterPath: String,
    var favorite: Boolean = false,
    var isTvShows: Boolean = false
) : Parcelable