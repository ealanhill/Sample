package me.ealanhill.smartmomsample.networking.model

import com.google.gson.annotations.SerializedName
import java.text.DateFormat
import java.util.*

data class Post(val uuid: String = "",
                val slug: String = "",
                val message: String = "",
                val pictures: List<Picture> = listOf(),
                @SerializedName("reply_count") val replyCount: String = "",
                @SerializedName("follow_count") val followCount: String = "",
                @SerializedName("created_dt") val created: Date = Calendar.getInstance().time,
                @SerializedName("updated_dt") val update: Date = Calendar.getInstance().time,
                val poster: Poster = Poster(),
                val type: Type = Type.POST) {
    enum class Type {
        POST,
        LOADING
    }

    private val dateFormat = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT, Locale.US)

    fun getDate(): String = dateFormat.format(created)
}