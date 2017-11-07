package me.ealanhill.smartmomsample.networking

import me.ealanhill.smartmomsample.networking.model.PostDetail
import me.ealanhill.smartmomsample.networking.model.Posts
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api{
    @GET("v2/post")
    fun getPosts(): Call<Posts>

    @GET("v2/post")
    fun getPosts(@Query("after") uuid: String): Call<Posts>

    @GET("v2/post/{uuid}")
    fun getPostDetail(@Path("uuid") uuid: String): Call<PostDetail>
}