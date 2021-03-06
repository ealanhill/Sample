package me.ealanhill.smartmomsample

import me.ealanhill.smartmomsample.networking.model.Details
import me.ealanhill.smartmomsample.networking.model.Post

data class PostsState(val posts: List<Post> = listOf(),
                      val postDetail: Details = Details())