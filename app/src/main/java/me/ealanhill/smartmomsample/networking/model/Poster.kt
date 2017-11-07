package me.ealanhill.smartmomsample.networking.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class Poster(val uuid: String,
                  @SerializedName("first_name") val firstName: String,
                  @SerializedName("last_name") val lastName: String,
                  val picture: String) {
    fun getName(): String = String.format(Locale.US, "%s %s", firstName, lastName)
}