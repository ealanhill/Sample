package me.ealanhill.smartmomsample.networking.model

import com.google.gson.annotations.SerializedName

data class Comment(val uuid: String = "",
                   val message: String = "",
                   @SerializedName("like_count") val likeCount: String = "",
                   @SerializedName("created_dt") val created: String = "",
                   @SerializedName("updated_dt") val update: String = "",
                   @SerializedName("did_like") val didLike: Boolean = false,
                   val poster: Poster = Poster(),
                   val pictures: List<Picture> = listOf())