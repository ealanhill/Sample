package me.ealanhill.smartmomsample.networking.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class Comment(val uuid: String = "",
                   val message: String = "",
                   @SerializedName("like_count") val likeCount: String = "",
                   @SerializedName("created_dt") val created: Date = Calendar.getInstance().time,
                   @SerializedName("updated_dt") val update: Date = Calendar.getInstance().time,
                   val poster: Poster = Poster(),
                   val pictures: List<Picture> = listOf())